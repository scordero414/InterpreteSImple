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

    @Override
    public void ejecutar(HashMap tablaVariables, String variable1, String variable2, ArrayList variablesGuardadas) {
        JOptionPane.showMessageDialog(null,"El valor de " + variable1 + " es: " + tablaVariables.get(variable1));
    }
    
}
