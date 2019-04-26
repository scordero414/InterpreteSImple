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
public class InstruccionMostrar extends Instruccion {

    /**
     * Se obtiene y se muestra el valor de la variable que se pide 'mostrar'.
     * @param variable1 variable a la cual se quiere mostrar el valor. 
     * @param tablaVariables tabla de variables, en donde se almacenaran temporalmente los datos.
     */
    @Override
    public void ejecutar(HashMap tablaVariables, String variable1,double valorVariable,double operando2, String variable2, ArrayList variablesGuardadas,String [] arregloTemporalInstrucciones) {
        JOptionPane.showMessageDialog(null,"El valor de " + variable1 + " es: " + tablaVariables.get(variable1));
    }
    
}
