/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class Instruccion {
    
    private Operacion operacion;
    
    public void asignar(String variable, float valor,HashMap variables){
        variables.put(variable, valor);
    }
    
    public float operar(float operando1 , char operador, float operando2){
        return operacion.determinarOperacion(operando1,operador,operando2);
    }
    
    public String mostrar(String variable,HashMap variables){
        return (String) variables.get(variable);
    }
    
    public void  guardarVariableTemporalPedida(String variable, HashMap variables , float valorPedido){
        variables.put(variable, valorPedido);
    }
    
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    
}
