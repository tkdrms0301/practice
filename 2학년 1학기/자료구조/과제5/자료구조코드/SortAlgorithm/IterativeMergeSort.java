package SortAlgorithm;

import java.lang.Comparable;

public class IterativeMergeSort extends Sort {
    public IterativeMergeSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        int curr_size;
        int left_start;
        for (curr_size = 1; curr_size <= a.length - 1; curr_size = 2 * curr_size) {
            for (left_start = 0; left_start < a.length - 1; left_start += 2 * curr_size) {
                int mid = Math.min(left_start + curr_size - 1, a.length - 1);
                int right_end = Math.min(left_start + 2 * curr_size - 1, a.length - 1);
                merge(a, left_start, mid, right_end);
            }
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }

    public void merge(Comparable[] a, int l, int m, int r) {
        int i, j, k;
        int n1 = m - l + 1;
        int n2 = r - m;
        Comparable L[] = new Comparable[n1];
        Comparable R[] = new Comparable[n2];
        for (i = 0; i < n1; i++)
            L[i] = a[l + i];
        for (j = 0; j < n2; j++)
            R[j] = a[m + 1 + j];
        i = 0;
        j = 0;
        k = l;
        while (i < n1 && j < n2) {
            if (isless(L[i], R[j])) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
}

// source : https://www.geeksforgeeks.org/iterative-merge-sort/