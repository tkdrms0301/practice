package SortAlgorithm;

import java.lang.Comparable;

public class RecursiveMergeSort extends Sort {
    public RecursiveMergeSort() {

    }

    private void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi) {
                a[k] = aux[i++];
            } else if (isless(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

    private void mergeSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)
            return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        start = System.currentTimeMillis();
        mergeSort(a, aux, 0, a.length - 1);
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}