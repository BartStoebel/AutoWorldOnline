/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


/**
 *
 * @author Vinnie
 */
public abstract class Voertuig implements Comparable<Voertuig>, Serializable{
    private static final long serialVersionUID = 1L;
    
    private String merk;
    private Datum DatumEersteIngebruikname;
    private int aankoopprijs;
    private final int zitplaatsen; // totaal aantal, bestuurdersplaats inbegrepen!
    private Set<Mens> passagiers;  // zonder bestuurder! laatst toegevoegde is: (Mens)inzittenden.toArray()[inzittenden.size()-1]
    
    private Mens bestuurder;
    
    //private Rijbewijs[] rijbewijs;
    
    //Nummerplaat wordt aangemaakt:
    private final Nummerplaat NUMMERPLAAT;
    
    //Comparators
    private static final Comparator<Voertuig> vergelijkAankoopprijs = new VergelijkAankoopprijs();
    private static final Comparator<Voertuig> vergelijkMerk = new VergelijkMerk();

    //abstract methods
    protected abstract int getMAX_ZITPLAATSEN();
    protected abstract Rijbewijs[] getToegestaneRijbewijzen() ;
    
    //constructor
    public Voertuig(String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Mens bestuurder, Mens...passagiers) throws MensException {
        setMerk(merk);
        setDatumEersteIngebruikname(DatumEersteIngebruikname);
        setAankoopprijs(aankoopprijs);
        
        if (zitplaatsen > 0 && zitplaatsen <= getMAX_ZITPLAATSEN()){
            this.zitplaatsen = zitplaatsen;
        }else {
            throw new IllegalArgumentException ("Aantal zitplaatsen moet groter "
                    + "zijn dan 0 en kleiner of gelijk aan " + getMAX_ZITPLAATSEN());
        }
        try{
            setBestuurder(bestuurder);
            setPassagiers(passagiers);
        }catch (MensException ex){
            throw ex;
        }
        
        
        NUMMERPLAAT = DIV.getNummerplaat();
    }

    //Overrided methods
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.NUMMERPLAAT);
        hash = 67 * hash + Objects.hashCode(this.merk);
        hash = 67 * hash + Objects.hashCode(this.DatumEersteIngebruikname);
        hash = 67 * hash + Objects.hashCode(this.aankoopprijs);
        hash = 67 * hash + Objects.hashCode(this.zitplaatsen);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Voertuig other = (Voertuig) obj;
        if (!Objects.equals(this.NUMMERPLAAT, other.NUMMERPLAAT)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String namenPassagiers = "";
        if (getIngezeteneExclusiefBestuurder().size()>0){
            for (Mens mens : getIngezeteneExclusiefBestuurder()){
                namenPassagiers = namenPassagiers  + ", " + mens.getNaam();
            }
            namenPassagiers = namenPassagiers.substring(2, namenPassagiers.length());
            namenPassagiers = getBestuurder().toString() + " [" + namenPassagiers + "]";
        } else {
            namenPassagiers = getBestuurder().toString() + "";
        }
        return NUMMERPLAAT.getPLAAT() + " " + getMerk() + " " + DatumEersteIngebruikname.toString()
                + " " + aankoopprijs + " " + namenPassagiers;
    }

    @Override
    public int compareTo(Voertuig o) {
        return this.NUMMERPLAAT.compareTo(o.NUMMERPLAAT);
    }

    //methods
    /**
     * Kijk na of deze bestuurder dit voertuig mag besturen (het geldige rijbewijs
     * heeft). Het voertuigrijbewijs wordt opgehaald in 
     * voertuig.getToegestaneRijbewijzen().
     * @param mens
     * @return true indien hij dit voertuig mag besturen!
     */
    public  boolean controleerBestuurderGeldigRijbewijs(Mens mens){
        boolean bestuurderOK = false;
        List<Rijbewijs> lijst = Arrays.asList(getToegestaneRijbewijzen());
        for (Rijbewijs r : mens.getRijbewijs()){
            if(lijst.contains(r)){
                return bestuurderOK = true;
            }
        }
        return bestuurderOK;
    }
    
    //GetSetters
    public String getMerk() {
        return merk;
    }
    public final void setMerk(String merk) {
        if(merk != null && !merk.isEmpty()){
            this.merk = merk;
        }
    }

    public Datum getDatumEersteIngebruikname() {
        return DatumEersteIngebruikname;
    }
    public final void setDatumEersteIngebruikname(Datum DatumEersteIngebruikname) {
        if(DatumEersteIngebruikname != null){
            this.DatumEersteIngebruikname = DatumEersteIngebruikname;
        }
    }

    public int getAankoopprijs() {
        return aankoopprijs;
    }
    public final void setAankoopprijs(int aankoopprijs) {
        if(aankoopprijs > 0){
            this.aankoopprijs = aankoopprijs;
        }
    }
    
    public int getZitplaatsen() {
        return zitplaatsen;
    }
    public final void setZitplaatsen(int zitplaatsen) {
         
    }
    
    public void setPassagiers(Mens ... passagiers) throws MensException {
        this.passagiers = new LinkedHashSet<>();
        if(this.passagiers == null || passagiers != null){
            this.passagiers.addAll(Arrays.asList(passagiers));
        }
        if (getIngezetenen().size() > this.zitplaatsen){
            throw new MensException ("Er zijn meer inzittenden dan plaatsen!");
        }
    }

    /**
     * 
     * @return alle inzittenden van het voertuig, inclusief de bestuurder
     */
    public Set<Mens> getIngezetenen() {
        Set<Mens> inzittenden = new TreeSet<Mens>(passagiers);
        inzittenden.add(bestuurder);
//        Collections.sort(inzittenden);
//        inzittenden = inzittenden.stream().sorted();
        return inzittenden;
    }
    
    public Set<Mens> getIngezeteneExclusiefBestuurder(){
        Set<Mens> inzittenden = new LinkedHashSet<Mens>(passagiers);
        return inzittenden;
    }

    public Mens getBestuurder() {
        return bestuurder;
    }
    
    /**
     * Voeg een passagier toe, als er plaats voor is.
     * @param ingezetene
     * @throws MensException 
     */
    public void addIngezetene(Mens ingezetene)throws MensException {
        if (ingezetene == null){
            throw new IllegalArgumentException("Er is geen bestuurder aanwezig");
        }
        if (getIngezetenen().contains(ingezetene)){
            System.err.println("Deze persoon zit reeds in de wagen");
            return;
        }
        //is er nog plaats in het voertuig?
        if (getVrijePlaatsen() > 0){
            passagiers.add(ingezetene);
        } else {
            throw new MensException("Er is geen plaats meer voor een extra "
                    + "passagier.");
        }
    }

    /**
     * Voeg een bestuurder toe - of vervang huidige bestuurder, die dan een 
     * passagier wordt, indien de wagen nog niet volzet is.
     * @param bestuurder
     * @throws MensException 
     */
    public void setBestuurder(Mens bestuurder) throws MensException {
         if (bestuurder == null){
            throw new IllegalArgumentException("Er is geen bestuurder aanwezig");
        }
        if (!controleerBestuurderGeldigRijbewijs(bestuurder)){
            throw new MensException("Deze bestuurder heeft geen geldig rijbewijs"
                    + " voor dit voertuig");
        }
        //er is nog geen bestuurder (aanroep vanuit constructor)
        if (this.bestuurder == null){
            this.bestuurder = bestuurder;

        //Er is al een bestuurder: nieuwe bestuurder stapt in, huidige bestuurder
        //wordt toegevoegd bij passagiers:
        } else {
            //nieuwe bestuurder zit al bij de passagiers ... maar wordt toegevoegd als bestuurder
            if (passagiers.contains(bestuurder)){  
                passagiers.remove(bestuurder);
                passagiers.add(this.bestuurder);
                this.bestuurder = bestuurder;
            } else {
                //nieuwe bestuurder zit nog niet in auto: als er plaats is: voeg hem toe ...
                if (passagiers.size()< zitplaatsen -1){
                    passagiers.add(this.bestuurder);
                    this.bestuurder = bestuurder;
                } else {
                    throw new MensException("Het voertuig heeft reeds het maximum aantal"
                            + " ingezetenen bereikt!");
                }
            }
        }
    }
    
    public Nummerplaat getNummerplaat() {
        return NUMMERPLAAT;
    }
    
    private int getVrijePlaatsen(){
        return zitplaatsen - getIngezetenen().size() ;  
    }

    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return vergelijkAankoopprijs;
    }
    public static Comparator<Voertuig> getMerkComparator() {
        return vergelijkMerk;
    }
 
    public boolean isIngezetene(Mens mens){
        if (passagiers.contains(mens)|| bestuurder.equals(mens)){
            return true;
        } else {
            return false;
        }
    }
    
    private static class VergelijkMerk implements Comparator<Voertuig>,Serializable {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.getMerk().toUpperCase().compareTo(o2.getMerk().toUpperCase());
        }
    }
    private static class VergelijkAankoopprijs implements Comparator<Voertuig>,Serializable {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.getAankoopprijs() - (o2.getAankoopprijs());
        }
    }
    
}
