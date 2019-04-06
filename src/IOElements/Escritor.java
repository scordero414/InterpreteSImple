/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;

/**
 *
 * @author ASUS
 */
public interface Escritor {
    public abstract void escribir(String archivoDestino, String variable, float valor) throws IOException;
}
