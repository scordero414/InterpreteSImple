/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 * Se define la operación (Multiplicación) a dos operandos.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190408
 * @since 1.0
 */
public class Multiplicacion extends Operacion{

    public Multiplicacion(float operando1, float operando2) {
        super(operando1, operando2);
    }

    @Override
    public float operar() {
        return getOperando1() * getOperando2();
    }
    
}
