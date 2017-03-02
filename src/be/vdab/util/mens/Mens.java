/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author Vinnie
 */
public class Mens implements Serializable, Comparable<Mens>{
    private String naam;
    private Set<Rijbewijs> rijbewijs;
    
    /**
     * Ik had eerst een Exception gethrowed bij een setNaam, die geen naam zou 
     * bevatten. Dit wordt nu niet opgevangen. Het object wordt toch gemaakt!
     * Indien ik het wel implementeer, krijg ik een foutmelding van de foutenmodule
     * @param naam 
     */
    public Mens(String naam) {
        setNaam(naam);
        this.rijbewijs = new TreeSet<>();
    }
    public Mens(String naam, Rijbewijs r)  {
        this(naam) ;
        this.rijbewijs.add(r);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1)  {
        this(naam, r);
        this.rijbewijs.add(r1);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1, Rijbewijs r2)  {
        this(naam, r, r1);
        this.rijbewijs.add(r2);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1, Rijbewijs r2, 
            Rijbewijs r3)  {
        this(naam, r, r1, r2);
        this.rijbewijs.add(r3);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1, Rijbewijs r2, 
            Rijbewijs r3, Rijbewijs r4)  {
        this(naam, r, r1, r2, r3);
        this.rijbewijs.add(r4);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1, Rijbewijs r2, 
            Rijbewijs r3, Rijbewijs r4, Rijbewijs r5)  {
        this(naam, r, r1, r2, r3, r4);
        this.rijbewijs.add(r5);
    }
    public Mens(String naam, Rijbewijs r, Rijbewijs r1, Rijbewijs r2, 
            Rijbewijs r3, Rijbewijs r4, Rijbewijs r5, Rijbewijs r6) {
        this(naam, r, r1, r2, r3, r4, r5);
        this.rijbewijs.add(r6);
    }

    @Override
    public String toString() {
        String rij = "";
        for(Rijbewijs r : getRijbewijs()){
            rij = rij + ", " + r.getRijbewijs();
        }
        if (rij.isEmpty()){
            return naam;
        } else {
            return naam + "(" + rij.substring(2, rij.length()) + ")";
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.naam);
        hash = 19 * hash + Objects.hashCode(this.rijbewijs);
        return hash;
    }
    
    /**
     * Hier twijfel ik of Rijbewijs moet opgenomen worden! Naam zou theoretisch 
     * uniet moeten zijn ... rijbewijs kan veranderen in loop van de tijd!
     * @param obj
     * @return 
     */
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
        final Mens other = (Mens) obj;
        if (!Objects.equals(this.naam, other.naam)) {
            return false;
        }
//        if (!Objects.equals(this.rijbewijs, other.rijbewijs)) {
//            return false;
//        }
        return true;
    }

    /**
     * Idem als equals: Rijbewijs niet opgenomen in vergelijking, ervan uitgaande 
     * dat naam uniek is.
     * @param o
     * @return 
     */
    @Override
    public int compareTo(Mens o) {
        return getNaam().compareTo(o.getNaam());
    }
    
    public String getNaam() {
        return naam;
    }

    /**
     * Hier zou ik een Exception throwen, maar dit is niet toegelaten door de 
     * testmodule ... Een foutboodschap wordt weergegeven.
     * @param naam 
     */
    public final void setNaam(String naam) {
        if (naam != null && !naam.isEmpty()){
            this.naam = naam;
        } else{
            System.err.println("De mens moet verplicht een naam hebben!! Dit"
                    + "kan niet worden afgedwongen door de testmodule!");
        }
    }

    public Rijbewijs[] getRijbewijs() {
        return rijbewijs.toArray(new Rijbewijs[rijbewijs.size()]);
    }

    public void setRijbewijs(Set<Rijbewijs> rijbewijs) {
        this.rijbewijs = rijbewijs;
    }
    
}
