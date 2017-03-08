/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import static be.vdab.util.mens.Rijbewijs.*; 
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
/**
 *
 * @author Vinnie
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//      Volume v0 = new Volume(100, 100, 100, Maat.centimeter);
//      Volume v1 = new Volume(10, 10, 10, Maat.decimeter);
//      Volume v2 = new Volume(1, 1, 1, Maat.meter);
//      System.out.println("Volume v0: " + v0.getVolume());
//      System.out.println("Volume v1: " + v1.getVolume());
//      System.out.println("Volume v2: " + v2.getVolume());
//      
//      System.out.println("Volume v0 compare to v2: " + v0.compareTo(v2));
      
//      assertTrue(v2.compareTo(v0) > 0);
//      assertTrue(v0.compareTo(v2) < 0);
//      assertTrue(v2.compareTo(v1) > 0);
//      assertTrue(v1.compareTo(v2) < 0);
        
        
        Datum datum;
        try{
            Mens a = new Mens("Ammelie", B, BE, C, CE);
            Mens b = new Mens("Eva",A, B);
            Mens c = new Mens ("Ruben");
            //System.out.println(a);
            //System.out.println(a.equals(a));
            datum = new Datum(2, 2, 2017);
            Volume volume = new Volume(20, 20, 20, Maat.meter);
            
            Voertuig p = new Vrachtwagen("Mercedes", datum, 18300, 3 , volume, 200, 3 , a);
            Voertuig pw = new Personenwagen("auto", datum, 2000, 1, Color.blue, b);
            System.out.println(p.getZitplaatsen());
            System.out.println(p);
//            System.out.println();
//            p.setBestuurder(b);
//            System.out.println(p.isIngezetene(a));
//            System.out.println(p.isIngezetene(b));
//            System.out.println(p.getIngezetenen().size());
//            
//            System.out.println(p);
//            for (Voertuig voertuig : lijst){
//                System.out.println(voertuig.getMerk() + ": " + voertuig.getIngezetenen());
//            }
            
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        } catch (MensException e){
            System.err.println(e.getMessage());
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
