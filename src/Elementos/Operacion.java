/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 * Interface para métodos y atributos para las operaciones.
 * @author Daniel Gutierrez
 * @author Sebastián Cordero
 * @version 20190408
 * @since 1.0
 */
public abstract class Operacion {
    private float operando1;
    private float operando2;

    public Operacion(float operando1, float operando2) {
        this.operando1 = operando1;
        this.operando2 = operando2;
    }

    public float getOperando1() {
        return operando1;
    }

    public void setOperando1(float operando1) {
        this.operando1 = operando1;
    }

    public float getOperando2() {
        return operando2;
    }

    public void setOperando2(float operando2) {
        this.operando2 = operando2;
    }
    
    /**
     * Método para operar dos operandos, determinando su operador.
     * @return 
     */
    public abstract float operar();
 
}
