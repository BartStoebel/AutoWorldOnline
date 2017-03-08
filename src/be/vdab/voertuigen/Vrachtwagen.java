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
import be.vdab.util.mens.Rijbewijs;

/**
 *
 * @author Vinnie
 */
public class Vrachtwagen extends Voertuig implements Laadbaar{
    
    private Volume volume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;
    
    private static final Rijbewijs[] TOEGESTAAN_RIJBEWIJS = new Rijbewijs[]{Rijbewijs.C, Rijbewijs.CE};

    private final int MAX_ZITPLAATSEN = 3;

    public Vrachtwagen(String merk, Datum DatumEersteIngebruikname, int aankoopprijs, 
            int zitplaatsen, Volume volume, int maximaalToegelatenMassa, 
            int aantalAssen, Mens bestuurder, Mens... passagiers) {
        super(merk, DatumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, passagiers);
        if (volume == null) {
           throw new IllegalArgumentException("Volume moet worden opgegeven!");
        } 
        if (maximaalToegelatenMassa < 0){
            throw new IllegalArgumentException("Maximaal toegelaten massa kan "
                    + "niet kleiner zijn dan 0");
        }
        if (aantalAssen <2){
            throw new IllegalArgumentException("Het aantal assen kan niet "
                + "kleiner zijn dan 2");
        }
        setLaadvolume(volume);
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        this.aantalAssen = aantalAssen;
    }

    @Override
    public String toString() {
        String string = super.toString();
        string = string + " assen:" + aantalAssen + ", maximaal toegelaten massa:" + 
                maximaalToegelatenMassa + ", laadvolume:" + volume;
        return string;
    }
    
    //Overrided methods van Voertuig:
    @Override
    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return TOEGESTAAN_RIJBEWIJS;
    }

    //Overrided methods van Laadbaar
    @Override
    public Volume getLaadvolume() {
        return volume;
    }

    @Override
    public final void setLaadvolume(Volume vol) {
        this.volume = vol;
    }

    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    public int getAantalAssen() {
        return aantalAssen;
    }
    
    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        this.maximaalToegelatenMassa = maximaalToegelatenMassa;
    }

    public void setAantalAssen(int aantalAssen) {
        this.aantalAssen = aantalAssen;
    }
    
}
