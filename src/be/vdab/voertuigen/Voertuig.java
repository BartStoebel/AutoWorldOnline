/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Vinnie
 */
public class Voertuig implements Comparable<Voertuig>, Serializable{
    private final Nummerplaat nummerplaat;
    private String merk;
    private Datum DatumEersteIngebruikname;
    private int aankoopprijs;
    private final int zitplaatsen;

    public Voertuig(String merk, Datum DatumEersteIngebruikname, int aankoopprijs,
            int zitplaatsen, Mens bestuurder, Mens...ingezetenen) {
        this.merk = merk;
        this.DatumEersteIngebruikname = DatumEersteIngebruikname;
        this.aankoopprijs = aankoopprijs;
        this.zitplaatsen = zitplaatsen;
        
        nummerplaat = DIV.getNummerplaat();
        
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.nummerplaat);
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
        if (!Objects.equals(this.nummerplaat, other.nummerplaat)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Voertuig{" + "nummerplaat=" + nummerplaat + ", merk=" + merk + ", DatumEersteIngebruikname=" + DatumEersteIngebruikname + ", aankoopprijs=" + aankoopprijs + ", zitplaatsen=" + zitplaatsen + '}';
    }

    @Override
    public int compareTo(Voertuig o) {
        return this.nummerplaat.compareTo(o.nummerplaat);
    }
    
    private static class VergelijkMerk implements Comparator<Voertuig> {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.merk.compareTo(o2.merk);
        }
    }

    private static class VergelijkAankoopprijs implements Comparator<Voertuig> {

        @Override
        public int compare(Voertuig o1, Voertuig o2) {
            return o1.aankoopprijs - (o2.aankoopprijs);
        }
    }
    
}
