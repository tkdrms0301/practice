public class Node {
    private Token item;
    private Node left;
    private Node right;

    public Node(Token newItem, Node lt, Node rt) {
        item = newItem;
        left = lt;
        right = rt;
    }

    public Token getKey() {
        return item;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public void setKey(Token newItem) {
        item = newItem;
    }

    public void setLeft(Node lt) {
        left = lt;
    }

    public void setRight(Node rt) {
        right = rt;
    }
}