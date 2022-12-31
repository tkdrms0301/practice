package SortAlgorithm;

import java.lang.Comparable;

public class InsertionSort extends Sort {
    public InsertionSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        int N = a.length;
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (isless(a[j], a[j - 1]))
                    swap(a, j, j - 1);
                else
                    break;
            }
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}
