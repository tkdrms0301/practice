package SortAlgorithm;

import java.lang.Comparable;

public class ShellSort extends Sort {
    public ShellSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        int N = a.length;
        int h = 4;
        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && isless(a[j], a[j - h]); j -= h) {
                    swap(a, j, j - h);
                }
            }
            h /= 3;
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}
