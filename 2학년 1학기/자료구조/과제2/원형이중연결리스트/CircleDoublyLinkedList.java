import java.io.*;
import java.util.Scanner;

public class CircleDoublyLinkedList {
    private static class Node {
        private int studentNumber;
        private String name;
        private Node prev;
        private Node next;

        public Node() {
            prev = this;
            next = this;
        }

        public Node(int number, String name, Node prev, Node next) {
            studentNumber = number;
            this.name = name;
            this.prev = prev;
            this.next = next;
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        public String getName() {
            return name;
        }

        public Node getPrev() {
            return prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node node) {
            next = node;
        }

        public void setPrev(Node node) {
            prev = node;
        }
    }

    public CircleDoublyLinkedList() {
        head = new Node(); // dummy head O
    }

    private Node head;
    private Node cur;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return head.getNext() == head;
    }

    public void addFirst(int number, String name) {
        cur = head;
        Node newNode = new Node(number, name, cur, cur);
        cur.setNext(newNode);
        cur.setPrev(newNode);
        size++;
    }

    public void addAfter(int number, String name) {
        cur = compareToStudentNumber(number);
        if (cur == null)
            cur = head;
        Node newNode = new Node(number, name, cur, cur.getNext());
        cur.getNext().setPrev(newNode);
        cur.setNext(newNode);
        size++;
    }

    public Node compareToStudentNumber(int number) {
        cur = head.getNext();
        Node result = null;
        while (cur != head) {
            if (number > cur.getStudentNumber())
                result = cur;
            cur = cur.getNext();
        }
        return result; // null or cur
    }

    public void remove(int number) {
        cur = search(number);
        cur.getNext().setPrev(cur.getPrev());
        cur.getPrev().setNext(cur.getNext());
        cur = null;
        size--;
        if (getSize() == 0) {
            head.setNext(head);
            head.setPrev(head);
        }
    }

    public void printByAscend() {
        cur = head.getNext();
        while (cur != head) {
            System.out.println(cur.getStudentNumber() + " " + cur.getName());
            cur = cur.getNext();
        }
    } // 1,2,3, ...

    public void printByDescend() {
        cur = head.getPrev();
        while (cur != head) {
            System.out.println(cur.getStudentNumber() + " " + cur.getName());
            cur = cur.getPrev();
        }
    } // n, n-1, n-2, ...

    public Node search(int number) {
        cur = head.getNext();
        while (cur != head) {
            if (cur.getStudentNumber() == number)
                return cur;
            cur = cur.getNext();
        }
        return null;
    }

    public void printByInputNumber(int number) {
        cur = search(number);
        Node printEnd = search(number).getPrev();
        if (printEnd == head)
            printEnd = head.getPrev();

        if (cur == null) {
            System.out.println("wrong studentNumber");
            return;
        }
        for (int i = 0; i < getSize(); i++) {
            if (cur == head)
                cur = cur.getNext();
            System.out.println(cur.getStudentNumber() + " " + cur.getName());
            cur = cur.getNext();
        }
    }

    public void open(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "EUC_KR"));

            String line;
            String[] input;
            int strToInt;
            while ((line = br.readLine()) != null) {
                input = line.split(" ");
                strToInt = Integer.valueOf(input[0]);
                if (getSize() == 0)
                    addFirst(strToInt, input[1]);
                else
                    addAfter(strToInt, input[1]);
            }
            br.close();
        } catch (IOException e) {

        }
    }

    public void save(String path) {
        try {
            cur = head.getNext();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "EUC_KR"));
            for (int i = 0; i < getSize() && cur != null; i++) {
                bw.write(cur.getStudentNumber() + " " + cur.getName() + "\n");
                cur = cur.getNext();
            }
            bw.close();
        } catch (IOException e) {
        }
    }

    public void mainMenu(String path) {
        open(path);
        Scanner s = new Scanner(System.in, "EUC_KR");
        System.out.println("==================================");
        System.out.println("(1) new student input");
        System.out.println("(2) student delete");
        System.out.println("(3) print order by studentNumber");
        System.out.println("(4) print reverse order by studentNumber");
        System.out.println("(5) print order by input studentNumber");
        System.out.println("(6) save in file and exit");
        System.out.println("==================================");

        while (true) {
            System.out.print("input number : ");
            char command = s.next().charAt(0);
            int number;
            String name;
            switch (command) {
            case '1':
                System.out.print("input studentNumber and name : ");
                number = s.nextInt();
                name = s.next();
                if (getSize() == 0)
                    addFirst(number, name);
                else
                    addAfter(number, name);
                break;
            case '2':
                System.out.print("input studentNumber : ");
                number = s.nextInt();
                remove(number);
                break;
            case '3':
                printByAscend();
                break;
            case '4':
                printByDescend();
                break;
            case '5':
                System.out.print("input studentNumber : ");
                number = s.nextInt();
                if (search(number) == null)
                    break;
                printByInputNumber(number);
                break;
            case '6':
                s.close();
                System.out.print("exit");
                save(path);
                return;
            default:
                System.out.println("wrong input");
                break;
            }
        }
    }
}