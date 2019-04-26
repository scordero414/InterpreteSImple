/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author dgutierrezd
 */
public class InstruccionPedir extends Instruccion {

    /**
     * Se crea una varibale, y se le asigna un valor. Por medio de un inputDialog.
     * @param variable a la cual necesitamis darle un valor.
     * @param variables tabla de variables, en donde se almacenaran temporalmente los datos.
     * @throws NumberFormatException 
     */
    @Override
    public void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardas,String [] arregloTemporalInstrucciones) {
        try {
            String resultadoPedir = JOptionPane.showInputDialog(("Ingresa el valor para " + variable1 + ":").trim());
            
            if(resultadoPedir != null){
                double resultado = Double.parseDouble(resultadoPedir);
                Instruccion asignacion = new AsignacionSimple();
                asignacion.ejecutar(tablaVariables, variable1,resultado, 0, null, null,null);
            } else {
                System.out.println("Has presionado cancelar!!");
            }
            
        } catch(NumberFormatException nfe) {
            ejecutar(tablaVariables, variable1, valorVariable,operando2, variable2, variablesGuardas,arregloTemporalInstrucciones);
        }
    }
    
}
