/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.ui;

import javax.swing.JSplitPane;
import javax.swing.plaf.SplitPaneUI;
import javax.swing.plaf.basic.BasicSplitPaneUI;

/**
 *
 * @author daiyma
 */
public class FrmEnvelop extends javax.swing.JPanel {

    /**
     * Creates new form FrmEnvelop
     */
    public FrmEnvelop() {
        initComponents();
        init();
    }

    final void init() {
        
        
        jSplitPane1.setOneTouchExpandable(true);//让分割线显示出箭头
        jSplitPane1.setContinuousLayout(true);//操作箭头，重绘图形
        jSplitPane1.setOrientation(JSplitPane.HORIZONTAL_SPLIT);//设置分割线方向
        jpSrc.setSize(400, 400);
        jpRst.setSize(400, 400);
        jSplitPane1.setLeftComponent(jpSrc);//布局中添加组件 ，面板1
        jSplitPane1.setRightComponent(jpRst);//添加面板2
        jSplitPane1.setDividerSize(5);//设置分割线的宽度
        jSplitPane1.setDividerLocation(200);//设定分割线的距离左边的位置
        
        
        SplitPaneUI ui = jSplitPane1.getUI();
        if (ui instanceof BasicSplitPaneUI) {
            ((BasicSplitPaneUI) ui).getDivider().setBorder(null);
        } 
       
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT
     * modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jpSrc = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jpRst = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        jpSrc.setBackground(new java.awt.Color(255, 255, 255));
        jpSrc.setBorder(javax.swing.BorderFactory.createTitledBorder("原文"));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setBorder(null);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        jTextArea3.setBorder(null);
        jScrollPane3.setViewportView(jTextArea3);

        javax.swing.GroupLayout jpSrcLayout = new javax.swing.GroupLayout(jpSrc);
        jpSrc.setLayout(jpSrcLayout);
        jpSrcLayout.setHorizontalGroup(
            jpSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 623, Short.MAX_VALUE)
            .addGroup(jpSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE))
        );
        jpSrcLayout.setVerticalGroup(
            jpSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
            .addGroup(jpSrcLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jpSrc);

        jpRst.setBackground(new java.awt.Color(255, 255, 255));
        jpRst.setBorder(javax.swing.BorderFactory.createTitledBorder("结果"));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setBorder(null);
        jScrollPane2.setViewportView(jTextArea2);

        javax.swing.GroupLayout jpRstLayout = new javax.swing.GroupLayout(jpRst);
        jpRst.setLayout(jpRstLayout);
        jpRstLayout.setHorizontalGroup(
            jpRstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        jpRstLayout.setVerticalGroup(
            jpRstLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jpRst);

        jButton3.setText("解密");

        jButton2.setText("加密");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
            .addComponent(jSplitPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 697, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSplitPane1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JPanel jpRst;
    private javax.swing.JPanel jpSrc;
    // End of variables declaration//GEN-END:variables
}
