/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Excepciones.DivisionCeroException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Se crean las asignaciones compuestas, a una variable se le designa una operación.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public abstract class AsignacionCompuesta extends InstruccionAsignar{

    @Override
    public abstract void ejecutar(HashMap tablaVariables, String variable1, double valorVariable,double operando2, String variable2, ArrayList variablesGuardas,String [] arregloTemporalInstrucciones);

}