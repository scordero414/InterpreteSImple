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
public class AsignacionSimple extends Asignacion{
    
    public AsignacionSimple(String variable, float valor) {
        super(variable, valor);
    }
    
    public void asignar(HashMap variables){
        variables.put(getVariable(), getValor());
    }

    @Override
    public void asignar(String[] arregloTemporalInstrucciones, HashMap variables) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
