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
 *
 * @author ASUS
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
     * @param variable
     * @param variables
     * @return 
     */
    public float mostrar(String variable,HashMap variables){
        return (float) variables.get(variable);
    }
    
    /**
     * Se le asigna el valor de una varibale, en otra variable aun no creada.
     * @param variable1
     * @param variable2
     * @param variables 
     */
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    /**
     * Se crea una varibale, y se le asigna un valor. Por medio de un inputDialog.
     * @param variable
     * @param variables
     * @throws NumberFormatException 
     */
    public void pedir(String variable, HashMap variables) throws NumberFormatException {
        try {
            float resultadoPedir = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para " + variable + ":").trim());                
            asignacion = new AsignacionSimple(variable, resultadoPedir);
            asignacion.asignar(variables);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        } 
    }
    
    /**
     * Conociendo las instrucciones pedidas en el archivo de texto, se determina su función.
     * @param opcion
     * @param arregloTemporalInstrucciones
     * @param variables
     * @throws NumberFormatException
     * @throws InstruccionIncorrectaException
     * @throws NullPointerException
     * @throws VariableGuardadaException 
     */
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables) throws NumberFormatException, InstruccionIncorrectaException, NullPointerException, VariableGuardadaException{
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
                try {
                    pedir(arregloTemporalInstrucciones[1], variables);
                } catch (NumberFormatException nfe) {
                    pedir(arregloTemporalInstrucciones[1], variables);
                }
                
            break;

            case "guardar":
                variablesGuardadas.add(guardar(arregloTemporalInstrucciones[1], variables));                
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
     * @param archivoInstrucciones
     * @throws IOException
     * @throws NullPointerException
     * @throws InstruccionIncorrectaException
     * @throws VariableGuardadaException 
     */
    public void determinarInstruccion(Lector lector,String archivoInstrucciones) throws IOException, NullPointerException, InstruccionIncorrectaException, VariableGuardadaException{
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

    public void iniciar(Lector lector, String archivoInstrucciones) throws IOException {
        determinarInstruccion(lector, archivoInstrucciones);
    }
    
    /**
     * Se guardan las variables pedidas en un archivo de texto predeterminado.
     * @param variable
     * @param variables
     * @return 
     */
    public String guardar(String variable,HashMap variables){
        String linea = ""+variable+" = "+variables.get(variable);
        return linea;
    }

    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }
    
    
}
