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

    @Override
    public void ejecutar(HashMap tablaVariables, String variable1, String variable2, ArrayList variablesGuardas) {
        try {
            String resultadoPedir = JOptionPane.showInputDialog(("Ingresa el valor para " + variable1 + ":").trim());
            
            if(resultadoPedir != null){
                double resultado = Double.parseDouble(resultadoPedir);
                InstruccionAsignar asignacion = new AsignacionSimple(variable1, resultado);
                asignacion.asignar(tablaVariables);
            } else {
                System.out.println("Has presionado cancelar!!");
            }
            
        } catch(NumberFormatException nfe) {
            ejecutar(tablaVariables, variable1, variable2, variablesGuardas);
        }
    }
    
}
