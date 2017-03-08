/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.awt.Color;
import java.io.Serializable;

/**
 *
 * @author Vinnie
 */
public class Personenwagen extends Voertuig implements Serializable{
    
    private final int MAX_ZITPLAATSEN = 8;
    private static final Rijbewijs[] TOEGESTAAN_RIJBEWIJS = new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    
    private Color kleur;
    
    public Personenwagen( String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Color kleur, Mens bestuurder, Mens...passagiers){
        super(merk, DatumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, passagiers);
        this.kleur = kleur;
    }
    
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return TOEGESTAAN_RIJBEWIJS;
    }
    @Override
    protected int getMAX_ZITPLAATSEN(){
        return MAX_ZITPLAATSEN;
    }

    @Override
    public String toString() {
        return super.toString() + " " + super.getZitplaatsen();
    }
    
    
    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) {
        this.kleur = kleur;
    }
}
