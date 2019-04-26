/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Se define la operación (Multiplicación) a dos operandos.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190408
 * @since 1.0
 */
public class Multiplicacion extends Operacion{

    public void ejecutar(HashMap tablaVariables, String variable1, double valorVariable, double operando2, String variable2, ArrayList variablesGuardas, String[] arregloTemporalInstrucciones) {
        tablaVariables.put(variable1, (valorVariable*operando2));
    }
}
