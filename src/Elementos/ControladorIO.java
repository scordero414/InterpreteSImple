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
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ASUS
 */
public class ControladorIO {
    private Lector lector;
    private Escritor escritor;
    private Path archivoDatos = Paths.get("archivoDestino.txt");
    private Path archivoInstrucciones = Paths.get("archivoInstrucciones.txt");
    private File rutaArchivoDatos = new File("archivoDestino.txt");
    private File rutaArchivoInstrucciones = new File("archivoInstrucciones.txt");
    private GestorDeVariables gestorDeVariables;
    
    public ControladorIO(GestorDeVariables gestorDeVariables){
        lector = new LectorArhivoTextoPlano();
        escritor = new EscritorArchivoTextoPlano();
        this.gestorDeVariables = gestorDeVariables;
        
    }
    
    /**
     * Se abre un archivo de texto.
     * @param objectTxt Archivo ya creado, listo para ser abierto.
     */
    public void abrirTxt(File objectTxt){
        try {
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
    public void limpiarHashMap(Analizador analizador){
        analizador.getVariables().clear();
    }
    
    public Lector getLector() {
        return lector;
    }

    public Escritor getEscritor() {
        return escritor;
    }

    public Path getArchivoDatos() {
        return archivoDatos;
    }

    public Path getArchivoInstrucciones() {
        return archivoInstrucciones;
    }

    public File getRutaArchivoDatos() {
        return rutaArchivoDatos;
    }

    public File getRutaArchivoInstrucciones() {
        return rutaArchivoInstrucciones;
    }

    
    
}
