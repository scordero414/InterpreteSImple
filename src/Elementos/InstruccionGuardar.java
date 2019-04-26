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

    @Override
    public void ejecutar(HashMap tablaVariables, String variable1, String variable2, ArrayList variablesGuardadas) {
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
