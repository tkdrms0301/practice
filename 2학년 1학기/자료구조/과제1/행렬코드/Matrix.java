import java.lang.Math;

public class Matrix {
    private int[][] matrix;

    public Matrix(int row, int coloumn) {
        matrix = new int[row][coloumn];
    }

    public void setMatrixAttribute(Matrix matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[i][j] = (int) (Math.random() * 1000) + 1;
            }
        }
    }

    public int getMatrixAttribute(Matrix matrix, int row, int coloumn) {
        return this.matrix[row][coloumn];
    }

    public void matrixPrint() {
        for (int i = 0; i < 4; i++) {
            System.out.print("| ");
            for (int j = 0; j < 4; j++) {
                System.out.printf("%3d" + " ", matrix[i][j]);
            }
            System.out.print("|");
            System.out.println("");
        }
        System.out.println("");
    }

    public void printRow(int line) {
        System.out.print("| ");
        for (int i = 0; i < 4; i++) {
            System.out.printf("%3d" + " ", matrix[line][i]);
        }
    }

    public void matrixRight(Matrix matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[j][3 - i] = matrix.getMatrixAttribute(matrix, i, j);
            }
        }
    }

    public void matrixLeft(Matrix matrix) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.matrix[3 - j][i] = matrix.getMatrixAttribute(matrix, i, j);
            }
        }
    }

    public void matrixTransposition(Matrix matrix, int count) {
        if (count > 4)
            return;
        int i = count;
        int j = count;
        while (i < 4) {
            this.matrix[i][j] = matrix.getMatrixAttribute(matrix, j, i);
            i++;
        }
        i = count;
        while (j < 4) {
            this.matrix[i][j] = matrix.getMatrixAttribute(matrix, j, i);
            j++;
        }
        j = count;
        matrixTransposition(matrix, count + 1);
    }
}