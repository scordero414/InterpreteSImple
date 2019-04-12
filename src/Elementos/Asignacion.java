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
public abstract class Asignacion {
    private String variable;
    private float valor;

    public Asignacion(String variable, float valor) {
        this.variable = variable;
        this.valor = valor;
    }         
    
    public Asignacion() {
    }
    
    public abstract void asignar(HashMap variables);
    public abstract void asignar(String [] arregloTemporalInstrucciones,HashMap variables);
    
    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
    
    
}