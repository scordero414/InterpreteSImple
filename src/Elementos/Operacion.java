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
public class Operacion {
//    private float operando1;
//    private char operador;
//    private float operando2;
//
//    public Operacion(float operando1, char operador, float operando2) {
//        this.operando1 = operando1;
//        this.operador = operador;
//        this.operando2 = operando2;
//    }
//
//    public float getOperando1() {
//        return operando1;
//    }
//
//    public void setOperando1(float operando1) {
//        this.operando1 = operando1;
//    }
//
//    public char getOperador() {
//        return operador;
//    }
//
//    public void setOperador(char operador) {
//        this.operador = operador;
//    }
//
//    public float getOperando2() {
//        return operando2;
//    }
//
//    public void setOperando2(float operando2) {
//        this.operando2 = operando2;
//    }
    
    private float sumar(float operando1, float operando2){
        return operando1 + operando2;
    }
    private float restar(float operando1, float operando2){
        return operando1 - operando2;
    }
    private float multiplicar(float operando1, float operando2){
        return operando1 * operando2;
    }
    private float dividir(float operando1, float operando2){
        return operando1 / operando2;
    }
    private float modular(float operando1, float operando2){
        return operando1 % operando2;
    }
    
    public float determinarOperacion(float operando1, char operador, float operando2){
        float resultado = 0;
        switch(operador){
            case '+':
                resultado = sumar(operando1, operando2);
            break;
            case '-':
                resultado = restar(operando1, operando2);
            break;
            case '*':
                resultado = multiplicar(operando1, operando2);
            break;
            case '/':
                resultado = dividir(operando1, operando2);
            break;
            case '%':
                resultado = modular(operando1, operando2);
            break;
            default:
                System.out.println("Hay un problema, el caracter no esta entrando.");
            break;
        }
        return resultado;
    }
}
