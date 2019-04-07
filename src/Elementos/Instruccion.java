/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Excepciones.InstruccionIncorrectaException;
import Excepciones.ValorIncorrectoException;
import Excepciones.VariableGuardadaException;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Instruccion {
    
    private Operacion operacion;
    
    public void asignar(String variable, float valor,HashMap variables){
        variables.put(variable, valor);
    }
    
    public float mostrar(String variable,HashMap variables){
        return (float) variables.get(variable);
    }
    
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    public void pedir(String variable, HashMap variables) {
        try {
            float resultadoPedir = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para " + variable + ":").trim());                
            asignar(variable, resultadoPedir, variables);
        } catch(NumberFormatException nf) {
            JOptionPane.showMessageDialog(null, "Has ingresado un valor incorrecto, por favor ingresa un número.");
            pedir(variable, variables);
        }
    }
    
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables) throws NullPointerException, InstruccionIncorrectaException, ValorIncorrectoException, VariableGuardadaException{
        switch(opcion){
            case "mostrar":
                try {
                    float resultado = mostrar(arregloTemporalInstrucciones[1], variables);
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+resultado);
                } catch (NullPointerException np) {
                    JOptionPane.showMessageDialog(null, "La variable " + arregloTemporalInstrucciones[1] +" no existe.");
                }
                    
            break;

            case "pedir":
                pedir(arregloTemporalInstrucciones[1], variables);
            break;

            case "guardar":
            break;

            case "leer":
                leerEn(arregloTemporalInstrucciones[1], arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1],variables);
            break;
            default:
                JOptionPane.showMessageDialog(null, "La instruccion " + opcion + " no existe.");
            break;
        }
    }
    
    public void determinarAsignacionCompuesta(String [] arregloTemporalInstrucciones,HashMap variables){
        for(int j = 0; j < arregloTemporalInstrucciones.length; j++) {
            if(variables.containsKey(arregloTemporalInstrucciones[j])) {
                arregloTemporalInstrucciones[j] = Float.toString((float) variables.get(arregloTemporalInstrucciones[j]));

            }
        }
        Operacion operacion = new Operacion(Float.parseFloat(arregloTemporalInstrucciones[2]),arregloTemporalInstrucciones[3].charAt(0),Float.parseFloat(arregloTemporalInstrucciones[4]));
        float resultado = operacion.determinarOperacion();
        asignar(arregloTemporalInstrucciones[0],resultado,variables);
    }
    
}
