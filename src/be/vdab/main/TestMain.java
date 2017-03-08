/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be.vdab.main;

import be.vdab.util.Datum;
import be.vdab.util.DatumException;
import be.vdab.util.Maat;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import static be.vdab.util.mens.Rijbewijs.*; 
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;
/**
 *
 * @author Vinnie
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Creëer eerst de nodige objecten:
        Datum datum;
        Mens bart = new Mens("Bart", B, BE, C, CE);
        Mens frank = new Mens("Frank",A, B);
        Mens ruben = new Mens("Ruben");
        Mens kjell = new Mens("Kjell", Rijbewijs.A, Rijbewijs.B, Rijbewijs.BE, Rijbewijs.C);
        Volume volume = new Volume(2, 2, 10, Maat.meter);
        Volume volumeGroot = new Volume(2, 2, 20, Maat.meter);
        Volume volumeKlein = new Volume(1,2,4,Maat.meter);
        
        try{
            datum = new Datum(2, 2, 2017);
            Voertuig auto1 = new Personenwagen("Honda", datum, 25000, 5, Color.blue, bart, frank, ruben);
            Voertuig auto2 = new Personenwagen("Mercedes", datum, 32000, 5, Color.blue, frank);
            Voertuig camion1 = new Vrachtwagen("Mercedes", datum, 40000, 3, volume, 2000, 4, bart, kjell, ruben);
            Voertuig camion2 = new Vrachtwagen("Scania", datum, 45000, 2, volumeGroot, 20000, 6, kjell);
            Voertuig pickup1 = new Pickup("Mazda", datum, 35000, 3, Color.BLACK, volumeKlein, frank);
            Voertuig pickup2 = new Pickup("Land Rover", datum, 42000, 2, Color.GRAY, volumeKlein, kjell, ruben);
            //Vul de eerste Set
            Set<Voertuig> sortedSet = new TreeSet<>();  //automatisch genummerd naar nummerplaat via CompareTo
            sortedSet.add(camion1);
            sortedSet.add(auto2);
            sortedSet.add(auto1);
            sortedSet.add(camion2);
            sortedSet.add(pickup1);
            sortedSet.add(pickup2);
            System.out.println("//Gesorteerde voertuigen naar nummerplaat:");
            sortedSet.stream().forEach(e->System.out.println(e));
            System.out.println();
            System.out.println("//Gesorteerde voertuigen naar aankoopprijs:");
            
            //Vul de 2e Set:
            Set<Voertuig> setAankoopprijs = new TreeSet<>(Voertuig.getAankoopprijsComparator().reversed()); 
            setAankoopprijs.addAll(sortedSet);
            //Dit lijkt niet te werken, maar weet niet waarom:
            /*
            setAankopprijs = sortedSet.stream()
                    .sorted((e1,e2)-> - (e1.getAankoopprijs() - (e2.getAankoopprijs()))) //dit lijkt niet te werken
                    .collect(Collectors.toSet());*/
            //Sorteer en toon:
            setAankoopprijs.stream()
                    .forEach(e->System.out.println(e));
            
            //vul de 3e Set:
            System.out.println();
            System.out.println("//Gesorteerde voertuigen naar merk:");
            Set<Voertuig> setMerk = new TreeSet<>(Voertuig.getMerkComparator());
            setMerk.addAll(sortedSet);
            setMerk.stream().forEach(e->System.out.println(e));
            
            File file = new File("wagenpark.txt");
            try(ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(file));){
                oos.writeObject(setMerk);
            }  catch (IOException e){
                System.err.println(e.getMessage());
            }
            
//            Write write = new Write(setMerk);
//            Thread thread = new Thread(write);
//            //thread.sleep(4000);
//            thread.run();
            
           
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));){
                Set<Voertuig> setMerk2 = (Set<Voertuig>)ois.readObject();
                System.out.println();
                System.out.println("// Na import ...");
                setMerk2.stream().forEach(e->System.out.println(e));
            }
            

           
            
            
        } catch (IllegalArgumentException e){
            System.err.println(e.getMessage());
        } catch (MensException e){
            System.err.println(e.getMessage());
        }  catch (IOException e){
            System.err.println(e.getMessage());
        } catch (DatumException e){
            System.err.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.toString());
        }
    }
    
    
    
}
 class Write implements Runnable{
    
    Set <Voertuig> setMerk;
    
    public Write(Set<Voertuig> setMerk){
        this.setMerk = setMerk;
    }

    public void run (){
        File file = new File("wagenpark.txt");
            try(ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(file));){
                oos.writeObject(setMerk);
            }  catch (IOException e){
                System.err.println(e.getMessage());
            }
    }
    
}
