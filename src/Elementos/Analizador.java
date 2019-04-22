/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Excepciones.ArchivoVacioException;
import Excepciones.InstruccionIncorrectaException;
import Excepciones.ValorIncorrectoException;
import Excepciones.VariableGuardadaException;
import IOElements.Lector;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 * Se conocen las instrucciones y se determinan sus funciones.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class Analizador {
    
    private Asignacion asignacion;
    
    /**
     * Las variables que se van asignando se guardan en este Hashmap.
     */
    private HashMap<String,Float> variables = new HashMap<>();
    
    /**
     * Las variables guardadas, en otro archivo de texto. Se añaden a este ArrayList.
     */
    private ArrayList<String> variablesGuardadas = new ArrayList<>();
    
    /**
     * Se obtiene y se muestra el valor de la variable que se pide 'mostrar'.
     * @param variablel variable a la cual se quiere mostrar el valor. 
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @return 
     */
    public float mostrar(String variable,HashMap variables){
        return (float) variables.get(variable);
    }
    
    /**
     * Se le asigna el valor de una varibale, en otra variable aun no creada.
     * @param variable1 variable original
     * @param variable2 variable a la cual se le quiere transferir el valor.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     */
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    /**
     * Se crea una varibale, y se le asigna un valor. Por medio de un inputDialog.
     * @param variable a la cual necesitamis darle un valor.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @throws NumberFormatException 
     */
    public void pedir(String variable, HashMap variables) throws NumberFormatException {
        try {
            float resultadoPedir = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para " + variable + ":").trim());                
            asignacion = new AsignacionSimple(variable, resultadoPedir);
            asignacion.asignar(variables);
        } catch(NumberFormatException nfe) {
            pedir(variable, variables);
        }
        
    }
    
    /**
     * Conociendo las instrucciones pedidas en el archivo de texto, se determina su función.
     * @param opcion instruccion que tiene caada linea.
     * @param arregloTemporalInstrucciones  tiene guardada en cada posicion, una palabra de la linea, con la cual se va a trabajar.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @throws NumberFormatException
     * @throws InstruccionIncorrectaException
     * @throws NullPointerException
     * @throws VariableGuardadaException 
     */
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables) throws NumberFormatException, InstruccionIncorrectaException, NullPointerException, VariableGuardadaException, ArrayIndexOutOfBoundsException{
        switch(opcion){
            case "mostrar":
                try {
                    float resultado = mostrar(arregloTemporalInstrucciones[1], variables);
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+resultado);
                } catch (NullPointerException np) {
                    throw new NullPointerException("La variable '" + arregloTemporalInstrucciones[1] + "' no existe.");
                }
                    
            break;

            case "pedir":
                pedir(arregloTemporalInstrucciones[1], variables);
            break;

            case "guardar":
                if(variablesGuardadas.contains(arregloTemporalInstrucciones[1])) {
                    throw new VariableGuardadaException("La variable " + arregloTemporalInstrucciones[1] + " ya está guardada, deseas reescribir su valor?");
                } else {
                    variablesGuardadas.add(guardar(arregloTemporalInstrucciones[1], variables));                
                    System.out.println(variablesGuardadas);
                }
                
            break;

            case "leer":
                leerEn(arregloTemporalInstrucciones[1], arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1],variables);
            break;
            default:
                throw new InstruccionIncorrectaException("La instrucción '" + opcion + "' no es correcta."); 
        }
    }
    
    /**
     * Se determina la operación para el valor de la variable.
     * @param lector 
     * @param archivoInstrucciones Ruta archivo de texto plano, donde se ingresan las instrucciones.
     * @param archivoDatos Ruta archivo de texto plano, donde se almacenan las variables guardadas.
     * @throws IOException
     * @throws NullPointerException
     * @throws InstruccionIncorrectaException
     * @throws VariableGuardadaException 
     */
    public void determinarInstruccion(Lector lector,String archivoInstrucciones,String archivoDatos) throws IOException, NullPointerException, InstruccionIncorrectaException, VariableGuardadaException{
        ArrayList instrucciones = lector.leerArchivo(archivoInstrucciones);
        ArrayList nuevasVariables = lector.leerArchivo(archivoDatos);
        leerVariablesGuardadas(nuevasVariables);
        if(instrucciones.isEmpty()) {
            throw new ArchivoVacioException();
        }
        //leerVariablesGuardadas(variablesGuardadas);
        determinarInstruccionesNuevas(instrucciones);
        
    }    

    public void iniciar(Lector lector, String archivoInstrucciones, String archivoDatos) throws IOException {
        determinarInstruccion(lector, archivoInstrucciones,archivoDatos);
    }
    
    /**
     * Se guardan las variables pedidas en un archivo de texto predeterminado.
     * @param variable variable a la cual se quiere guardar.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @return 
     */
    public String guardar(String variable,HashMap variables){
        int resultado = (int) mostrar(variable, variables);
        String linea =""+variable+" = "+resultado;
        return linea;
    }

    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }
    
    /**
     * Se determinan las instrucciones para conocer el valor de algunas variables.
     * @param instrucciones ArrayList para conocer las instrucciones.
     */
    public void determinarInstruccionesNuevas(ArrayList instrucciones){
        for (int i = 0; i < instrucciones.size(); i++) {
            String instruccionTemporal = instrucciones.get(i).toString();
            if(instruccionTemporal.equals("")){
                continue;
            }
            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                asignacion = new AsignacionSimple(arregloTemporalInstrucciones[0],Float.parseFloat(arregloTemporalInstrucciones[2]));
                asignacion.asignar(variables);
            }else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){
                asignacion = new AsignacionCompuesta();
                asignacion.asignar(arregloTemporalInstrucciones, variables);
            }else{
                String opcion = arregloTemporalInstrucciones[0];
                determinarInstruccionSimple(opcion, arregloTemporalInstrucciones,variables);
            } 
        } 
    }
    
    /**
     * Se leen las variables que se han guardado.
     * @param variablesGuardadas ArrayList con las varuables guardadas.
     */
//    public void leerVariablesGuardadas(ArrayList variablesGuardadas){
//        for (int i = 0; i < variablesGuardadas.size(); i++) {
//            String instruccionTemporal = variablesGuardadas.get(i).toString();
//            if(instruccionTemporal.equals("")){
//                continue;
//            }
//            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
//                asignacion = new AsignacionSimple(arregloTemporalInstrucciones[0],Float.parseFloat(arregloTemporalInstrucciones[0]));
//                asignacion.asignar(variables);
//        } 
//    }
    
    public void leerVariablesGuardadas(ArrayList variablesEnTexto){
        
        for (int i = 0; i < variablesEnTexto.size(); i++) {
            if(variablesEnTexto.isEmpty()){
                break;
            }else{
                String line = (String) variablesEnTexto.get(i);
                String[] linea  = line.split(" ");
                String variable = linea[0];
                System.out.println(linea[2]);
                Float valor = Float.parseFloat(linea[2]);
                Asignacion nuevaAsignacion = new AsignacionSimple(variable, valor);
                nuevaAsignacion.asignar(variables);
                System.out.println(variables);
            }
        }
    }
    
}
