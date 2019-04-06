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
 *
 * @author ASUS
 */
public class LectorArhivoTextoPlano implements Lector{
    @Override
    public ArrayList leerArchivo() throws IOException {
        RandomAccessFile lector = new RandomAccessFile("instrucciones.txt", "r");
        ArrayList<String> instrucciones = new ArrayList<>();
        String linea = null;
        while((linea = lector.readLine()) != null){
            instrucciones.add(linea);
        }
        return instrucciones;
    }
    
}
