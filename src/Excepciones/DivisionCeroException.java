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
public class DivisionCeroException extends ArithmeticException {
    public DivisionCeroException(String mensaje) {
        super(mensaje);
    }
    
    public DivisionCeroException() {
        
    }
}
