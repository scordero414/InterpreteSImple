/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author ASUS
 */
public interface Lector {
    public abstract ArrayList leerArchivo(String archivo) throws IOException;
}
