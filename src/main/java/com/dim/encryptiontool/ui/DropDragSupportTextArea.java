/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dim.encryptiontool.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.swing.JTextArea;

/**
 *
 * @author yanming_dai
 */
public class DropDragSupportTextArea extends JTextArea implements DropTargetListener {

    private DropTarget dropTarget;

    public DropDragSupportTextArea() {
        //注册DropTarget，并将它与组件相连，处理哪个组件的相连  
        //即连通组件（第一个this）和Listener(第二个this)  
        dropTarget = new DropTarget(this, DnDConstants.ACTION_COPY_OR_MOVE, this, true);
    }

    //拖入文件或字符串,这里只说明能拖拽，并未打开文件并显示到文本区域中
    public void dragEnter(DropTargetDragEvent dtde) {
        System.out.print("Enter");
    }

    public void dragOver(DropTargetDragEvent dtde) {
        System.out.print("Over");
    }

    public void dropActionChanged(DropTargetDragEvent dtde) {
        System.out.print("Change");
    }

    public void dragExit(DropTargetEvent dte) {
        System.out.print("Exit");
    }

    public void drop(DropTargetDropEvent dtde) {
        DataFlavor[] dataFlavors = dtde.getCurrentDataFlavors();
        for(DataFlavor s: dataFlavors){
            System.out.println(s.getMimeType());
        }
        if (dataFlavors[0].match(DataFlavor.javaFileListFlavor)) {
            try {//important
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                Transferable tr = dtde.getTransferable();
                Object obj = tr.getTransferData(DataFlavor.javaFileListFlavor);
                List<File> files = (List<File>) obj;
                for (int i = 0; i < files.size(); i++) {
                    append(files.get(i).getAbsolutePath() + "/r/n");
                }
            } catch (UnsupportedFlavorException ex) {
            } catch (IOException ex) {
            }
        }
    }
}
