/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Se determinan las asignaciones a una variable, ya sea asginaciín simple o compuesta.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190406
 * @since 1.0
 */
public abstract class InstruccionAsignar extends Instruccion{
    
    @Override
    public abstract void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardas,String [] arregloTemporalInstrucciones);

}
