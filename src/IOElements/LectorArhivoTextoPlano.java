/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;
import java.io.RandomAccessFile;
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
    public ArrayList<String> leerArchivo(String archivo) throws IOException {
        RandomAccessFile lector = new RandomAccessFile(archivo, "r");
        ArrayList<String> instrucciones = new ArrayList<>();
        String linea = null;
        while((linea = lector.readLine()) != null){
            instrucciones.add(linea);
        }
        lector.close();
        return instrucciones;
    }
    
}
