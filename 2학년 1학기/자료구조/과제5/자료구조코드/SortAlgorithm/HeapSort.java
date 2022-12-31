package SortAlgorithm;

import java.lang.Comparable;

public class HeapSort extends Sort {
    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        int heapSize = a.length - 1;
        for (int i = heapSize / 2; i > 0; i--)
            downheap(a, i, heapSize);
        while (heapSize > 1) {
            swap(a, 1, heapSize--);
            downheap(a, 1, heapSize);
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }

    private void downheap(Comparable[] a, int p, int heapSize) {
        while (2 * p <= heapSize) {
            int s = 2 * p;
            if (s < heapSize && isless(a[s], a[s + 1]))
                s++;
            if (!isless(a[p], a[s]))
                break;
            swap(a, p, s);
            p = s;
        }
    }
}
