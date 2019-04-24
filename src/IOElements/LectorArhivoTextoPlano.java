/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.annotation.processing.FilerException;

/**
 * Se lee el archivo de texto.
 * @author Daniel Gutiérrez 
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class LectorArhivoTextoPlano implements Lector{
    
    @Override
    public ArrayList<String> leerArchivo(Path archivo) throws IOException {
        
        BufferedReader reader = Files.newBufferedReader(archivo, charset);
        String line = null;
        ArrayList<String> instrucciones = new ArrayList<>();
        int index = 0;
        while ((line = reader.readLine()) != null) {
                instrucciones.add(line);
                index ++;
        }
        reader.close();
        return instrucciones;
    }
    
}
