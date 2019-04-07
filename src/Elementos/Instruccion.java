/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

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
    
    public void determinarInstruccionSimple(String opcion,String [] arregloTemporalInstrucciones,HashMap variables){
        switch(opcion){
            case "mostrar":
                try{
                    float resultado = mostrar(arregloTemporalInstrucciones[1], variables);
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+resultado);
                }catch(NullPointerException np){
                    System.out.println("Errorsito!!!");
                }
            break;

            case "pedir":
                float resultado = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para "+arregloTemporalInstrucciones[1]+":").trim());                
                asignar(arregloTemporalInstrucciones[1], resultado, variables);
            break;

            case "guardar":
            break;

            case "leer":
                leerEn(arregloTemporalInstrucciones[1], arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1],variables);
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
