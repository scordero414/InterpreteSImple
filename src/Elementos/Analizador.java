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
    
    /**
     * Las variables que se van asignando se guardan en este Hashmap.
     */
    private HashMap<String,Double> variables = new HashMap<>();
    
    /**
     * Las variables guardadas, en otro archivo de texto. Se añaden a este ArrayList.
     */
    private ArrayList<String> variablesGuardadas = new ArrayList<>();
    
    
    public void iniciar(Lector lector, Path archivoInstrucciones, Path archivoDatos) throws IOException,ArrayIndexOutOfBoundsException {
        determinarInstruccion(lector, archivoInstrucciones,archivoDatos);
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
            
            if(!arregloTemporalInstrucciones[0].matches("^[a-zA-Z0-9]*$"))
                throw new VariablesAlfabeticasException("'"+arregloTemporalInstrucciones[0]+"'" + " no es una variable válida. Debes ingresar una letra.\n *Linea: " + cont );
            
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                determinarValorVariableIgualesException(variables, arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[2], cont);
                InstruccionAsignar asignacion = new AsignacionSimple();
                asignacion.ejecutar(variables, arregloTemporalInstrucciones[0],Double.parseDouble(arregloTemporalInstrucciones[2]),0, null, null, null);
            } else if(arregloTemporalInstrucciones.length > 5) {
                throw new ExcedeLimiteInstruccionException(cont + "");
            } else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){

                determinarValorVariableIgualesException(variables, arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[2], cont);
                determinarValorVariableIgualesException(variables, arregloTemporalInstrucciones[0], arregloTemporalInstrucciones[4], cont);
                
                if(!variables.containsKey(arregloTemporalInstrucciones[2])) {
                    try {
                        Float.parseFloat(arregloTemporalInstrucciones[2]);
                    } catch(NumberFormatException nfe) {
                        InstruccionAsignar asignacion = new AsignacionSimple();
                        asignacion.ejecutar(variables,arregloTemporalInstrucciones[2],0,0,null,null,null);
                    }
                } else if(!variables.containsKey(arregloTemporalInstrucciones[4])) {
                    try {
                        Float.parseFloat(arregloTemporalInstrucciones[4]);
                    } catch(NumberFormatException nfe) {
                        InstruccionAsignar asignacion = new AsignacionSimple();
                        asignacion.ejecutar(variables,arregloTemporalInstrucciones[4],0,0,null,null,null);
                    }
                }
                try {                    
                    determinarAsignacionCompuesta(arregloTemporalInstrucciones, variables);
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
                if(!variables.containsKey(arregloTemporalInstrucciones[1])) {
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+ 0);
                } else {
                    Instruccion mostrar = new InstruccionMostrar();
                    mostrar.ejecutar(variables, arregloTemporalInstrucciones[1], 0, 0, null, variablesGuardadas, null);
                }
            break;

            case "pedir":
                Instruccion pedir = new InstruccionPedir();
                pedir.ejecutar(variables, arregloTemporalInstrucciones[1], 0, 0, null, variablesGuardadas, null);
            break;

            case "guardar":
                if(variablesGuardadas.contains(arregloTemporalInstrucciones[1])) {
                    throw new VariableGuardadaException("La variable " + arregloTemporalInstrucciones[1] + " ya está guardada, deseas reescribir su valor?");
                } else {
                    Instruccion guardar = new InstruccionGuardar();
                    guardar.ejecutar(variables, arregloTemporalInstrucciones[1], 0, 0, null, variablesGuardadas, null);
                }
            break;

            case "leer":
                if(!variables.containsKey(arregloTemporalInstrucciones[1])) {
                    InstruccionAsignar asignacion = new AsignacionSimple();
                    asignacion.ejecutar(variables,arregloTemporalInstrucciones[1],0,0,null,null,null);
                }
                Instruccion leer = new InstruccionLeerEn();
                leer.ejecutar(variables, arregloTemporalInstrucciones[1],0,0,arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1], null,null);
            break;
            default:
                throw new InstruccionIncorrectaException("La instrucción '" + opcion + "' no es correcta.\n"
                                                 + "*Linea: "+ cont);
        }
    }
    
    /**
     * Se leen las variables que se han guardado.
     * @param variablesEnTexto ArrayList con las varuables guardadas.
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
                Instruccion nuevaAsignacion = new AsignacionSimple();
                nuevaAsignacion.ejecutar(variables, variable, valor, 0, null, null, null);
                System.out.println(variables);
        }
    }
    
    public void determinarAsignacionCompuesta(String [] arregloTemporalInstrucciones,HashMap variables) throws DivisionCeroException {
        for(int j = 0; j < arregloTemporalInstrucciones.length; j++) {
            if(variables.containsKey(arregloTemporalInstrucciones[j])) {
                arregloTemporalInstrucciones[j] = Double.toString((double) variables.get(arregloTemporalInstrucciones[j]));
            }
        }
        determinarOperacion(arregloTemporalInstrucciones[0],Double.parseDouble(arregloTemporalInstrucciones[2]),arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-2].charAt(0),Double.parseDouble(arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1]));
    }
/**
     * Se determinan las operaciones 
     * (suma, resta, multiplicaión, division, modular)
     * @param operando1
     * @param operador
     * @param operando2
     * @return 
     */
    public void determinarOperacion(String variable1,double operando1,char operador,double operando2) throws DivisionCeroException {
        AsignacionCompuesta operacion;
        switch(operador){
            case '+':
                operacion = new Suma();
                operacion.ejecutar(variables, variable1, operando1, operando2, null, variablesGuardadas, null);
            break;
            case '-':
                operacion = new Resta();
                operacion.ejecutar(variables, variable1, operando1, operando2, null, variablesGuardadas, null);
            break;
            case '*':
                operacion = new Multiplicacion();
                operacion.ejecutar(variables, variable1, operando1, operando2, null, variablesGuardadas, null);
            break;
            case '/':
                if(operando2 == 0) {
                    throw new DivisionCeroException();
                }
                operacion = new Division();
                operacion.ejecutar(variables, variable1, operando1, operando2, null, variablesGuardadas, null);
            break;
            case '%':
                operacion = new Modular();
                operacion.ejecutar(variables, variable1, operando1, operando2, null, variablesGuardadas, null);
            break;
            default:
                System.out.println("Se ingreso otro caracter.");
            break;
        }
    }
    
    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }
    
    public void determinarValorVariableIgualesException(HashMap variables, String valor,String variable, int cont) throws ValorVariableIgualesException {
        if(!(variables.containsKey(variable)) && valor.equals(variable))
            throw new ValorVariableIgualesException(cont + "");
        
    }
}
