import java.util.*;

public class SparaseMatrix {
    int[][] matrix;
    int term;

    Scanner s = new Scanner(System.in);

    SparaseMatrix() {
        term = 0;
        matrix = new int[1][2];
    }

    public void insert() {
        System.out.println("input coeffient, exponent / if (exponent==0) exit");
        while (true) {
            if (matrix.length == term)
                resize(matrix.length * 2);
            int coef = s.nextInt();
            int exp = s.nextInt();
            if (coef == 0 && exp == 0)
                break;
            matrix[term][0] = coef;
            matrix[term][1] = exp;

            term++;
            if (matrix[term - 1][1] == 0)
                break;

        }
    }

    public void insert(int coef, int exp) {
        if (matrix.length == term)
            resize(matrix.length * 2);
        for (int i = 0; i < term; i++) {
            if (matrix[i][1] < exp) {
                shift(i);
                matrix[i][0] = coef;
                matrix[i][1] = exp;
                term++;
                return;
            } else if (matrix[i][1] == exp) {
                matrix[i][0] += coef;
                if (matrix[i][0] == 0) {
                    matrix[i][1] = 0;
                    term--;
                }
                return;
            }
        }
        if (matrix[term][0] == 0 && matrix[term][1] == 0) {
            matrix[term][0] = coef;
            matrix[term][1] = exp;
            term++;
        }
    }

    public void shift(int shiftTerm) {
        if (matrix.length == term)
            resize(matrix.length * 2);
        for (int i = term - 1; i >= shiftTerm; i--) {
            matrix[i + 1][0] = matrix[i][0];
            matrix[i + 1][1] = matrix[i][1];
        }
    }

    public void resize(int size) {
        int[][] arr = new int[size][2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                arr[i][j] = matrix[i][j];
            }
        }
        matrix = arr;
    }

    public int getAttribute(int coef, int exp) {
        return matrix[coef][exp];
    }

    public void multipleMatrix(SparaseMatrix matrix1, SparaseMatrix matrix2, SparaseMatrix matrix3) {
        for (int i = 0; i < matrix2.term; i++) {
            for (int j = 0; j < matrix1.term; j++) {
                int coef = matrix1.getAttribute(j, 0) * matrix2.getAttribute(i, 0);
                int exp = matrix1.getAttribute(j, 1) + matrix2.getAttribute(i, 1);
                matrix3.insert(coef, exp);
            }
        }
    }

    public void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0)
                break;
            if (matrix[i][0] == 1) {
                if (i == term - 1)
                    System.out.printf("x^" + "%d", matrix[i][1]);
                else
                    System.out.printf("x^" + "%d + ", matrix[i][1]);
            } else if (matrix[i][0] == -1) {
                if (i == term - 1)
                    System.out.printf("-" + "x^" + "%d", matrix[i][1]);
                else
                    System.out.printf("-" + "x^" + "%d + ", matrix[i][1]);
            } else {
                if (i == term - 1)
                    System.out.printf("%d" + "x^" + "%d", matrix[i][0], matrix[i][1]);
                else
                    System.out.printf("%d" + "x^" + "%d + ", matrix[i][0], matrix[i][1]);
            }
        }
        System.out.println();
    }
}