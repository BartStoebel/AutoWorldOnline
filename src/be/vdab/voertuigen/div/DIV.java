/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.voertuigen.div;

/**
 *
 * @author Vinnie
 */
public enum DIV {
    INSTANCE;
    
    private static int plaatCijfer = 1;
    
    private DIV(){
        
    }
     /**
     * Creëer een nieuwe nummperplaat en verhoog het cijfer van de nummerplaat 
     * met 1
     * @return nieuwePlaat Nummerplaat object
     */        
    public static Nummerplaat getNummerplaat(){
        Nummerplaat nieuwePlaat = new Nummerplaat(maakPlaatOpschrift());
        if (plaatCijfer <999){
            plaatCijfer +=1; 
        } else {
            plaatCijfer = 1;
        }
        return nieuwePlaat;
    }
    private static String maakPlaatOpschrift(){
        String plaat = "AAA";
        String cijfer = String.valueOf(plaatCijfer);
        if(cijfer.length() == 1){
            plaat = plaat + "00" + cijfer;
        }else if (cijfer.length() == 2){
            plaat = plaat + "0" + cijfer;
        }else {
            plaat = plaat + cijfer;
        }
        return plaat;
    }
}
