/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author dgutierrezd
 */
public class ExcedeLimiteInstruccionException extends RuntimeException {
    public ExcedeLimiteInstruccionException(String mensaje) {
        super(mensaje);
    }
    
    public ExcedeLimiteInstruccionException() {
        
    }
}
