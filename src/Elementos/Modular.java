/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 * Se define la operación (Modular) a dos operandos.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190408
 * @since 1.0
 */
public class Modular extends Operacion{

    public Modular(double operando1, double operando2) {
        super(operando1, operando2);
    }

    @Override
    public double operar() {
        return getOperando1() % getOperando2();
    }
    
}
