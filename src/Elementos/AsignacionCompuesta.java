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
    
//    private Operacion operacion;
//    
//    public AsignacionCompuesta(String variable, double valor) {
//        super(variable, valor);
//    }
//
//    public AsignacionCompuesta() {
//    }
//    
//    /**
//     * Se asigna un valor por medio de una operación a una variable, 
//     * y estas se almacenan en un Hashmap.
//     * @param arregloTemporalInstrucciones
//     * @param variables 
//     */
//    @Override
//    public void asignar(String [] arregloTemporalInstrucciones,HashMap variables) throws DivisionCeroException {
//        determinarAsignacionCompuesta(arregloTemporalInstrucciones, variables);
//        variables.put(getVariable(), getValor());
//    }
//    
//    /**
//     * Se determina una operación y ésta se almacena en una variable.
//     * @param arregloTemporalInstrucciones
//     * @param variables 
//     */
//    public void determinarAsignacionCompuesta(String [] arregloTemporalInstrucciones,HashMap variables) throws DivisionCeroException {
//        for(int j = 0; j < arregloTemporalInstrucciones.length; j++) {
//            if(variables.containsKey(arregloTemporalInstrucciones[j])) {
//                arregloTemporalInstrucciones[j] = Double.toString((double) variables.get(arregloTemporalInstrucciones[j]));
//            }
//        }
//        double resultado = determinarOperacion(Double.parseDouble(arregloTemporalInstrucciones[2]),arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-2].charAt(0),Double.parseDouble(arregloTemporalInstrucciones[arregloTemporalInstrucciones.length-1]));
//        setVariable(arregloTemporalInstrucciones[0]);
//        setValor(resultado);
//    }
//    
//    /**
//     * Se determinan las operaciones 
//     * (suma, resta, multiplicaión, division, modular)
//     * @param operando1
//     * @param operador
//     * @param operando2
//     * @return 
//     */
//    public double determinarOperacion(double operando1,char operador,double operando2) throws DivisionCeroException {
//        double resultado = 0;
//        switch(operador){
//            case '+':
//                operacion = new Suma(operando1, operando2);
//                resultado = operacion.operar();
//            break;
//            case '-':
//                operacion = new Resta(operando1, operando2);
//                resultado = operacion.operar();
//            break;
//            case '*':
//                operacion = new Multiplicacion(operando1, operando2);
//                resultado = operacion.operar();
//            break;
//            case '/':
//                if(operando2 == 0) {
//                    throw new DivisionCeroException();
//                }
//                operacion = new Division(operando1, operando2);
//                resultado = operacion.operar();
//            break;
//            case '%':
//                operacion = new Modular(operando1, operando2);
//                resultado = operacion.operar();
//            break;
//            default:
//                System.out.println("Se ingreso otro caracter.");
//            break;
//        }
//        return resultado;
//    }
//
//    @Override
//    public void asignar(HashMap variables) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}