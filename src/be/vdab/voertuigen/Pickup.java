/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Vinnie
 */
public class Pickup extends Personenwagen implements Laadbaar, Serializable{
    
    private Volume volume;

    public Pickup(String merk, Datum DatumEersteIngebruikname, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder, Mens... passagiers) {
        super(merk, DatumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, bestuurder, passagiers);
        
    }

    
    
    //Overrided methods van interface Laadbaar
    @Override
    public Volume getLaadvolume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setLaadvolume(Volume vol) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
