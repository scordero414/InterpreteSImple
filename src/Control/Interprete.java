/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Elementos.Instruccion;
import Elementos.Operacion;
import IOElements.Escritor;
import IOElements.EscritorArchivoTextoPlano;
import IOElements.Lector;
import IOElements.LectorArhivoTextoPlano;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class Interprete {
    private Lector lector;
    private ArrayList instrucciones;
    private Instruccion instruccion;
    private HashMap<String,Float> variables = new HashMap<>();
    private static String archivoInstrucciones = "instrucciones.txt";
    private static String archivoDatos = "archivoDestino.txt";
    public Interprete() {
        lector = new LectorArhivoTextoPlano();
        instruccion = new Instruccion();
    }
    
    
    public static void main(String[] args) throws IOException{
        Interprete interprete = new Interprete();
        interprete.mostrarInstrucciones();
        interprete.determinarInstruccion();
//        System.out.println(interprete.variables.get("a"));
//        System.out.println(interprete.variables.get("b"));
//        System.out.println(interprete.variables.get("r1"));
//        System.out.println(interprete.variables.get("r3"));
    }
    public void mostrarInstrucciones() throws IOException{
        this.instrucciones = lector.leerArchivo(archivoInstrucciones);
        for (int i = 0; i < this.instrucciones.size(); i++) {
            System.out.println(this.instrucciones.get(i));
        }
    }
    
    public void determinarInstruccion() throws IOException{
        for (int i = 0; i < this.instrucciones.size(); i++) {
            String instruccionTemporal = this.instrucciones.get(i).toString();
            if(instruccionTemporal.equals("")){
                continue;
            }
            String [] arregloTemporalInstrucciones = instruccionTemporal.split(" ");
            if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length == 3)){
                instruccion.asignar(arregloTemporalInstrucciones[0],Float.parseFloat(arregloTemporalInstrucciones[2]),variables);
            }else if(arregloTemporalInstrucciones[1].equals("=") & (arregloTemporalInstrucciones.length > 3)){
                for(int j = 0; j < arregloTemporalInstrucciones.length; j++) {
                    if(variables.containsKey(arregloTemporalInstrucciones[j])) {
                        arregloTemporalInstrucciones[j] =Float.toString(variables.get(arregloTemporalInstrucciones[j]));

                    }
                }
                Operacion operacion = new Operacion(Float.parseFloat(arregloTemporalInstrucciones[2]),arregloTemporalInstrucciones[3].charAt(0),Float.parseFloat(arregloTemporalInstrucciones[4]));
                float resultado = operacion.determinarOperacion();
                instruccion.asignar(arregloTemporalInstrucciones[0],resultado,variables);
            }else if(arregloTemporalInstrucciones[0].equals("mostrar")){
                try{
                    float resultado = instruccion.mostrar(arregloTemporalInstrucciones[1], variables);
                    JOptionPane.showMessageDialog(null,"El valor de "+ arregloTemporalInstrucciones[1]+" es: "+resultado);
                }catch(NullPointerException np){
                    System.out.println("Errorsito!!!");
                }
            }else if(arregloTemporalInstrucciones[0].equals("pedir")){
                float resultado = Float.parseFloat(JOptionPane.showInputDialog("Ingresa el valor para "+arregloTemporalInstrucciones[1]+":"));
                instruccion.asignar(arregloTemporalInstrucciones[1], resultado, variables);
            }else if(arregloTemporalInstrucciones[0].equals("guardar")){
                Escritor escritor = new EscritorArchivoTextoPlano();
                escritor.escribir(archivoDatos, arregloTemporalInstrucciones[1], variables.get(arregloTemporalInstrucciones[1]));
            }
        }
    }
}
