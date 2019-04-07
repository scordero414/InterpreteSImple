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
    
    public float mostrar(String variable,HashMap variables){
        return (float) variables.get(variable);
    }
    
    public void leerEn(String variable1,String variable2, HashMap variables){
        float valor  = (float) variables.get(variable1);
        variables.put(variable2, valor);        
    }
    
    
}
