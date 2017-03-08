/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Bart Stoebel
 */
public class Boekentas implements Laadbaar, Serializable {
    
    private Volume laadvolume;
    private String kleur;

    public Boekentas(String kleur, Volume volume) {
        setKleur(kleur);
        setLaadvolume(volume);
    }
    
    //Overrided methods

    @Override
    public String toString() {
        return "boekentas " + kleur + " " + laadvolume  ;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.laadvolume);
        hash = 11 * hash + Objects.hashCode(this.kleur);
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
        final Boekentas other = (Boekentas) obj;
        if (!Objects.equals(this.kleur, other.kleur)) {
            return false;
        }
        if (!Objects.equals(this.laadvolume, other.laadvolume)) {
            return false;
        }
        return true;
    }
    
    
    
    //Overrided methods van interface Laadbaar
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public void setLaadvolume(Volume vol) {
        if(vol != null){
            this.laadvolume = vol;
        } else {
            throw new IllegalArgumentException ("Dit is geen geldig laadvolume"
                    + " voor deze boekentas.");
        }
    }
    
    //Overrided methods
    
    //GetSetters
    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        if (kleur != null && !kleur.isEmpty()) {
            this.kleur = kleur;
        } else {
            throw new IllegalArgumentException ("Dit is geen geldige kleur "
                    + "voor deze boekentas.");
        }
    }
    
    
}
