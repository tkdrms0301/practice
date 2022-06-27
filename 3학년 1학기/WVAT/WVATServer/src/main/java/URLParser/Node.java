package URLParser;

import java.util.*;

public class Node {
    private String data;
    private List<Node> child;

    public Node(){
        data = null;
        child = new ArrayList<>();
    }

    public Node(String newData){
        data = newData;
        child = new ArrayList<>();
    }

    public int nodeSize(){
        return child.size();
    }

    public String getData(){
        return data;
    }

    public void setData(String newData){
        data = newData;
    }

    public List<Node> getChildNode(){
        return child;
    }

    public void setChild(List<Node> newChild){
        child = newChild;
    }

    public void addData(String data){
        child.add(new Node(data));
    }

    public boolean containData(String data){
        for (int i = 0; i < child.size(); i++){
            if(child.get(i).getData().compareTo(data) == 0){
                return true;
            }
        }
        return false;
    }

    public Node nextChild(String data){
        for (Node node : child){
            if(node.getData().compareTo(data) == 0){
                return node;
            }
        }
        return null;
    }
}
