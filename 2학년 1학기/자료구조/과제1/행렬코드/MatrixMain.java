public class MatrixMain {
    public static void main(String[] args) {

        Matrix srcMat = new Matrix(4, 4);
        srcMat.setMatrixAttribute(srcMat);
        Matrix rightMat = new Matrix(4, 4);
        Matrix leftMat = new Matrix(4, 4);
        Matrix transMat = new Matrix(4, 4);

        rightMat.matrixRight(srcMat);
        leftMat.matrixLeft(srcMat);
        transMat.matrixTransposition(srcMat, 0);

        srcMat.matrixPrint();
        rightMat.matrixPrint();
        leftMat.matrixPrint();
        transMat.matrixPrint();
        System.out.print("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        System.out.println();
        for (int row = 0; row < 4; row++) {
            srcMat.printRow(row);
            rightMat.printRow(row);
            leftMat.printRow(row);
            transMat.printRow(row);
            System.out.print("|");
            System.out.println();
        }
    }
}