/*
* @Autor Frank
* Dit is een oefening.
 */

package be.vdab.voertuigen.div;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author frank.roelants
 */
public class Nummerplaat implements Serializable, Comparable<Nummerplaat>{
    private static final long serialVersionUID = 1L;
    private final String PLAAT;
    
    /**
     * Default visibility. Creation of object only by DIV.getNummerplaat()
     * @param plaat the String for PLAAT
     */
    Nummerplaat(String plaat) {
        this.PLAAT = plaat;
    }

    public String getPLAAT() {
        return PLAAT;
    }
    public String getPlaat() {
        return PLAAT;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.PLAAT);
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
        final Nummerplaat other = (Nummerplaat) obj;
        if (!Objects.equals(this.PLAAT, other.PLAAT)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return PLAAT;
    }

    @Override
    public int compareTo(Nummerplaat o) {
        return PLAAT.compareTo(o.PLAAT);
    }

    
}
