package SortAlgorithm;

public class RadixSortHexadecimal extends Sort {
    public RadixSortHexadecimal() {

    }

    public void sort(int[] array) { // 3 - 1
        start = System.currentTimeMillis();
        int n = array.length;
        int[] result = new int[n];

        for (int k = 0x10; k <= 0x1000; k *= 0x10) {
            int[] count = new int[0x10 + 1];

            for (int i = 0; i < n; i++) {
                count[(array[i] % k) / (k / 0x10) + 1]++;
            }
            for (int i = 0; i < 16; i++) {
                count[i + 1] += count[i];
            }
            for (int i = 0; i < n; i++) {
                result[count[(array[i] % k) / (k / 0x10)]++] = array[i];
            }
            for (int i = 0; i < n; i++) {
                array[i] = result[i];
            }
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}
