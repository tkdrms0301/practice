import java.io.PrintStream;

public class BinarySearchTree<Key extends Comparable<Key>> {
    public Node<Key> root;

    public Node<Key> getRoot() {
        return root;
    }

    public BinarySearchTree() {

    }

    public void setRoot(Key newName) {
        root = new Node<Key>(newName);
    }

    public Key get(Key k) {
        return get(root, k);
    }

    public Key get(Node<Key> n, Key k) {
        if (n == null)
            return null;
        int t = n.getName().compareTo(k);
        if (t > 0)
            return get(n.getLeft(), k);
        else if (t < 0)
            return get(n.getRight(), k);
        else
            return (Key) n.getName();
    }

    public void put(Key k) {
        root = put(root, k);
    }

    public Node<Key> put(Node<Key> n, Key k) {
        if (n == null)
            return new Node(k);
        int t = n.getName().compareTo(k);
        if (t > 0)
            n.setLeft(put(n.getLeft(), k));
        else if (t < 0)
            n.setRight(put(n.getRight(), k));
        else
            n.setName(k);
        return n;
    }

    public void print(PrintStream os) {
        // source code
        // https://www.baeldung.com/java-print-binary-tree-diagram#1-pre-order-traversal
        os.print(traversePreOrder(root));
        System.out.println();
    }

    public String traversePreOrder(Node<Key> root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.getName());

        String pointerRight = "戌式式";
        String pointerLeft = (root.getRight() != null) ? "戍式式" : "戌式式";

        if (root.getLeft() != null)
            traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        else {
            sb.append("\n");
            sb.append("戌式式");
            sb.append("x");
        }
        if (root.getRight() != null)
            traverseNodes(sb, "", pointerRight, root.getRight(), false);
        else {
            sb.append("\n");
            sb.append("戌式式");
            sb.append("x");
        }
        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.getName());

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("弛  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "戌式式";
            String pointerLeft = (node.getRight() != null) ? "戍式式" : "戌式式";

            if (node.getLeft() == null && node.getRight() == null)
                return;
            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        } else {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append("x");
        }
    }
}