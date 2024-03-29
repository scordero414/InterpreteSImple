/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Control.Interprete;
import Elementos.Analizador;
import Excepciones.ArchivoVacioException;
import Excepciones.DivisionCeroException;
import Excepciones.ExcedeLimiteInstruccionException;
import Excepciones.InstruccionIncorrectaException;
import Excepciones.ValorVariableIgualesException;
import Excepciones.VariableGuardadaException;
import Excepciones.VariablesAlfabeticasException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dgutierrezd
 */
public class VistaPrincipal extends javax.swing.JFrame {
    
    /**
     * Estado de la vista, conociendo los botones accionados.
     */
    private int estado;
    private Interprete interprete;
    private Analizador instruccion;
    private String variable;
    private HashMap<String, Integer> variables = new HashMap<String, Integer>();
    
    public int getEstado() {
        return estado;
    }
    
    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal(Interprete interprete) {
        initComponents();
        setLocationRelativeTo(this);
        setVisible(true);
        this.interprete = interprete;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bIniciar = new javax.swing.JButton();
        bEditarArchivo = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bIniciar.setText("Iniciar");
        bIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bIniciarActionPerformed(evt);
            }
        });

        bEditarArchivo.setText("Editar Archivo");
        bEditarArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarArchivoActionPerformed(evt);
            }
        });

        bSalir.setText("Salir");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 204, 255));
        jLabel1.setText("Interprete Simple");

        jButton2.setText("Ver Variables Guardadas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Captura.JPG"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(bEditarArchivo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(bIniciar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(350, 350, 350)
                        .addComponent(bSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107)
                        .addComponent(bEditarArchivo))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(bIniciar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(bSalir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    String[] options = {"Si", "No"};
    
    /**
     * Se inicia con la lectura del archivo de texto, y dependiendo de ésta, se crean las
     * instrucciones.
     * @param evt 
     */
    private void bIniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bIniciarActionPerformed
        estado = 1;
        try {
            interprete.determinarOpcionesVista(estado);
        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ArchivoVacioException ave) {
            JOptionPane.showMessageDialog(null, "El archivo de texto esta vacío!");
        } catch (InstruccionIncorrectaException ie) {
            JOptionPane.showMessageDialog(null,ie.getMessage());
        } catch (NullPointerException npe) {
            JOptionPane.showMessageDialog(null, npe.getMessage());
        } catch (VariableGuardadaException vge) {
            int opcion = JOptionPane.showOptionDialog(null,vge.getMessage(), "Selecciona un botón",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Has ingresado un valor incorrecto, por favor ingresa un número.");
        } catch (ArrayIndexOutOfBoundsException aioe) {
            JOptionPane.showMessageDialog(null,"Instruccion incorrecta.\n *Linea: " + aioe.getMessage());
        } catch (ExcedeLimiteInstruccionException ele) {
            JOptionPane.showMessageDialog(null, "No puedes ingresar una asignación con más de una operación. \n *Linea: " +  ele.getMessage());
        } catch (DivisionCeroException dce) {
            JOptionPane.showMessageDialog(null, "No puedes dividir por cero! \n *Linea: " + dce.getMessage());
        } catch (VariablesAlfabeticasException vae) {
            JOptionPane.showMessageDialog(null, vae.getMessage());
        } catch (ValorVariableIgualesException vie) {
            JOptionPane.showMessageDialog(null, "La variable no puede ser igual a su valor por determinar. \n *Linea: " + vie.getMessage());
        }
    }//GEN-LAST:event_bIniciarActionPerformed

    /**
     * Salir de la aplicacion.
     * @param evt 
     */
    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        estado = 3;
        try {
            interprete.determinarOpcionesVista(estado);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_bSalirActionPerformed

    /**
     * Se abre el archivo de texto, para poder editar el interprete, y las instrucciones.
     * @param evt 
     */
    private void bEditarArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarArchivoActionPerformed
        estado = 2;
        try {
            interprete.determinarOpcionesVista(estado);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }//GEN-LAST:event_bEditarArchivoActionPerformed

    /**
     * Se abre un archivo de texto, mostrando las variables anteriormente guardadas.
     * @param evt 
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        estado = 4;
        try {
            interprete.determinarOpcionesVista(estado);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());;
        }
    }//GEN-LAST:event_jButton2ActionPerformed
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bEditarArchivo;
    private javax.swing.JButton bIniciar;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
