/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class AsignacionCompuesta extends Asignacion{
    private Operacion operacion;
    
    public AsignacionCompuesta(String variable, float valor) {
        super(variable, valor);
    }

    public AsignacionCompuesta() {
    }
    
    public void asignar(String [] arregloTemporalInstrucciones,HashMap variables){
        determinarAsignacionCompuesta(arregloTemporalInstrucciones, variables);
        variables.put(getVariable(), getValor());
    }
    
    public void determinarAsignacionCompuesta(String [] arregloTemporalInstrucciones,HashMap variables){
        for(int j = 0; j < arregloTemporalInstrucciones.length; j++) {
            if(variables.containsKey(arregloTemporalInstrucciones[j])) {
                arregloTemporalInstrucciones[j] = Float.toString((float) variables.get(arregloTemporalInstrucciones[j]));
            }
        }
        float resultado = determinarOperacion(Float.parseFloat(arregloTemporalInstrucciones[2]),arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-2].charAt(0),Float.parseFloat(arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1]));
        setVariable(arregloTemporalInstrucciones[0]);
        setValor(resultado);
    }
    
    
    public float determinarOperacion(float operando1,char operador,float operando2){
        float resultado = 0;
        switch(operador){
            case '+':
                operacion = new Suma(operando1, operando2);
                resultado = operacion.operar();
            break;
            case '-':
                operacion = new Resta(operando1, operando2);
                resultado = operacion.operar();
            break;
            case '*':
                operacion = new Multiplicacion(operando1, operando2);
                resultado = operacion.operar();
            break;
            case '/':
                operacion = new Division(operando1, operando2);
                resultado = operacion.operar();
            break;
            case '%':
                operacion = new Modular(operando1, operando2);
                resultado = operacion.operar();
            break;
            default:
                System.out.println("Hay un problema, el caracter no esta entrando.");
            break;
        }
        return resultado;
    }

    @Override
    public void asignar(HashMap variables) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

}
