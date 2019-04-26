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
public class InstruccionLeerEn extends Instruccion {

    @Override
    public void ejecutar(HashMap tablaVariables, String variable1, String variable2, ArrayList variablesGuardadas) {
        double valor  = (double) tablaVariables.get(variable1);
        tablaVariables.put(variable2, valor);
    }
    
}
