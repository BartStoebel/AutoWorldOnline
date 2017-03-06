/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 *
 * @author Vinnie
 */
public class Personenwagen extends Voertuig{
    
    private final int MAX_ZITPLAATSEN = 8;
    private static final Rijbewijs[] TOEGESTAAN_RIJBEWIJS = new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    
    
    public Personenwagen(String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Mens bestuurder, Mens...passagiers){
        
        super(8, merk, DatumEersteIngebruikname, aankoopprijs, zitplaatsen, bestuurder, passagiers);
            

    }
    
    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return TOEGESTAAN_RIJBEWIJS;
    }
    @Override
    protected int getMAX_ZITPLAATSEN(){
        return MAX_ZITPLAATSEN;
    }
    
}
