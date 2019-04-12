/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 * Excepción para definir que el archivo cargado está vacío.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190407
 * @since 1.0
 */
public class ArchivoVacioException extends RuntimeException {
    
    public ArchivoVacioException(String mensaje) {
        super(mensaje);
    }
    
    public ArchivoVacioException() {
        
    }
}
