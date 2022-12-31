package SortAlgorithm;

public class RadixSortDecimal extends Sort {
    public RadixSortDecimal() {

    }

    public void sort(int[] array) { // 3 - 3
        start = System.currentTimeMillis();
        int r = 10;
        int n = array.length;
        int[] result = new int[n];

        for (int k = 10; k <= 1000; k *= 10) {
            int[] count = new int[r + 1];

            for (int i = 0; i < n; i++) {
                count[(array[i] % k) / (k / 10) + 1]++;
            }
            for (int i = 0; i < r; i++) {
                count[i + 1] += count[i];
            }
            for (int i = 0; i < n; i++) {
                result[count[(array[i] % k) / (k / 10)]++] = array[i];
            }
            for (int i = 0; i < n; i++) {
                array[i] = result[i];
            }
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}
