public class SparaseMatrixMain {
    public static void main(String[] args) {
        SparaseMatrix matrix1 = new SparaseMatrix();
        SparaseMatrix matrix2 = new SparaseMatrix();

        matrix1.insert();
        matrix2.insert();

        // matrix1.printMatrix();
        System.out.println();
        // matrix2.printMatrix();
        System.out.println();

        SparaseMatrix matrix3 = new SparaseMatrix();
        matrix3.multipleMatrix(matrix1, matrix2, matrix3);
        matrix3.printMatrix();
    }
}