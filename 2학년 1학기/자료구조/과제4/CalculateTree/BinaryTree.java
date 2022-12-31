import java.io.PrintStream;
import java.util.*;

public class BinaryTree<E> {
    private Node root;

    public BinaryTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node newRoot) {
        root = newRoot;
    }

    public boolean isEMpty() {
        return root == null;
    }

    public void preorder(Node n) {
        if (n != null) {
            if (n.getKey().getOperator() != null)
                System.out.print(n.getKey().getOperator() + " ");
            if (n.getKey().getValue() != 0 && n.getKey().getOperator() == null)
                System.out.print(n.getKey().getValue() + " ");
            preorder(n.getLeft());
            preorder(n.getRight());
        }
    }

    public void inorder(Node n) {
        if (n != null) {
            inorder(n.getLeft());
            if (n.getKey().getOperator() != null)
                System.out.print(n.getKey().getOperator() + " ");
            if (n.getKey().getValue() != 0 && n.getKey().getOperator() == null)
                System.out.print(n.getKey().getValue() + " ");
            inorder(n.getRight());
        }
    }

    public void postorder(Node n) {
        if (n != null) {
            postorder(n.getLeft());
            postorder(n.getRight());
            if (n.getKey().getOperator() != null)
                System.out.print(n.getKey().getOperator() + " ");
            else
                System.out.print(n.getKey().getValue() + " ");
        }
    }

    public void levelorder(Node root) {
        Queue<Node> q = new LinkedList<Node>();
        Node t;
        q.add(root);
        while (!q.isEmpty()) {
            t = q.remove();
            System.out.print(t.getKey() + " ");
            if (t.getLeft() != null)
                q.add(t.getLeft());
            if (t.getRight() != null)
                q.add(t.getRight());
        }
    }

    public int size(Node n) {
        if (n == null)
            return 0;
        else
            return (1 + size(n.getLeft()) + size(n.getRight()));
    }

    public void print(PrintStream os) {
        // source code
        // https://www.baeldung.com/java-print-binary-tree-diagram#1-pre-order-traversal
        os.print(traversePreOrder(root));
        System.out.println();
    }

    public String traversePreOrder(Node root) {

        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        if (root.getKey().getOperator() != null) {
            sb.append(root.getKey().getOperator());
            sb.append("[");
            sb.append(root.getKey().getValue());
            sb.append("]");
        }

        String pointerRight = "戌式式";
        String pointerLeft = (root.getRight() != null) ? "戍式式" : "戌式式";

        if (root.getLeft() != null)
            traverseNodes(sb, "", pointerLeft, root.getLeft(), root.getRight() != null);
        if (root.getRight() != null)
            traverseNodes(sb, "", pointerRight, root.getRight(), false);

        return sb.toString();
    }

    public void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            if (node.getKey().getOperator() != null) {
                if (node.getKey().getOperator().equals("x"))
                    sb.append(node.getKey().getOperator());
                else {
                    sb.append(node.getKey().getOperator());
                    sb.append("[");
                    sb.append(node.getKey().getValue());
                    sb.append("]");
                }
            } else {
                sb.append(node.getKey().getValue());
            }

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("弛  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "戌式式";
            String pointerLeft = (node.getRight() != null) ? "戍式式" : "戌式式";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.getLeft(), node.getRight() != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.getRight(), false);
        }
    }
}
