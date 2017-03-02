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
public class DatumException extends java.lang.Exception{
    private String error;
    
    public DatumException(){
        
    }
    public DatumException(String message){
        super(message);
    }
    public DatumException(String message, Throwable cause){
        super(message, cause);
    }
    public DatumException(Throwable cause){
        super(cause);
    } 
    
}
