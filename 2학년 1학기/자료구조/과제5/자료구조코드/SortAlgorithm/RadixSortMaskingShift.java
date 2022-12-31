package SortAlgorithm;

public class RadixSortMaskingShift extends Sort {
    public RadixSortMaskingShift() {

    }

    public void sort(int[] array) { // 3 - 2
        start = System.currentTimeMillis();
        int position = 0;
        int n = array.length;
        int[] result = new int[n];
        int max = 1;

        while (max >> position > 0) {
            int[] count = new int[16];

            for (int each : array) {
                if (position == 0) {
                    if (each > max)
                        max = each;
                }
                count[(each >> position) & 0xf]++;
            }

            for (int i = 1; i < 16; i++) {
                count[i] += count[i - 1];
            }

            for (int i = n - 1; i >= 0; i--) {
                int current = (array[i] >> position) & 0xf;
                result[count[current] - 1] = array[i];
                count[current]--;
            }
            for (int i = 0; i < n; i++) {
                array[i] = result[i];
            }
            position += 4;
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}