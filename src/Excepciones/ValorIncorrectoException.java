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
public class ValorIncorrectoException extends RuntimeException {
    
    public ValorIncorrectoException(String mensaje) {
        super(mensaje);
    }
    
}
