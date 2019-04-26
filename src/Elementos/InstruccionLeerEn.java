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

    /**
     * Se le asigna el valor de una varibale, en otra variable aun no creada.
     * @param variable1 variable original
     * @param variable2 variable a la cual se le quiere transferir el valor.
     * @param tablaVariables tabla de variables, en donde se almacenaran temporalmente los datos.
     */
    @Override
    public void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardadas,String [] arregloTemporalInstrucciones) {
        double valor  = (double) tablaVariables.get(variable1);
        tablaVariables.put(variable2, valor);
    }
    
}
