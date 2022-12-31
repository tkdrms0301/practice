import java.lang.Comparable;
import java.util.Random;

public class CreateArray {
    Comparable[] array;
    char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9' };
    Random r;

    public CreateArray() {
        r = new Random();
    }

    public Comparable[] createRandomIntArray(int length) {
        array = new Comparable[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(65535);
        }
        return array;
    }

    public int[] createRandomUnsignedArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextInt(65535); // 2 ^ 16 - 1
        }
        return array;
    }

    public Comparable[] createRandomDoubleArray(int length) {
        array = new Comparable[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = r.nextDouble();
        }
        return array;
    }

    public Comparable[] createRandomStringArray(int length) {
        array = new Comparable[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = randomAlphabetWord(r.nextInt(10) + 1); // Random String Length 1 ~ 10
        }
        return array;
    }

    private String randomAlphabetWord(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(charArray[r.nextInt(charArray.length - 1) + 1]);
        }
        return sb.toString();
    }

    public Comparable[] createRandomStudentArray(int length) { // grade 0~100
        array = new Comparable[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = new Student(r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100));
        }
        return array;
    }
}
