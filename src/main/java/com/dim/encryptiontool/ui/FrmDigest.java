/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.ui;

import com.dim.encryption.MD5;
import java.io.ByteArrayInputStream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author daiyma
 */
public class FrmDigest extends javax.swing.JPanel {

    /**
     * Creates new form FrmDigest
     */
    public FrmDigest() {
        initComponents();
        init();
    }
    
    private void init() {
        jtta = new DropDragSupportTextArea();
        jtta.setColumns(20);
        jtta.setRows(5);
        jScrollPane1.setViewportView(jtta);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbBoxAlg = new javax.swing.JComboBox();
        jbtnCalculate = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtta = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jttaDigest = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        cmbBoxAlg.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "MD5", "SHA1", "SHA256" }));

        jbtnCalculate.setText("计算(C)");
        jbtnCalculate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCalculateActionPerformed(evt);
            }
        });

        jtta.setColumns(20);
        jtta.setRows(5);
        jtta.setToolTipText("可以拖拽文件哦");
        jScrollPane1.setViewportView(jtta);

        jttaDigest.setColumns(20);
        jttaDigest.setEditable(false);
        jttaDigest.setRows(2);
        jScrollPane2.setViewportView(jttaDigest);

        jLabel1.setText("摘要算法");

        jLabel2.setFont(new java.awt.Font("仿宋", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 153, 0));
        jLabel2.setText("支持拖拽文件");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbBoxAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jbtnCalculate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(167, 167, 167)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbBoxAlg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbtnCalculate)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnCalculateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCalculateActionPerformed
        // TODO add your handling code here:
        String algorithm = cmbBoxAlg.getSelectedItem().toString();
        if(StringUtils.isBlank(jtta.getText())){
            return;
        }
        String p = jtta.getText();
        String md5digest = MD5.streamToMD5(new ByteArrayInputStream(p.getBytes()));
        jttaDigest.setEditable(false);
        jttaDigest.setText(md5digest);
    }//GEN-LAST:event_jbtnCalculateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cmbBoxAlg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jbtnCalculate;
    private javax.swing.JTextArea jtta;
    private javax.swing.JTextArea jttaDigest;
    // End of variables declaration//GEN-END:variables
}
