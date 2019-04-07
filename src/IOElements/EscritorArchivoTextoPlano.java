/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IOElements;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author ASUS
 */
public class EscritorArchivoTextoPlano implements Escritor{

    @Override
    public void escribir(String archivoDestino , String variable, float valor) throws IOException {
        RandomAccessFile escritor = new RandomAccessFile(archivoDestino, "rw");
        
        escritor.writeChars(variable + " = "+valor+"\n");
        
        escritor.close();
    }
    
}
