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
    private String merk;
    private Datum DatumEersteIngebruikname;
    private int aankoopprijs;
    private final int zitplaatsen; // totaal aantal, bestuurdersplaats inbegrepen!
    private Set<Mens> passagiers;  // zonder bestuurder! laatst toegevoegde is: (Mens)inzittenden.toArray()[inzittenden.size()-1]
    
    private Mens bestuurder;
    
    private Rijbewijs[] rijbewijs;
    
    //Nummerplaat wordt aangemaakt:
    private final Nummerplaat NUMMERPLAAT;
    
    //Comparators
    private static final Comparator<Voertuig> vergelijkAankoopprijs = new VergelijkAankoopprijs();
    private static final Comparator<Voertuig> vergelijkMerk = new VergelijkMerk();

    
    public Voertuig(String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Mens bestuurder, Mens...passagiers) {
        setMerk(merk);
        setDatumEersteIngebruikname(DatumEersteIngebruikname);
        setAankoopprijs(aankoopprijs);
        try{
            if (zitplaatsen>0 && zitplaatsen <= getMAX_ZITPLAATSEN()){
                this.zitplaatsen = zitplaatsen;
            }else {
                this.zitplaatsen = 5/0;  // fout genereren
            }
         }catch (ArithmeticException e){
             throw new IllegalArgumentException ("Aantal zitplaatsen kan niet negatief zijn.");
         }
        try{
            setPassagiers(passagiers);
        }catch (MensException ex){
            ex.getMessage();
        }
        setBestuurder(bestuurder);
        NUMMERPLAAT = DIV.getNummerplaat();
    }

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
        for (Mens mens : getIngezeteneExclusiefBestuurder()){
            namenPassagiers = namenPassagiers  + ", " + mens.getNaam();
        }
        namenPassagiers = namenPassagiers.substring(2, namenPassagiers.length());
        return NUMMERPLAAT + " "+ merk + " " + DatumEersteIngebruikname + " " + aankoopprijs + " " + getBestuurder().toString() + " [" + namenPassagiers + "]";
    }

    @Override
    public int compareTo(Voertuig o) {
        return this.NUMMERPLAAT.compareTo(o.NUMMERPLAAT);
    }

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
    
    public void setPassagiers(Mens ... passagiers) throws MensException{
        try{
            if (passagiers.length < this.zitplaatsen){
                this.passagiers = new LinkedHashSet<>();
                this.passagiers.addAll(Arrays.asList(passagiers));
            } else {
                int a = Integer.parseInt("fout genereren");
            }
        } catch (NumberFormatException e){
            throw new MensException ("Er zijn meer inzittenden dan plaatsen!");
        }
    }
    
    protected abstract int getMAX_ZITPLAATSEN();
    
    protected abstract Rijbewijs[] getToegestaneRijbewijzen() ;
       
    

    /**
     * 
     * @return alle inzittenden van het voertuig, inclusief de bestuurder
     */
    public Set<Mens> getIngezetenen() {
        Set<Mens> inzittenden = new TreeSet<Mens>(passagiers);
        inzittenden.add(bestuurder);
        return inzittenden;
    }
    
    public Set<Mens> getIngezeteneExclusiefBestuurder(){
        Set<Mens> inzittenden = new LinkedHashSet<Mens>(passagiers);
        return inzittenden;
    }

    public Mens getBestuurder() {
        return bestuurder;
    }
    
    // dit is de setBestuurder ...
    public void addIngezetene(Mens bestuurder) /*throws MensException*/ {
        //er is nog geen bestuurder: aanroep vanuit constructor
        if (this.bestuurder == null){
            if (bestuurder == null){
                throw new IllegalArgumentException("Er is geen bestuurder aanwezig");
            } else {
                this.bestuurder = bestuurder;
            }
        // er is al een bestuurder: aanroep om nieuwe bestuurder te zetten, of passagier toe te voegen:    
        } else {
            //nieuwe bestuurder zit al bij de passagiers ... maar wordt toegevoegd als bestuurder
            List<Rijbewijs> bestuurderRijbewijzen = Arrays.asList(bestuurder.getRijbewijs());
            // GA HIER VERDER!!! COPY BEIDE ARRAYS IN 1 EN KIJK OF ER DUBBELS INZITTEN!
            Rijbewijs[] alleRijbewijzen = Arrays.
            if (passagiers.contains(bestuurder) && bestuurderRijbewijzen.contains(getToegestaneRijbewijzen())){  
                passagiers.remove(bestuurder);
                
            }
            
            if (passagiers.size()< zitplaatsen -1){
                passagiers.add(this.bestuurder);
            } else {
                new MensException("Het voertuig heeft reeds het maximum aantal ingezetenen bereikt!");
            }
            this.bestuurder = bestuurder;
        }
    }

    public void setBestuurder(Mens bestuurder) /*throws MensException*/ {
        if (this.bestuurder == null){
            if (bestuurder == null){
                new IllegalArgumentException("Er is geen bestuurder aanwezig");
            } else {
                this.bestuurder = bestuurder;
            }
        } else {
            if (passagiers.contains(bestuurder)){  //nieuwe bestuurder zit al bij de passagiers ... maar wordt bestuurder
                passagiers.remove(bestuurder);
            }
            if (passagiers.size()< zitplaatsen -1){
                passagiers.add(this.bestuurder);
            } else {
                new MensException("Het voertuig heeft reeds het maximum aantal ingezetenen bereikt!");
            }
            this.bestuurder = bestuurder;
        }
    }
    
    public Nummerplaat getNummerplaat() {
        return NUMMERPLAAT;
    }



          
    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return vergelijkAankoopprijs;
    }
    public static Comparator<Voertuig> getMerkComparator() {
        return vergelijkMerk;
    }
 
    public boolean isIngezetene(Mens mens){
        if (getIngezetenen().contains(mens)){
            return true;
        } else {
            return false;
        }
    }
    
    
    private static class VergelijkMerk implements Comparator<Voertuig> {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.getMerk().toUpperCase().compareTo(o2.getMerk().toUpperCase());
        }
    }
    private static class VergelijkAankoopprijs implements Comparator<Voertuig> {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.getAankoopprijs() - (o2.getAankoopprijs());
        }
    }
    
}
