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
public class ArchivoVacioException extends RuntimeException {
    
    public ArchivoVacioException(String mensaje) {
        super(mensaje);
    }
    
    public ArchivoVacioException() {
        
    }
}
