/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.IO;

import java.awt.Component;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author yanming_dai
 */
public class FileUtil {

    static String lastSelection;

    /**
     * 选择目标文件
     *
     * @param winTitle 窗口标题
     * @param resultField 选择结果将要显示在的元素
     */
    public static File chooseFile(String winTitle, Component cmpnt) {
        FileSystemView desk = FileSystemView.getFileSystemView();
        JFileChooser file = null;

        if (null == lastSelection) {
            file = new JFileChooser(desk);
        } else {
            file = new JFileChooser(lastSelection, desk);
        }

        file.setDialogTitle(winTitle);
        file.setFileSelectionMode(JFileChooser.FILES_ONLY);//只能选择文件
        int returnVal = file.showOpenDialog(cmpnt);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            lastSelection = file.getSelectedFile().getAbsolutePath();
            return file.getSelectedFile();
        }
        return null;
    }
}
