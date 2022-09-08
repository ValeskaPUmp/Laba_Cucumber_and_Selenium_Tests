package org.example;

import javax.swing.*;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;

public class Form extends JFrame {
    Listener listener=new Listener();
    JTree tree;
    HashMap<TreeUniversity.Node,LinkedList<TreeUniversity.Node>> framework;
    TreeUniversity TU;
    ChooseSelector cs=new ChooseSelector();
    JTextField jtfield;
    JButton add;
    JFrame jf;
    TreeModel tm;
    TreeUniversity.Node all;
    JButton addInfo;
    JButton getInfo;
    public Form(){
        jf=this;
        setTitle("NaUKMA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultLookAndFeelDecorated(true);
        setSize(1080,720);
        all=new TreeUniversity.Node("NaUKMA");
        TU=new TreeUniversity();
        framework=TU.framework;
        /**
        TreeUniversity.Node faculty1=new TreeUniversity.Node("Fi");
        TreeUniversity.Node student1=new TreeUniversity.Node("John Makkein");
        TreeUniversity.Node student2=new TreeUniversity.Node("Paul Vein");
        TreeUniversity.Node faculty2=new TreeUniversity.Node("FP");
        TreeUniversity.Node student3=new TreeUniversity.Node("Klein");
        TU.addEdge(all,faculty1);
        TU.addEdge(all,faculty2);
        TU.addEdge(faculty2,student2);
        TU.addEdge(faculty1,student1);
        TU.addEdge(faculty2,student3);
         **/
        tm=new TreeModel() {

            @Override
            public TreeUniversity.Node getRoot() {
                return all;
            }
            @Override
            public TreeUniversity.Node getChild(Object parent, int index) {
                return framework.get(parent).get(index);
            }

            @Override
            public int getChildCount(Object parent) {
                return framework.get(parent).size();
            }

            @Override
            public boolean isLeaf(Object node) {
                return framework.get(node)==null;
            }

            @Override
            public void valueForPathChanged(TreePath path, Object newValue) {
                path.pathByAddingChild(newValue);

            }

            @Override
            public int getIndexOfChild(Object parent, Object child) {
                return framework.get(parent).indexOf(child);
            }

            @Override
            public void addTreeModelListener(TreeModelListener l) {

            }

            @Override
            public void removeTreeModelListener(TreeModelListener l) {

            }
        };

        tree=new JTree(tm);
        tree.addTreeSelectionListener(cs);
        tree.setVisible(true);
        add(tree,BorderLayout.CENTER);
        JPanel grid=new JPanel(new GridLayout(1,4,15,0));
        add=new JButton("Add item");
        addInfo=new JButton("Add info about item");
        getInfo=new JButton("Get info");
        jtfield=new JTextField(10);
        grid.add(jtfield);
        grid.add(add);
        grid.add(addInfo);
        grid.add(getInfo);
        JPanel flow=new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        addInfo.addActionListener(listener);
        add.addActionListener(listener);
        getInfo.addActionListener(listener);
        add(flow,BorderLayout.SOUTH);
        setVisible(true);
    }
    /**
    public void DFS(TreeUniversity.Node node){
        node.visit();
        DFSresult+=node.name+"-";
        LinkedList<TreeUniversity.Node> nhb=framework.get(node);
        if(nhb==null){
            DFSresult+="-";
            return;
        }
        for(TreeUniversity.Node n:nhb){
            if(!n.visited) {
                DFS(n);
            }
        }

    }
     **/
    private TreePath selected;
    private class ChooseSelector implements TreeSelectionListener{

        @Override
        public void valueChanged(TreeSelectionEvent e) {
            selected=e.getPath();
        }
    }
    int counter=0;
    private class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(selected==null){
                return;
            }
            if(e.getSource()==add){
                TU.addEdge((TreeUniversity.Node)selected.getLastPathComponent(),new TreeUniversity.Node(jtfield.getText()));
                jf.repaint();
            }if(e.getSource()==addInfo){
                JFileChooser fileChooser=new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Only *.pdf","pdf"));
                fileChooser.showDialog(jf,"Approve");
                try {
                    PDFLoader pdfLoader=new PDFLoader(fileChooser.getSelectedFile());
                    TreeUniversity.Node temp=new TreeUniversity.Node(pdfLoader.name+"---"+pdfLoader.pages);
                    temp.info=pdfLoader.file;
                    TU.addEdge((TreeUniversity.Node)selected.getLastPathComponent(),temp);
                } catch (IOException ex) {
                    return;
                }
                jf.repaint();
            }if(e.getSource()==getInfo){
                if(((TreeUniversity.Node)selected.getLastPathComponent()).info==null){
                    return;
                }
                PDFLoader pdfLoader= null;
                try {
                    pdfLoader = new PDFLoader(((TreeUniversity.Node)selected.getLastPathComponent()).info);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                pdfLoader.openPDF();
            }

        }
    }
}
