/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Elementos.Instruccion;
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

/**
 *
 * @author ASUS
 */
public class Interprete {
    private Lector lector;
    private Escritor escritor;
    private Instruccion instruccion;
    private HashMap<String,Float> variables = new HashMap<>();
    private static String archivoInstrucciones = "instrucciones.txt";
    private static String archivoDatos = "archivoDestino.txt";
    
    
    public Interprete() throws IOException {
        lector = new LectorArhivoTextoPlano();
        escritor = new EscritorArchivoTextoPlano();
        instruccion = new Instruccion();
        
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
        determinarInstruccion();
    }
    
    public void determinarInstruccion() throws IOException, NullPointerException, InstruccionIncorrectaException, VariableGuardadaException{
        ArrayList instrucciones = lector.leerArchivo(archivoInstrucciones);
        if(instrucciones.isEmpty()) {
            throw new ArchivoVacioException();
        }
        for (int i = 0; i < instrucciones.size(); i++) {
            String instruccionTemporal = instrucciones.get(i).toString();
            if(instruccionTemporal.equals("")){
                continue;
            }
            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                instruccion.asignar(arregloTemporalInstrucciones[0],Float.parseFloat(arregloTemporalInstrucciones[2]),variables);
            }else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){
                instruccion.determinarAsignacionCompuesta(arregloTemporalInstrucciones,variables);
            }else{
                String opcion = arregloTemporalInstrucciones[0];
                instruccion.determinarInstruccionSimple(opcion, arregloTemporalInstrucciones,variables);
            }
        }
    }    
}
