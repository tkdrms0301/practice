package URLParser;

import java.util.List;

public class URLTree {
    private Node rootNode;
    private String protocol;
    private final static int PROTOCOL = 0;
    private final static int BASE_URL = 1;
    private final static int FISRT_URL = 2;

    public URLTree (){
        rootNode = new Node();
    }

    public Node getRootNode(){
        return rootNode;
    }

    public void setRootNode(String data){
        String[] splitedData = data.split("//|/");
        setProtocol(splitedData[PROTOCOL]);
        rootNode = new Node(splitedData[BASE_URL]);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void addNode(String data){
        if(data == null)
            return;
        String[] splitedData = data.split("//|/"); // "//" | "/" 문자열 기준으로 split

        // split된 문자열 배열 길이는 2보다 큼 && protocol, baseUrl이 동일
        if(splitedData.length > FISRT_URL && (splitedData[PROTOCOL].compareTo(getProtocol()) == 0  && splitedData[BASE_URL].compareTo(getRootNode().getData()) == 0)){
            addNode(splitedData);
        }
    }

    private void addNode(String[] data){
        Node curNode = getRootNode();
        for(int i = FISRT_URL; i < data.length; i++){
            if(!curNode.containData(data[i])){
                curNode.addData(data[i]);
            }
            curNode = curNode.nextChild(data[i]);
        }
    }

    // 노드 순회하기 현재 1. 노드  2. 자식 노드
    public void toList(Node node, String url, List<String> arr){
        String result = url + "/" + node.getData();
        arr.add(result);
        for(int i = 0; i < node.nodeSize(); i++){
            toList(node.getChildNode().get(i), result, arr);
        }
    }
}
