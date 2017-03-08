/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import java.io.Serializable;

/**
 *
 * @author Vinnie
 */
public class Boekentas implements Laadbaar, Serializable {
    
    private Volume laadvolume;
    private String kleur;

    public Boekentas(Volume laadvolume, String kleur) {
        this.laadvolume = laadvolume;
        this.kleur = kleur;
    }
    
    

    @Override
    public Volume getLaadvolume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLaadvolume(Volume vol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
