/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vangoghproject;

import vangoghproject.*;


import java.awt.Panel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author josep
 */
public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainFrame
     */
    public mainFrame() {
        initComponents();
        setVisible(true);
        
    }

    public JLabel getSimilarityIndex() {
        return similarityIndex;
    }

    public JLabel getGoalImgLabel() {
        return goalImgLabel;
    }

    public JLabel getHealthiestImgLabel() {
        return healthiestImgLabel;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        goalImgLabel = new javax.swing.JLabel();
        healthiestImgLabel = new javax.swing.JLabel();
        similarityIndex = new javax.swing.JLabel();
        indexLabel0 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        optionsComboBox = new javax.swing.JComboBox<>();
        tamanoPoblacionText = new java.awt.TextField();
        cruceText = new java.awt.TextField();
        mutateQuantityText = new java.awt.TextField();
        pathText = new java.awt.TextField();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Main Window");
        setName("mainWindow"); // NOI18N

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Imagen meta");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Individuo mas apto");

        goalImgLabel.setBackground(new java.awt.Color(102, 255, 255));
        goalImgLabel.setToolTipText("goal Image");
        goalImgLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        goalImgLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        healthiestImgLabel.setBackground(new java.awt.Color(102, 255, 255));
        healthiestImgLabel.setToolTipText("Individuo mas apto");
        healthiestImgLabel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        healthiestImgLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        similarityIndex.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        similarityIndex.setText("Indice similitud");
        similarityIndex.setToolTipText("Score");
        similarityIndex.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        indexLabel0.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        indexLabel0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        indexLabel0.setText("Score:");
        indexLabel0.setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));

        optionsComboBox.setMaximumRowCount(3);
        optionsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distancia Euclideana", "Distancia Manhattan", "BEJARANO-FENG", " " }));
        optionsComboBox.setToolTipText("Options");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(optionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        label1.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        label1.setForeground(new java.awt.Color(0, 0, 0));
        label1.setText("Probabilidad de cruce");

        label2.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        label2.setForeground(new java.awt.Color(0, 0, 0));
        label2.setText("Tamano poblacion");

        label3.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        label3.setForeground(new java.awt.Color(0, 0, 0));
        label3.setText("Porcentaje genes a mutar");

        label4.setFont(new java.awt.Font("Courier New", 0, 18)); // NOI18N
        label4.setForeground(new java.awt.Color(0, 0, 0));
        label4.setText("Ruta de la imagen");

        jButton1.setText("Ejecutar");
        jButton1.setActionCommand("goBtn");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(168, 168, 168)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(goalImgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(healthiestImgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(pathText, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                                    .addComponent(mutateQuantityText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cruceText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tamanoPoblacionText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(617, 617, 617)
                        .addComponent(indexLabel0)
                        .addGap(95, 95, 95)
                        .addComponent(similarityIndex)))
                .addGap(167, 167, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(similarityIndex, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(indexLabel0))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(goalImgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(healthiestImgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tamanoPoblacionText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cruceText, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(mutateQuantityText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pathText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jButton1)))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(95, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String cruceGot,poblacionGot,mutationGot,pathGot;
        cruceGot=this.cruceText.getText();
        poblacionGot=this.tamanoPoblacionText.getText();
        mutationGot=this.mutateQuantityText.getText();
        pathGot=this.pathText.getText();
        int distanceMode = this.optionsComboBox.getSelectedIndex();
        if(cruceGot.equals("")||poblacionGot.equals("")||mutationGot.equals("") || pathGot.equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacios!");
        }else if(Integer.parseInt(mutationGot)>100 || Integer.parseInt(mutationGot)<=0 || Integer.parseInt(cruceGot)>100 ||Integer.parseInt(cruceGot)<=0 || Integer.parseInt(poblacionGot)<=0){
            JOptionPane.showMessageDialog(null, "Hay valores invalidos!");
        }else{
            Control ctrlInstance = new Control(Integer.parseInt(poblacionGot),Double.parseDouble(cruceGot)/100,Double.parseDouble(mutationGot)/100,pathGot,distanceMode,this);
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    /*
   String cruceGot,poblacionGot,mutationGot,pathGot;
        cruceGot=this.cruceText.getText();
        poblacionGot=this.tamanoPoblacionText.getText();
        mutationGot=this.mutateQuantityText.getText();
        pathGot=this.pathText.getText();
        System.out.println(Integer.parseInt(mutationGot));
        int distanceMode = this.optionsComboBox.getSelectedIndex();
        if(cruceGot.equals("")||poblacionGot.equals("")||mutationGot.equals("") || pathGot.equals("")){
            JOptionPane.showMessageDialog(null, "Hay campos vacios!");
        }else if(Integer.parseInt(mutationGot)>100 || Integer.parseInt(mutationGot)<=0 || Integer.parseInt(cruceGot)>100 ||Integer.parseInt(cruceGot)<=0 || Integer.parseInt(poblacionGot)<=0){
            JOptionPane.showMessageDialog(null, "Hay valores invalidos!");
            System.exit(0);
        }else{
            //Control ctrlInstance = new Control(Integer.getInteger(poblacionGot),Double.parseDouble(cruceGot)/100,Double.parseDouble(mutationGot)/100,pathGot,distanceMode,this);
            System.out.println("Creado");
        }
    */
    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mainFrame();
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.TextField cruceText;
    private javax.swing.JLabel goalImgLabel;
    private javax.swing.JLabel healthiestImgLabel;
    private javax.swing.JLabel indexLabel0;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.TextField mutateQuantityText;
    private javax.swing.JComboBox<String> optionsComboBox;
    private java.awt.TextField pathText;
    private javax.swing.JLabel similarityIndex;
    private java.awt.TextField tamanoPoblacionText;
    // End of variables declaration//GEN-END:variables

    
}
