/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import IOElements.Escritor;
import IOElements.EscritorArchivoTextoPlano;
import IOElements.Lector;
import IOElements.LectorArhivoTextoPlano;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ASUS
 */
public class ControladorIO {
    private Lector lector;
    private Escritor escritor;
    private String archivoInstrucciones = "instrucciones.txt";
    private String archivoDatos = "archivoDestino.txt";
    
    public ControladorIO(){
        lector = new LectorArhivoTextoPlano();
        escritor = new EscritorArchivoTextoPlano();
    }
    
    /**
     * Se abre un archivo de texto.
     * @param archivo Ruta del archivo de texto plano.
     */
    public void abrirTxt(String archivo){
        try {
            File objectTxt = new File(archivo);
            Desktop.getDesktop().open(objectTxt);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    /**
     * Las variables que se van guardando, se irán añadiendo al archivo de texto.
     * @throws IOException 
     */
    public void escribirVariablesGuardadas(Analizador analizador) throws IOException{
        escritor.escribir(archivoDatos, analizador.getVariablesGuardadas());
        analizador.getVariablesGuardadas().clear();
    }
    
    public Lector getLector() {
        return lector;
    }

    public Escritor getEscritor() {
        return escritor;
    }

    public String getArchivoInstrucciones() {
        return archivoInstrucciones;
    }

    public String getArchivoDatos() {
        return archivoDatos;
    }
    
}
