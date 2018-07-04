/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.ui;

import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author yanming_dai
 */
public class FrmPKI extends javax.swing.JPanel {

    static Color m_color = new Color(240, 240, 240);

    /**
     * Creates new form NewJPanel
     */
    public FrmPKI() {
        initComponents();
        this.setBackground(m_color);
        initTabs();
    }

    private void initTabs() {
        jSplitPane1.setOneTouchExpandable(true);//让分割线显示出箭头
        jSplitPane1.setContinuousLayout(true);//操作箭头，重绘图形
        jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线方向
        SplitPaneUI ui = jSplitPane1.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ((BasicSplitPaneUI) ui).getDivider().setBorder(null);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jbtnSign = new javax.swing.JButton();
        jbtnVerify = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(450, 200));

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(128);
        jSplitPane1.setDividerSize(8);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setLastDividerLocation(128);
        jSplitPane1.setOneTouchExpandable(true);

        jScrollPane3.setBorder(null);

        jTextArea3.setColumns(16);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setBorder(javax.swing.BorderFactory.createTitledBorder("原文"));
        jScrollPane3.setViewportView(jTextArea3);

        jSplitPane1.setLeftComponent(jScrollPane3);

        jScrollPane4.setBorder(null);

        jTextArea4.setColumns(20);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setBorder(javax.swing.BorderFactory.createTitledBorder("签名结果"));
        jScrollPane4.setViewportView(jTextArea4);

        jSplitPane1.setRightComponent(jScrollPane4);

        jbtnSign.setText("签名");
        jbtnSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSignActionPerformed(evt);
            }
        });

        jbtnVerify.setText("验签名");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnSign)
                .addGap(18, 18, 18)
                .addComponent(jbtnVerify)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jbtnVerify)
                    .addComponent(jbtnSign))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSignActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnSignActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JButton jbtnSign;
    private javax.swing.JButton jbtnVerify;
    // End of variables declaration//GEN-END:variables
}
