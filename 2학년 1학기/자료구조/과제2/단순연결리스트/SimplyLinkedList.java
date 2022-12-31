import java.io.*;
import java.util.*;

public class SimplyLinkedList {
    private class Node {
        private int studentNumber;
        private String name;
        private String clubName;
        private Node clubNodeHead;
        private Node curClubNode;
        private Node next;

        public Node(String club, Node n) {
            clubName = club;
            next = n;
        }

        public Node(int studentNumber, String name, String club, Node n) {
            this.studentNumber = studentNumber;
            this.name = name;
            next = n;

            if (club == null)
                return;
            String[] inputClub = club.split("/");
            for (int i = 0; i < inputClub.length; i++) {
                if (i == 0) {
                    Node newNode = new Node(inputClub[i], null);
                    clubNodeHead = newNode;
                    curClubNode = newNode;
                } else {
                    Node newNode = new Node(inputClub[i], null);
                    curClubNode.setNext(newNode);
                    curClubNode = newNode;
                }

            }
        }

        public int getStudentNumber() {
            return studentNumber;
        }

        public String getName() {
            return name;
        }

        public String getClubName() {
            return clubName;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node n) {
            next = n;
        }
    }

    private Node head = null;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public SimplyLinkedList() {
    }

    public void addFirst(int number, String name, String club) {
        Node newNode = new Node(number, name, club, null);
        head = newNode;
        size++;
    }

    public void addAfter(int number, String name, String club, Node makeNewNode) {
        if (compareToStudentNumber(number) == -1) {
            Node newNode = new Node(number, name, club, makeNewNode);
            head = newNode;
        } else {
            Node newNode = new Node(number, name, club, makeNewNode.getNext());
            makeNewNode.setNext(newNode);
        }
        size++;
    }

    public int search(int number) {
        Node cur = head;
        for (int i = 0; i < size && cur != null; cur = cur.getNext(), i++) {
            if (number == cur.getStudentNumber())
                return i;
        }
        return -1;
    }

    public void removeFirst(int number) {
        if (isEmpty())
            return;
        Node curNode = getCurNode(search(number));
        head = curNode.getNext();
        size--;
    }

    public void removeAfter(int number) {
        Node prevNode = getCurNode(search(number) - 1);
        Node curNode = getCurNode(search(number));
        if (prevNode == null)
            return;
        prevNode.setNext(curNode.getNext());
        curNode.setNext(null);
        size--;
    }

    public int compareToStudentNumber(int number) {
        Node cur = head;
        int result = -1;
        for (int i = 0; i < size && cur != null; cur = cur.getNext(), i++) {
            if (number > cur.getStudentNumber())
                result = i;
        }
        if (result >= 0)
            return result;
        return -1;
    }

    public Node getCurNode(int number) {
        Node cur = head;
        for (int i = 0; i < number; i++) {
            cur = cur.getNext();
        }
        return cur;
    }

    public void print() {
        Node cur = head;
        Node curClub = null;
        for (int i = 0; i < size && cur != null; cur = cur.getNext(), i++) {
            System.out.print(cur.getStudentNumber() + " " + cur.getName() + " ");
            if (cur.clubNodeHead != null) {
                curClub = cur.clubNodeHead;
                while (true) {
                    System.out.print(curClub.getClubName());
                    curClub = curClub.getNext();
                    if (curClub == null) {
                        break;
                    }
                    System.out.print("/");
                }
                curClub = null;
            }

            System.out.println("");
        }
    }

    public void open(String path) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "EUC_KR"));

            String line;
            String[] input;
            int strToInt;
            while ((line = br.readLine()) != null) {
                input = line.split(" ", 3);
                strToInt = Integer.valueOf(input[0]);
                String[] inputCopy = new String[3];
                for (int i = 0; i < input.length; i++) {
                    inputCopy[i] = input[i];
                }
                if (getSize() == 0)
                    addFirst(strToInt, inputCopy[1], inputCopy[2]);
                else {
                    addAfter(strToInt, inputCopy[1], inputCopy[2], getCurNode(compareToStudentNumber(strToInt)));
                }
            }
            br.close();
        } catch (IOException e) {
        }
    }

    public void save(String path) {
        try {
            System.out.print("exit");
            Node cur = head;
            Node curClub = null;
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "EUC_KR"));
            for (int i = 0; i < size && cur != null; cur = cur.getNext(), i++) {
                bw.write(cur.getStudentNumber() + " " + cur.getName() + " ");
                if (cur.clubNodeHead != null) {
                    curClub = cur.clubNodeHead;
                    while (true) {
                        bw.write(curClub.getClubName());
                        curClub = curClub.getNext();
                        if (curClub == null) {
                            break;
                        }
                        bw.write("/");
                    }
                    curClub = null;
                }
                bw.write("\n");
            }
            bw.close();
        } catch (IOException e) {

        }
        return;
    }

    public void mainMenu(String path) {
        open(path);
        Scanner s = new Scanner(System.in, "EUC_KR");
        System.out.println("==================================");
        System.out.println("(1) new student input");
        System.out.println("(2) student delete");
        System.out.println("(3) print order by studentNumber");
        System.out.println("(4) save file and exit");
        System.out.println("==================================");

        while (true) {
            System.out.print("input number : ");
            char command = s.next().charAt(0);
            int number;
            String name;
            String club;
            switch (command) {
            case '1':
                System.out.print("input studentNumber, name, club : ");
                number = s.nextInt();
                name = s.next();
                club = s.nextLine();
                if (isEmpty())
                    addFirst(number, name, club);
                else {
                    addAfter(number, name, club, getCurNode(compareToStudentNumber(number)));
                }
                break;
            case '2':
                System.out.print("input studentNumber : ");
                number = s.nextInt();
                if (search(number) == -1)
                    break;
                else if (search(number) == 0)
                    removeFirst(number);
                else
                    removeAfter(number);
                break;
            case '3':
                print();
                break;
            case '4':
                s.close();
                save(path);
                return;
            default:
                System.out.println("wrong input");
                break;
            }
        }
    }
}
