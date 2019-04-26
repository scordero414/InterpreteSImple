/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.HashMap;

/**
 * Se determinan las asignaciones a una variable, ya sea asginaciín simple o compuesta.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public abstract class InstruccionAsignar {
    private String variable;
    private double valor;

    public InstruccionAsignar(String variable, double valor) {
        this.variable = variable;
        this.valor = valor;
    }         
    
    public InstruccionAsignar() {
    }
    
    public abstract void asignar(HashMap variables);
    public abstract void asignar(String [] arregloTemporalInstrucciones,HashMap variables);
    
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
