/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Elementos.Analizador;
import Elementos.ControladorIO;
import Elementos.Operacion;
import Excepciones.ArchivoVacioException;
import Excepciones.InstruccionIncorrectaException;
import Excepciones.ValorIncorrectoException;
import Excepciones.VariableGuardadaException;
import IOElements.Escritor;
import IOElements.EscritorArchivoTextoPlano;
import IOElements.Lector;
import IOElements.LectorArhivoTextoPlano;
import Vistas.VistaPrincipal;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import jdk.nashorn.internal.objects.NativeArray;

/**
 * Clase control, donde se crea la interpretación de los archivos de texto y
 * se define la acción de vistas y demás clases.
 * @author Daniel Gutiérrez 
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class Interprete {
    private Analizador analizador;
    private ControladorIO controladorIO;
    
    public Interprete() throws IOException {   
        controladorIO = new ControladorIO();
        analizador = new Analizador();        
    }
        
    public static void main(String[] args) throws IOException{
        Interprete interprete = new Interprete();
        VistaPrincipal vistaPrincipal = new VistaPrincipal(interprete);        
        vistaPrincipal.setResizable(false);        
    }
    
    /**
     * Se determinan las opciones ó estado de la vista principal, éste estado se
     * determina dependiendo del botón seleccionado.
     * @param opcion Botón seleccionado en la vista.
     * @throws IOException 
     */
    public void determinarOpcionesVista(int opcion) throws IOException{
        switch(opcion){
            case 1:
                iniciarInterprete();
            break;
            case 2:
                controladorIO.abrirTxt(controladorIO.getArchivoInstrucciones());
            break;
            case 3:
                System.exit(0);
            break;
            case 4:
                controladorIO.abrirTxt(controladorIO.getArchivoDatos());
            break;
        }
    }
    
    
    
    /**
     * Se ejecuta y se lee el archivo de texto.
     * @throws IOException 
     */
    public void iniciarInterprete() throws IOException{
        analizador.iniciar(controladorIO.getLector(),controladorIO.getArchivoInstrucciones(),controladorIO.getArchivoDatos());
        controladorIO.escribirVariablesGuardadas(analizador);
    }
    
    
    
    
}
