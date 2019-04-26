/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author ASUS
 */
public class GestorDeVariables {
    /**
     * Las variables que se van asignando se guardan en este Hashmap.
     */
    private HashMap<String,Double> variables = new HashMap<>();
    /**
     * Las variables guardadas, en otro archivo de texto. Se añaden a este ArrayList.
     */
    private ArrayList<String> variablesGuardadas = new ArrayList<>();;

    public HashMap<String, Double> getVariables() {
        return variables;
    }

    public void setVariables(HashMap<String, Double> variables) {
        this.variables = variables;
    }

    public ArrayList<String> getVariablesGuardadas() {
        return variablesGuardadas;
    }

    public void setVariablesGuardadas(ArrayList<String> variablesGuardadas) {
        this.variablesGuardadas = variablesGuardadas;
    }
    
    
}
