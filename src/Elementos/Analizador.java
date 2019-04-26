/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Excepciones.ArchivoVacioException;
import Excepciones.DivisionCeroException;
import Excepciones.ExcedeLimiteInstruccionException;
import Excepciones.InstruccionIncorrectaException;
import Excepciones.ValorIncorrectoException;
import Excepciones.ValorVariableIgualesException;
import Excepciones.VariableGuardadaException;
import Excepciones.VariablesAlfabeticasException;
import IOElements.Lector;
import java.io.IOException;
import static java.lang.Float.parseFloat;
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
    
    private InstruccionAsignar asignacion;
    
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
            String resultadoPedir = JOptionPane.showInputDialog(("Ingresa el valor para " + variable + ":").trim());
            
            if(resultadoPedir != null){
                double resultado = Double.parseDouble(resultadoPedir);
                asignacion = new AsignacionSimple(variable, resultado);
                asignacion.asignar(variables);
            } else {
                System.out.println("Has presionado cancelar!!");
            }
            
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
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables, int cont)  throws NumberFormatException, InstruccionIncorrectaException, NullPointerException, VariableGuardadaException{
        switch(opcion){
            case "mostrar":
                try {
                    Instruccion mostrar = new InstruccionMostrar();
                    mostrar.ejecutar(variables, arregloTemporalInstrucciones[1], opcion, variablesGuardadas);
                } catch (NullPointerException np) {
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+ 0);
                }
            break;

            case "pedir":
                Instruccion pedir = new InstruccionPedir();
                pedir.ejecutar(variables, arregloTemporalInstrucciones[1], opcion, variablesGuardadas);
            break;

            case "guardar":
                if(variablesGuardadas.contains(arregloTemporalInstrucciones[1])) {
                    throw new VariableGuardadaException("La variable " + arregloTemporalInstrucciones[1] + " ya está guardada, deseas reescribir su valor?");
                } else {
                    Instruccion guardar = new InstruccionGuardar();
                    guardar.ejecutar(variables, arregloTemporalInstrucciones[1], opcion, variablesGuardadas);                        
                    System.out.println(variablesGuardadas);
                }
            break;

            case "leer":
                if(!variables.containsKey(arregloTemporalInstrucciones[1])) {
                    asignacion = new AsignacionSimple(arregloTemporalInstrucciones[1], 0);
                    asignacion.asignar(variables);
                }
                Instruccion leer = new InstruccionLeerEn();
                leer.ejecutar(variables, arregloTemporalInstrucciones[1], arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1], variablesGuardadas);
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
        String linea = "";
        try {
            double resultado = mostrar(variable, variables);
            linea = "" + variable + " = " + resultado;
        } catch(NullPointerException npe) {
            linea = "" + variable + " = " + 0;
        }
        
        return linea;
    }

    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }
    
    public void determinarValorVariableIgualesException(String valor,String variable, int cont) throws ValorVariableIgualesException {
        if(valor.equals(variable))
            throw new ValorVariableIgualesException(cont + "");
    }
    
    /**
     * Se determinan las instrucciones para conocer el valor de algunas variables.
     * @param instrucciones ArrayList para conocer las instrucciones.
     */
    public void determinarInstruccionesNuevas(ArrayList instrucciones) throws ValorVariableIgualesException ,DivisionCeroException, VariablesAlfabeticasException,ArrayIndexOutOfBoundsException, ExcedeLimiteInstruccionException{
        int cont = 0;
        for (int i = 0; i < instrucciones.size(); i++) {
            cont = cont + 1;
            String instruccionTemporal = instrucciones.get(i).toString();
            if(instruccionTemporal.equals("")){
                continue;
            }
            
            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
            if((arregloTemporalInstrucciones.length == 1)){
                throw new ArrayIndexOutOfBoundsException(""+cont);
            }
            
            if(!arregloTemporalInstrucciones[0].matches("[A-Za-z0-9]+"))
                throw new VariablesAlfabeticasException("'"+arregloTemporalInstrucciones[0]+"'" + " no es una variable válida. Debes ingresar una letra.\n *Linea: " + cont );
            
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                determinarValorVariableIgualesException(arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[2], cont);
                
                asignacion = new AsignacionSimple(arregloTemporalInstrucciones[0],Double.parseDouble(arregloTemporalInstrucciones[2]));
                asignacion.asignar(variables);
            } else if(arregloTemporalInstrucciones.length > 5) {
                throw new ExcedeLimiteInstruccionException(cont + "");
            } else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){

                determinarValorVariableIgualesException(arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[2], cont);
                determinarValorVariableIgualesException(arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[4], cont);
                
                if(!variables.containsKey(arregloTemporalInstrucciones[2])) {
                    try {
                        Float.parseFloat(arregloTemporalInstrucciones[2]);
                    } catch(NumberFormatException nfe) {
                        asignacion = new AsignacionSimple(arregloTemporalInstrucciones[2],0);
                        asignacion.asignar(variables);
                    }
                } else if(!variables.containsKey(arregloTemporalInstrucciones[4])) {
                    try {
                        Float.parseFloat(arregloTemporalInstrucciones[4]);
                    } catch(NumberFormatException nfe) {
                        asignacion = new AsignacionSimple(arregloTemporalInstrucciones[4],0);
                        asignacion.asignar(variables);
                    }

                }
                try {
                    asignacion = new AsignacionCompuesta();
                    asignacion.asignar(arregloTemporalInstrucciones, variables);
                } catch(DivisionCeroException dce) {
                    throw new DivisionCeroException(cont + "");
                }
            } else {
                String opcion = arregloTemporalInstrucciones[0];
                determinarInstruccionSimple(opcion, arregloTemporalInstrucciones,variables, cont);

            } 
        } 
    }
    
    /**
     * Se leen las variables que se han guardado.
     * @param variablesGuardadas ArrayList con las varuables guardadas.
     */
    public void leerVariablesGuardadas(ArrayList<String> variablesEnTexto) {
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
                InstruccionAsignar nuevaAsignacion = new AsignacionSimple(variable, valor);
                nuevaAsignacion.asignar(variables);
                System.out.println(variables);
        }
    }
    
}
