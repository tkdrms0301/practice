import java.util.Scanner;

class Calculator {
    private ListStack<Integer> operand;
    private ListStack<String> notationToPostfix;
    private ListStack<String> invalidCheckBracket;
    private String[] notationSplit;
    private String postfixNotation;
    private int calResult;

    public Calculator() {
    }

    public void input() {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.print("input notation with spacebar (quit is exit program) : ");
            String inputNotation = s.nextLine();
            if (inputNotation.equals("quit")) {
                System.out.print("exit");
                break;
            }
            transPostfix(inputNotation);
            calculatePostfix();
            printPostfix();
        }
        s.close();
    }

    public void printPostfix() {
        System.out.print("postfixNotation is : ");
        if (invalidCheck() == true)
            System.out.println(postfixNotation);
        else
            System.out.println("wrong bracket");
        System.out.print("result is : ");
        System.out.println(calResult);
    }

    public void inputPostfixNotation(String op) {
        postfixNotation = postfixNotation + op + " ";
    }

    public void unaryOpReplace() {
        for (int i = 0; i < notationSplit.length; i++) { // replace 단항연산자 - to m
            if (notationSplit[i].equals("-")) {
                if (i == 0 && notationSplit[i].equals("-")) { // 처음 - 가왔을때
                    notationSplit[i] = "m";
                } else { // 연산자나머지
                    if (inStackPriority(notationSplit[i - 1]) != -1) { // 이전 항이 연산자인경우
                        if (inStackPriority(notationSplit[i - 1]) == 8) // ) 인경우 - 가 이항연산자임
                            continue;
                        notationSplit[i] = "m";
                    }
                }
            }
        }
    }

    public boolean invalidCheck() { // OTL
        invalidCheckBracket = new ListStack<>();
        for (int i = 0; i < notationSplit.length; i++) {
            if (notationSplit[i].equals("(")) {
                invalidCheckBracket.push(notationSplit[i]);
            }
            if (notationSplit[i].equals(")")) {
                invalidCheckBracket.pop();
            }
        }
        if (invalidCheckBracket.isEmpty())
            return true;
        else
            return false;
    }

    public void transPostfix(String notation) {
        notationToPostfix = new ListStack<>();
        postfixNotation = "";
        notationSplit = notation.split(" ");
        unaryOpReplace();
        for (int i = 0; i < notationSplit.length; i++) {
            if (inStackPriority(notationSplit[i]) == -1) { // operand
                inputPostfixNotation(notationSplit[i]);
            } else { // operator
                if (inStackPriority(notationSplit[i]) == 0) {
                    notationToPostfix.push(notationSplit[i]);
                } else if (inStackPriority(notationSplit[i]) == 8) { // ) 이면 ( 일때까지 모든 연산자 출력
                    while (inStackPriority(notationToPostfix.peek()) != 0) {
                        inputPostfixNotation(notationToPostfix.pop());
                    }
                    notationToPostfix.pop(); // "(" pop()
                } else {
                    if (notationToPostfix.isEmpty()) {
                        notationToPostfix.push(notationSplit[i]);
                    } else {
                        if (inStackPriority(notationSplit[i]) == 7) {
                            notationToPostfix.push(notationSplit[i]);
                        } else if (inStackPriority(notationSplit[i]) <= inStackPriority(notationToPostfix.peek())) {
                            while (!notationToPostfix.isEmpty() && inStackPriority(notationToPostfix.peek()) == 7) {
                                inputPostfixNotation(notationToPostfix.pop());
                            }
                            if (!notationToPostfix.isEmpty())
                                inputPostfixNotation(notationToPostfix.pop());
                            notationToPostfix.push(notationSplit[i]);
                        } else {
                            notationToPostfix.push(notationSplit[i]);
                        }
                    }
                }
            }
        }
        while (!notationToPostfix.isEmpty())
            inputPostfixNotation(notationToPostfix.pop());
    }

    public void calculatePostfix() {
        operand = new ListStack<>();
        String[] postfix = postfixNotation.split(" ");
        for (int i = 0; i < postfix.length; i++) {
            if (inStackPriority(postfix[i]) == -1) {
                operand.push(Integer.parseInt(postfix[i]));
            } else {
                calculate(postfix[i]);
            }
        }
        calResult = operand.pop();
    }

    public void calculate(String operator) {
        int operandBefore = 0;
        int operandAfter;
        if (inStackPriority(operator) == 7) {
            operandAfter = operand.pop();
        } else {
            operandAfter = operand.pop();
            operandBefore = operand.pop();
        }
        switch (operator) {
        case "~":
            operand.push(~operandAfter);
            break;
        case "m":
            operand.push(-operandAfter);
            break;
        case "*":
            operand.push(operandBefore * operandAfter);
            break;
        case "/":
            operand.push(operandBefore / operandAfter);
            break;
        case "%":
            operand.push(operandBefore % operandAfter);
            break;
        case "+":
            operand.push(operandBefore + operandAfter);
            break;
        case "-":
            operand.push(operandBefore - operandAfter);
            break;
        case "<<":
            operand.push(operandBefore << operandAfter);
            break;
        case ">>":
            operand.push(operandBefore >> operandAfter);
            break;
        case "&":
            operand.push(operandBefore & operandAfter);
            break;
        case "^":
            operand.push(operandBefore ^ operandAfter);
            break;
        case "|":
            operand.push(operandBefore | operandAfter);
            break;
        }
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
}