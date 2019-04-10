/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Elementos.Analizador;
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
 *
 * @author ASUS
 */
public class Interprete {
    private Lector lector;
    private Escritor escritor;
    private Analizador analizador;
    
    private static String archivoInstrucciones = "instrucciones.txt";
    private static String archivoDatos = "archivoDestino.txt";
    
    
    public Interprete() throws IOException {
        lector = new LectorArhivoTextoPlano();
        escritor = new EscritorArchivoTextoPlano();
        analizador = new Analizador();
        
    }
        
    public static void main(String[] args) throws IOException{
        Interprete interprete = new Interprete();
        VistaPrincipal vistaPrincipal = new VistaPrincipal(interprete);        
        vistaPrincipal.setResizable(false);        
    }
    
    public void determinarOpcionesVista(int opcion) throws IOException{
        switch(opcion){
            case 1:
                iniciarInterprete();
            break;
            case 2:
                abrirTxt(archivoInstrucciones);
            break;
            case 3:
                System.exit(0);
            break;
            case 4:
                abrirTxt(archivoDatos);
            break;
        }
    }
    
    /**
     * Se abre un archivo de texto.
     * @param archivo 
     */
    public void abrirTxt(String archivo){
        try {
            File objectTxt = new File(archivo);
            Desktop.getDesktop().open(objectTxt);
        }catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void iniciarInterprete() throws IOException{
        analizador.iniciar(lector,archivoInstrucciones);
        escribirVariablesGuardadas();
    }
    
    public void escribirVariablesGuardadas() throws IOException{
        escritor.escribir(archivoDatos, analizador.getVariablesGuardadas());
        analizador.getVariablesGuardadas().clear();
    }
    
}
