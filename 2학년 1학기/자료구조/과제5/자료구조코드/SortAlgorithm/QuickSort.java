package SortAlgorithm;

import java.lang.Comparable;

public class QuickSort extends Sort {
    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        sort(a, 0, a.length - 1);
        end = System.currentTimeMillis();
        setTime(end - start);
    }

    private void sort(Comparable[] a, int low, int high) {
        if (high <= low)
            return;
        int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }

    private int partition(Comparable[] a, int pivot, int high) {
        int i = pivot + 1;
        int j = high;
        Comparable p = a[pivot];
        while (true) {
            while (i < high && a[i].compareTo(p) <= 0)
                i++;
            while (j > pivot && p.compareTo(a[j]) < 0)
                j--;
            if (i >= j)
                break;
            swap(a, i, j);
        }
        swap(a, pivot, j);
        return j;
    }
}