/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 *
 * @author ASUS
 */
public class Suma extends Operacion{

    public Suma(float operando1, float operando2) {
        super(operando1, operando2);
    }

    @Override
    public float operar() {
        return getOperando1() + getOperando2();
    }
    
}
