/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author dgutierrezd
 */
public class InstruccionGuardar extends Instruccion {

    /**
     * Se guardan las variables pedidas en un archivo de texto predeterminado.
     * @param tablaVariables HashMap donde se almacenan las variables.
     * @param variable1 Variable a guardar.
     * @param variablesGuardadas ArrayList en el cual se almacenan las variables guardadas.
     */
    @Override
    public void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardadas,String [] arregloTemporalInstrucciones) {
        String linea = "";
        try {
            double resultado = (double) tablaVariables.get(variable1);
            linea = "" + variable1 + " = " + resultado;
        } catch(NullPointerException npe) {
            linea = "" + variable1 + " = " + 0;
        }
        
        variablesGuardadas.add(linea);
    }
    
}
