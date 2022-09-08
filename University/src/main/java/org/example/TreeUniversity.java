package org.example;

import gherkin.lexer.No;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class TreeUniversity {
    public static class Node{

        String name;
        public boolean visited;
        public Node(String name){

            this.name=name;
            visited=false;
        }
        File info;
        public void visit(){
            visited=true;
        }
        public void unvisit(){
            visited=false;
        }

        @Override
        public String toString() {
            return name;
        }
    }
    public HashMap<Node,LinkedList<Node>> framework;
    public LinkedList<Node> resultDFS;
    public TreeUniversity(){
        framework=new HashMap<>();
        resultDFS = new LinkedList<>();
    }
    public void addEdge(Node source,Node destination){
        if(!framework.keySet().contains(source)){
            framework.put(source,null);
        }
        if(!framework.keySet().contains(destination)){
            framework.put(destination,null);
        }
        LinkedList<Node> temp=framework.get(source);
        if(temp!=null){
            temp.remove(destination);
        }else temp=new LinkedList<>();
        temp.add(destination);
        framework.put(source,temp);
    }

    @Override
    public String toString() {
        return super.toString();
    }
    public void DFS(Node root){
        if(framework.get(root)==null){
            return;
        }
        for (Node n:framework.get(root)){
            resultDFS.add(n);
            DFS(n);

        }

    }

    public void resetAll(){
        for(Node n:framework.keySet()){
            n.unvisit();
        }
    }
    public void clearAllBranchFrom(Node rootNode){
        resultDFS.clear();
        framework.remove(rootNode);
        DFS(framework.keySet().stream().findFirst().get());
    }
}
