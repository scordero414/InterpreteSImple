/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import IOElements.Lector;
import IOElements.LectorArhivoTextoPlano;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class Interprete {
    private Lector lector;

    public Interprete() {
        lector = new LectorArhivoTextoPlano();
    }
    
    HashMap<String,Float> variables = new HashMap<>();
    public static void main(String[] args) throws IOException{
        Interprete interprete = new Interprete();
        interprete.mostrarInstrucciones();
    }
    public void mostrarInstrucciones() throws IOException{
        ArrayList arregloInstrucciones = lector.leerArchivo();
        for (int i = 0; i < arregloInstrucciones.size(); i++) {
            System.out.println(arregloInstrucciones.get(i));            
        }
    }
}
