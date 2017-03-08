/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bart Stoebel
 */
public class Volume implements Comparable<Volume>, Serializable{
    private static final long serialVersionUID = 1L;
    
    private final int hoogte;
    private final int breedte; 
    private final int diepte;
    private final Maat maat;

    public Volume(int hoogte, int breedte, int diepte, Maat maat) {
        controleerGroterDanNul(diepte);
        controleerGroterDanNul(hoogte);
        controleerGroterDanNul(breedte);
        this.maat = maat;
        this.hoogte = hoogte;
        this.breedte = breedte;
        this.diepte = diepte;
    }

    @Override
    public int compareTo(Volume o) {
        return new Long(this.getVolume()).compareTo(new Long(o.getVolume())) ;
    }

    @Override
    public String toString() {
        return hoogte + "(h)x" + breedte + "(b)x" + diepte + "(d) " + maat;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.hoogte;
        hash = 53 * hash + this.breedte;
        hash = 53 * hash + this.diepte;
        hash = 53 * hash + Objects.hashCode(this.maat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Volume other = (Volume) obj;
        if (this.hoogte != other.hoogte) {
            return false;
        }
        if (this.breedte != other.breedte) {
            return false;
        }
        if (this.diepte != other.diepte) {
            return false;
        }
        if (this.maat != other.maat) {
            return false;
        }
        return true;
    }
    
    /**
     * Controleer of de value > 0. Indien niet wordt een VolumeException gethrowed
     * @param value 
     */
    private void controleerGroterDanNul (int value){
        if (value < 0){
            throw new VolumeException (value + " is niet groter dan nul. Een "
                    + "afstand kan niet kleiner zijn dan nul.");
        }
    }
    
    /**
     * in cm³
     * @return 
     */
    public long getVolume(){
        return (long)(hoogte * breedte * diepte *(long)Math.pow(this.maat.getMaat(),3));
    }

    /**
     * in de maat beschreven in getMaat()
     * @return 
     */
    public int getHoogte() {
        return hoogte;
    }

    /**
     * in de maat beschreven in getMaat()
     * @return 
     */
    public int getBreedte() {
        return breedte;
    }

    /**
     * in de maat beschreven in getMaat()
     * @return 
     */
    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }
    
}
