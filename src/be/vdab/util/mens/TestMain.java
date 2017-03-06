/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import static be.vdab.util.mens.Rijbewijs.*; 
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Voertuig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            Mens a = new Mens("Ammelie", B, BE, C, CE);
            Mens b = new Mens("Eva",A, B);
            Mens c = new Mens ("Ruben");
            //System.out.println(a);
            //System.out.println(a.equals(a));
            datum = new Datum(2, 2, 2017);
            
            Personenwagen p = new Personenwagen("auto", datum, 18300, 5, a,b);
            p.setBestuurder(b);
            System.out.println(p.isIngezetene(a));
            System.out.println(p.isIngezetene(b));
            System.out.println(p.getIngezetenen().size());
            
            System.out.println(p);
//            for (Voertuig voertuig : lijst){
//                System.out.println(voertuig.getMerk() + ": " + voertuig.getIngezetenen());
//            }
            
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
//        } catch (MensException e){
//            System.err.println(e.getMessage());
        }catch (DatumException e){
            System.err.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.toString());
        }
//        int[] a = new int[2];
//        a[0] = 1;
//        System.out.println(a.length);
        
        
    }
    
}
