/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.util.mens;

import static be.vdab.util.mens.Rijbewijs.*; 
/**
 *
 * @author Vinnie
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        try{
            Mens a = new Mens("Bart", B, BE, C, CE);
            System.out.println(a);
            System.out.println(a.equals(a));
        
        }catch (Exception e){
            System.out.println(e.toString());
        }
        
    }
    
}
