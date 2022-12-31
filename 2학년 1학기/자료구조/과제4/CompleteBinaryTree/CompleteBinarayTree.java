public class CompleteBinarayTree<E> {
    private int[] tree;
    private int treeSize;
    private int rootNode;

    ArrayQueue<Integer> arrayQueue;

    public CompleteBinarayTree(int nodeSize) {
        treeSize = nodeSize;
        tree = new int[nodeSize + 1];
        for (int i = 1; i <= nodeSize; i++) {
            tree[i] = i;
        }
        rootNode = tree[1];
    }

    public int getRootNode() {
        return rootNode;
    }

    public int getTreeSize() {
        return treeSize;
    }

    public int getLeft(int index) {
        return 2 * index;
        // if(2i ¡Â N) N is the number of node a[i]'s left node is a[2i]
    }

    public int getRight(int index) {
        return 2 * index + 1;
        // if(2i + 1 ¡Â N) N is the number of node a[i]'s left node is a[2i + 1]
    }

    public void preorder(int nodeNum) {
        if (nodeNum < tree.length && tree[nodeNum] != 0) {
            System.out.print(tree[nodeNum] + " ");
            preorder(getLeft(nodeNum));
            preorder(getRight(nodeNum));
        }
    }

    public void inorder(int nodeNum) {
        if (nodeNum < tree.length && tree[nodeNum] != 0) {
            inorder(getLeft(nodeNum));
            System.out.print(tree[nodeNum] + " ");
            inorder(getRight(nodeNum));
        }
    }

    public void postorder(int nodeNum) {
        if (nodeNum < tree.length && tree[nodeNum] != 0) {
            postorder(getLeft(nodeNum));
            postorder(getRight(nodeNum));
            System.out.print(tree[nodeNum] + " ");
        }
    }

    public void levelorder(int nodeNum) {
        arrayQueue = new ArrayQueue<Integer>();
        for (int i = nodeNum; i < tree.length; i++) {
            arrayQueue.add(tree[i]);
        }
        int tmp;
        while (!arrayQueue.isEmpty()) {
            tmp = arrayQueue.remove();
            System.out.print(tmp + " ");
        }
    }

    public void treeDraw() { // source : 20180661 ¾È»ó±Ù
        int height = logN(getTreeSize()) + 1; // tree height

        String[] printTreeNumber = new String[height];
        String[] printMark = new String[height];
        arrayQueue = new ArrayQueue<Integer>();
        preorderInQueue(getRootNode());

        for (int i = 0; i < printTreeNumber.length; i++) {
            printTreeNumber[i] = "";
            printMark[i] = "";
        }
        int nodeSize = arrayQueue.getSize();
        int tmp = arrayQueue.remove();
        int tmpNext;

        while (true) {
            tmpNext = arrayQueue.remove();
            int index = logN(tmp);
            printTreeNumber[index] = printTreeNumber[index] + Integer.toString(tmp) + " ";
            if (tmp == nodeSize && nodeSize % 2 == 0) {
                printTreeNumber[index] = printTreeNumber[index] + "x ";
            }
            if (tmp > tmpNext) {
                for (int i = 0; i < printTreeNumber.length; i++) {
                    while (printTreeNumber[index].length() != printTreeNumber[i].length()) {
                        printTreeNumber[i] = printTreeNumber[i] + " ";
                    }
                }
            }

            tmp = tmpNext;
            if (arrayQueue.isEmpty()) {
                break;
            }
        }
        printTreeNumber[logN(tmp)] = printTreeNumber[logN(tmp)] + Integer.toString(tmp) + " ";
        if (tmp == nodeSize && nodeSize % 2 == 0) {
            printTreeNumber[logN(tmp)] = printTreeNumber[logN(tmp)] + "x ";
        }

        for (int i = 0; i < printTreeNumber.length; i++) {
            printTreeNumber[i] = printTreeNumber[i].trim();
            int count = 0;
            for (int j = 0; j < printTreeNumber[i].length(); j++) {
                char index = printTreeNumber[i].charAt(j);
                if (index >= '0' && index <= '9') {
                    if (j == 0) {
                        printMark[i] = printMark[i] + "+";
                        count++;
                    } else {
                        if (printTreeNumber[i].charAt(j - 1) == ' ') {
                            printMark[i] = printMark[i] + "+";
                            count++;
                        } else {
                            if (count % 2 == 1)
                                printMark[i] = printMark[i] + "-";
                            else
                                printMark[i] = printMark[i] + " ";
                        }
                    }
                } else if (index == 'x') {
                    printMark[i] = printMark[i] + "+";
                    count++;
                } else {
                    if (count % 2 == 1)
                        printMark[i] = printMark[i] + "-";
                    else
                        printMark[i] = printMark[i] + " ";
                }
            }
        }

        System.out.println(printTreeNumber[0]);
        for (int i = 1; i < printTreeNumber.length; i++) {
            System.out.println(printMark[i]);
            System.out.println(printTreeNumber[i]);
        }
    }

    public void preorderInQueue(int nodeNum) {
        if (nodeNum < tree.length && tree[nodeNum] != 0) {
            arrayQueue.add(tree[nodeNum]);
            preorderInQueue(getLeft(nodeNum));
            preorderInQueue(getRight(nodeNum));
        }
    }

    public int logN(int num) {
        return (int) (Math.log(num) / Math.log(2));
    }

    public void printTreeNumber() {
        treeDraw();

        System.out.print("preorder : ");
        preorder(getRootNode());
        System.out.println();

        System.out.print("inorder : ");
        inorder(getRootNode());
        System.out.println();

        System.out.print("postorder : ");
        postorder(getRootNode());
        System.out.println();

        System.out.print("levelorder : ");
        levelorder(getRootNode());
        System.out.println();
    }
}