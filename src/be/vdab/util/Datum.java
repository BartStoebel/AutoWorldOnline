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
    
    private final int dag;
    private final int maand;
    private final int jaar;
    
    private final LocalDate datum;
    
    /**
     * Creëer een nieuwe immutable datum tussen 01/01/1583 en 31/12/4099
     * @param dag int dag van de maand
     * @param maand int maand
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
        this.dag = dag;
        this.maand = maand;
        this.jaar = jaar;
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
        hash = 47 * hash + this.dag;
        hash = 47 * hash + this.maand;
        hash = 47 * hash + this.jaar;
        return hash;
    }

   @Override
    public int compareTo(Datum o) {
        return datum.compareTo(o.datum);
    }
    
    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }
}
