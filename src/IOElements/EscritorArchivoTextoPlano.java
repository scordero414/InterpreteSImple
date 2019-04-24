/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Se escribe el archivo de texto.
 * @author Daniel Gutiérrez 
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class EscritorArchivoTextoPlano implements Escritor{

    
    @Override
    public void escribir(Path archivoDestino , ArrayList variablesGuardadas) throws IOException {
       BufferedWriter writer = Files.newBufferedWriter(archivoDestino, charset);
        String result="";
        for(int i=0 ; i<variablesGuardadas.size() ; i++){
                result += variablesGuardadas.get(i) + "\r\n";
                writer.write(result, 0, result.length());
        }
        writer.close();
    }
}
