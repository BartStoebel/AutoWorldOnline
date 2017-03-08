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
public class Pickup extends Personenwagen implements Laadbaar{
    
    private Volume volume;

    public Pickup(String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Color kleur, Volume volume, Mens bestuurder, 
            Mens... passagiers) {
        super(merk, DatumEersteIngebruikname, aankoopprijs, zitplaatsen, kleur, 
                bestuurder, passagiers);
        setLaadvolume(volume);
    }

    @Override
    public String toString() {
        return super.toString() + " " + volume;
    }
    
    //Overrided methods van interface Laadbaar
    @Override
    public Volume getLaadvolume() {
        return this.volume;
    }

    @Override
    public final void setLaadvolume(Volume vol) {
        this.volume = vol;
    }
    
    
}
