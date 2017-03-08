/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util;

/**
 *
 * @author Vinnie
 */
public enum Maat {
    centimeter (1), decimeter (10), meter (100);
    
    private final double MAAT;
    
    Maat(double maat){
        this.MAAT = maat;
    }
    public double getMaat(){
        return MAAT;
    }
}
