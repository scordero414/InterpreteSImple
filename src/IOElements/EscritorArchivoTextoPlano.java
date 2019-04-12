/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;
import java.io.RandomAccessFile;
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
    public void escribir(String archivoDestino , ArrayList variablesGuardadas) throws IOException {
        RandomAccessFile escritor = new RandomAccessFile(archivoDestino, "rw");
        for(int i=0 ; i<variablesGuardadas.size() ; i++){
            escritor.writeChars((String) variablesGuardadas.get(i)+"\n");
        }
        escritor.close();
    }
    
}
