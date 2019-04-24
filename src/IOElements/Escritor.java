/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Interface en la cual se determinan los métodos para escribir en los archivos de texto.
 * @author Daniel Gutiérrez 
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public interface Escritor {
    static Charset charset = Charset.forName("UTF-8");
    public abstract void escribir(Path archivoDestino, ArrayList variablesGuardadas) throws IOException;
}
