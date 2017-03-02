/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import static be.vdab.util.mens.Rijbewijs.*; 
import be.vdab.voertuigen.Voertuig;
/**
 *
 * @author Vinnie
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Datum datum;
        try{
            Mens a = new Mens("Bart", B, BE, C, CE);
            System.out.println(a);
            System.out.println(a.equals(a));
            datum = new Datum(2, 2, 2017);
            
            Voertuig v = new Voertuig("Toyota", datum, 20000, 5, a , a);
            
        } catch (DatumException e){
            System.err.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.toString());
        }
        
        
    }
    
}
