public class Node<Key extends Comparable<Key>> {
    private Key name;
    private Node<Key> left, right;

    public Node(Key newName) {
        name = newName;
        left = right = null;
    }

    public Key getName() {
        return name;
    }

    public void setName(Key name) {
        this.name = name;
    }

    public Node<Key> getLeft() {
        return left;
    }

    public Node<Key> getRight() {
        return right;
    }

    public void setLeft(Node<Key> left) {
        this.left = left;
    }

    public void setRight(Node<Key> right) {
        this.right = right;
    }
}
