/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.HashMap;

/**
 * Se le asigna un valor a una variable.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public class AsignacionSimple extends InstruccionAsignar{
    
    public AsignacionSimple(String variable, double valor) {
        super(variable, valor);
    }
    
    /**
     * Se le asigna un valor a una variable, y ésta se guarda en el Hashmap.
     * @param variables 
     */
    public void asignar(HashMap variables){
        variables.put(getVariable(), getValor());
    }

    @Override
    public void asignar(String[] arregloTemporalInstrucciones, HashMap variables) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
