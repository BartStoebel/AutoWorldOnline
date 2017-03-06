/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

import java.io.Serializable;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Vinnie
 */
public class Datum implements Comparable<Datum>, Serializable{
    private static final long serialVersionUID = 1L;
    
    private final int DAG;
    private final int MAAND;
    private final int JAAR;
    
    private final LocalDate datum;
    
    /**
     * Creëer een nieuwe immutable datum tussen 01/01/1583 en 31/12/4099
     * @param dag int DAG van de MAAND
     * @param maand int MAAND
     * @param jaar int jaartal
     */
    public Datum(int dag, int maand, int jaar) throws DatumException{
        try{
            datum = LocalDate.of(jaar, maand, dag);
            if(datum.isBefore(LocalDate.of(1583,1,1))){
                throw new DatumException("De datum moet groter zijn dan: 1/1/1583");
            } else if (datum.isAfter(LocalDate.of(4099,12,31))){
                throw new DatumException("De datum moet kleiner zijn dan: 31/12/499");    
            }         
        }catch(DateTimeException ex){
            throw new DatumException ("De datum is niet correct. Geef een geldige"
                    + " dag, maand en jaar in!" + ex.getMessage());
        }
        this.DAG = dag;
        this.MAAND = maand;
        this.JAAR = jaar;
    }

    /**
     * 
     * @return datum dd/MM/yyy
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return datum.format(formatter);
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
        final Datum other = (Datum) obj;
        if (!this.datum.isEqual(other.datum)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.DAG;
        hash = 47 * hash + this.MAAND;
        hash = 47 * hash + this.JAAR;
        return hash;
    }

   @Override
    public int compareTo(Datum o) {
        return datum.compareTo(o.datum);
    }
    
    public int getDAG() {
        return DAG;
    }

    public int getMAAND() {
        return MAAND;
    }

    public int getJAAR() {
        return JAAR;
    }
}
