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
    private HashMap<String,Float> variables = new HashMap<>();
    private ArrayList<String> variablesGuardadas = new ArrayList<>();
    

    public float mostrar(String variable,HashMap variables){
        return (float) variables.get(variable);
    }
    
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    public void pedir(String variable, HashMap variables) throws NumberFormatException {
        try {
            float resultadoPedir = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para " + variable + ":").trim());                
            asignacion = new AsignacionSimple(variable, resultadoPedir);
            asignacion.asignar(variables);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException();
        }
        
    }
    
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
    
    public String guardar(String variable,HashMap variables){
        String linea = ""+variable+" = "+variables.get(variable);
        return linea;
    }

    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }
    
    
}
