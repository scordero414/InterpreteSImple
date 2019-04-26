/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Se le asigna un valor a una variable.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class AsignacionSimple extends InstruccionAsignar{
     /**
     * Se le asigna un valor a una variable, y ésta se guarda en el Hashmap.
     * @param variables 
     */
    @Override
    public void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardas,String [] arregloTemporalInstrucciones) {
        tablaVariables.put(variable1, valorVariable);
    }

    
}
