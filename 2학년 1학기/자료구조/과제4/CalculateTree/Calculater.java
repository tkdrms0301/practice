class Calculator {
    private ArrayStack<String> stack;
    private ArrayQueue<Token> queue;
    private BinaryTree<Node> BinaryTree;
    final int operand_Num = -1;
    final int unaryOp_Num = 7;
    final int bracketLeft_Num = 0;
    final int bracketRight_Num = 8;

    public Calculator() {
        BinaryTree = new BinaryTree<Node>();
    }

    public void print() {
        System.out.print("--- postfix is : ");
        BinaryTree.postorder(BinaryTree.getRoot());
        System.out.println();
        System.out.print("--- infix is : ");
        BinaryTree.preorder(BinaryTree.getRoot());
        System.out.println();
        BinaryTree.print(System.out);
        System.out.print("--- result is : " + BinaryTree.getRoot().getKey().getValue());
        System.out.println();
    }

    public String[] unaryOpReplace(String[] notation) {
        for (int i = 0; i < notation.length; i++) { // replace 단항연산자 - to m
            if (notation[i].equals("-")) {
                if (i == 0 && notation[i].equals("-")) { // 처음 - 가왔을때
                    notation[i] = "m";
                } else { // 연산자나머지
                    if (inStackPriority(notation[i - 1]) != -1) { // 이전 항이 연산자인경우
                        if (inStackPriority(notation[i - 1]) == 8) // ) 인경우 - 가 이항연산자임
                            continue;
                        notation[i] = "m";
                    }
                }
            }
        }
        return notation;
    }

    public void transPostfix(String notation) {
        stack = new ArrayStack<String>();
        queue = new ArrayQueue<Token>();
        String[] notationSplit = unaryOpReplace(notation.split(" "));
        for (int i = 0; i < notationSplit.length; i++) {
            int isp = inStackPriority(notationSplit[i]);
            if (isp == operand_Num) {
                queue.add(new Token(Integer.parseInt(notationSplit[i])));
            } else {
                if (isp == bracketLeft_Num) {
                    stack.push(notationSplit[i]);
                } else if (inStackPriority(notationSplit[i]) == bracketRight_Num) { // ) 이면 ( 일때까지 모든 연산자 출력
                    while (inStackPriority(stack.peek()) != bracketLeft_Num) {
                        queue.add(new Token(stack.pop()));
                    }
                    stack.pop(); // "(" pop()
                } else {
                    if (stack.isEmpty()) {
                        stack.push(notationSplit[i]);
                    } else {
                        if (inStackPriority(notationSplit[i]) == unaryOp_Num) {
                            stack.push(notationSplit[i]);
                        } else if (inStackPriority(notationSplit[i]) <= inStackPriority(stack.peek())) {
                            while (!stack.isEmpty() && inStackPriority(stack.peek()) == unaryOp_Num) {
                                queue.add(new Token(stack.pop()));
                            }
                            if (!stack.isEmpty())
                                queue.add(new Token(stack.pop()));
                            stack.push(notationSplit[i]);
                        } else {
                            stack.push(notationSplit[i]);
                        }
                    }
                }
            }
        }
        while (!stack.isEmpty())
            queue.add(new Token(stack.pop()));
    }

    public void queueToTree() {
        ArrayStack<Node> arrayStack = new ArrayStack<Node>();
        while (!queue.isEmpty()) {
            Token token = queue.remove();
            Node nodeFront;
            Node nodeRear;
            Node nullNode;
            if (token.getOperator() == null) {
                arrayStack.push(new Node(token, null, null));
            }
            if (token.getOperator() != null && token.getValue() == 0) {
                if (inStackPriority(token.getOperator()) == unaryOp_Num) {
                    nodeFront = arrayStack.pop();
                    nullNode = new Node(new Token("x"), null, null);
                    unaryOpCal(token, nodeFront.getKey().getValue());
                    arrayStack.push(new Node(token, nodeFront, nullNode));
                } else {
                    nodeRear = arrayStack.pop();
                    nodeFront = arrayStack.pop();
                    binaryOpCal(token, nodeFront.getKey().getValue(), nodeRear.getKey().getValue());
                    arrayStack.push(new Node(token, nodeFront, nodeRear));
                }
            }
        }
        BinaryTree.setRoot(arrayStack.pop());
    }

    public int inStackPriority(String operator) {
        switch (operator) {
            case "(":
                return 0;
            case ")":
                return 8;
            case "~":
                return 7;
            case "m":
                return 7;
            case "*":
                return 6;
            case "/":
                return 6;
            case "%":
                return 6;
            case "+":
                return 5;
            case "-":
                return 5;
            case "<<":
                return 4;
            case ">>":
                return 4;
            case "&":
                return 3;
            case "^":
                return 2;
            case "|":
                return 1;
            default:
                return -1;
        }
    }

    public void unaryOpCal(Token operator, int operand) {
        switch (operator.getOperator()) {
            case "~":
                operator.setValue(~operand);
                break;
            case "m":
                operator.setValue(-operand);
                break;
        }
    }

    public void binaryOpCal(Token operator, int operandFront, int operandRear) {
        switch (operator.getOperator()) {
            case "*":
                operator.setValue(operandFront * operandRear);
                break;
            case "/":
                operator.setValue(operandFront / operandRear);
                break;
            case "%":
                operator.setValue(operandFront % operandRear);
                break;
            case "+":
                operator.setValue(operandFront + operandRear);
                break;
            case "-":
                operator.setValue(operandFront - operandRear);
                break;
            case "<<":
                operator.setValue(operandFront << operandRear);
                break;
            case ">>":
                operator.setValue(operandFront >> operandRear);
                break;
            case "&":
                operator.setValue(operandFront & operandRear);
                break;
            case "^":
                operator.setValue(operandFront ^ operandRear);
                break;
            case "|":
                operator.setValue(operandFront | operandRear);
                break;
        }
    }
}