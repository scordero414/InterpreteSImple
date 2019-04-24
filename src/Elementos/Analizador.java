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
import java.nio.file.Path;
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
    private HashMap<String,Double> variables = new HashMap<>();
    
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
    public double mostrar(String variable,HashMap variables){
        return (double) variables.get(variable);
    }
    
    /**
     * Se le asigna el valor de una varibale, en otra variable aun no creada.
     * @param variable1 variable original
     * @param variable2 variable a la cual se le quiere transferir el valor.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     */
    public void leerEn(String variable1,String variable2, HashMap variables){
        double valor  = (double) variables.get(variable1);
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
            double resultadoPedir = Double.parseDouble(JOptionPane.showInputDialog("Ingresa el valor para " + variable + ":").trim());                
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
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables, int cont) throws NumberFormatException, InstruccionIncorrectaException, NullPointerException, VariableGuardadaException{
        switch(opcion){
            case "mostrar":
                try {
                    double resultado = mostrar(arregloTemporalInstrucciones[1], variables);
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+resultado);
                } catch (NullPointerException np) {
                    throw new NullPointerException("La variable '" + arregloTemporalInstrucciones[1] + "' no existe. \n"
                                                 + "*Linea: "+ cont);
                }
            break;

            case "pedir":
                pedir(arregloTemporalInstrucciones[1], variables);
            break;

            case "guardar":
                try {
                    if(variablesGuardadas.contains(arregloTemporalInstrucciones[1])) {
                        throw new VariableGuardadaException("La variable " + arregloTemporalInstrucciones[1] + " ya está guardada, deseas reescribir su valor?");
                    } else {
                        variablesGuardadas.add(guardar(arregloTemporalInstrucciones[1], variables));                
                        System.out.println(variablesGuardadas);
                    }
                } catch (NullPointerException np) {
                    throw new NullPointerException("La variable '" + arregloTemporalInstrucciones[1] + "' no existe.\n"
                                                 + "*Linea: "+ cont);
                }
                
                
            break;

            case "leer":
                leerEn(arregloTemporalInstrucciones[1], arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1],variables);
            break;
            default:
                throw new InstruccionIncorrectaException("La instrucción '" + opcion + "' no es correcta.\n"
                                                 + "*Linea: "+ cont);
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
    public void determinarInstruccion(Lector lector,Path archivoInstrucciones,Path archivoDatos) throws IOException, NullPointerException, InstruccionIncorrectaException, VariableGuardadaException,ArrayIndexOutOfBoundsException{
        ArrayList<String> instrucciones = lector.leerArchivo(archivoInstrucciones);
        ArrayList<String> nuevasVariables = lector.leerArchivo(archivoDatos);
        leerVariablesGuardadas(nuevasVariables);
        if(instrucciones.isEmpty()) {
            throw new ArchivoVacioException();
        }
        determinarInstruccionesNuevas(instrucciones);
        
    }    

    public void iniciar(Lector lector, Path archivoInstrucciones, Path archivoDatos) throws IOException,ArrayIndexOutOfBoundsException {
        determinarInstruccion(lector, archivoInstrucciones,archivoDatos);
    }
    
    /**
     * Se guardan las variables pedidas en un archivo de texto predeterminado.
     * @param variable variable a la cual se quiere guardar.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @return 
     */
    public String guardar(String variable,HashMap variables){
        double resultado = mostrar(variable, variables);
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
    public void determinarInstruccionesNuevas(ArrayList instrucciones)throws ArrayIndexOutOfBoundsException{
        int cont = 0;
        for (int i = 0; i < instrucciones.size(); i++) {
            cont = cont + 1;
            String instruccionTemporal = instrucciones.get(i).toString();
            if(instruccionTemporal.equals("")){
                continue;
            }
            if((instruccionTemporal.length()==1)){
                throw new ArrayIndexOutOfBoundsException("Instruccion incorrecta.\n *Linea: "+cont);
            }
            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                asignacion = new AsignacionSimple(arregloTemporalInstrucciones[0],Double.parseDouble(arregloTemporalInstrucciones[2]));
                asignacion.asignar(variables);
            }else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){
                asignacion = new AsignacionCompuesta();
                asignacion.asignar(arregloTemporalInstrucciones, variables);
            }else{
                String opcion = arregloTemporalInstrucciones[0];
                determinarInstruccionSimple(opcion, arregloTemporalInstrucciones,variables, cont);
            } 
        } 
    }
    
    /**
     * Se leen las variables que se han guardado.
     * @param variablesGuardadas ArrayList con las varuables guardadas.
     */
    public void leerVariablesGuardadas(ArrayList<String> variablesEnTexto){
        for (int i = 0; i < variablesEnTexto.size(); i++) {
            String line = variablesEnTexto.get(i).toString();
            if(variablesEnTexto.isEmpty()){
                break;
            }else
            if(line.equals("")){
                continue;
            }
                System.out.println(line);
                String[] linea  = line.split(" ");
                String variable = linea[0];
                double valor = Double.parseDouble(linea[linea.length-1]);
                Asignacion nuevaAsignacion = new AsignacionSimple(variable, valor);
                nuevaAsignacion.asignar(variables);
                System.out.println(variables);
        }
    }
    
}
