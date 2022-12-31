package SortAlgorithm;

import java.lang.Comparable;

public class NaturalMergeSort extends Sort {
  public NaturalMergeSort() {

  }

  private Comparable[] b;

  public void sort(Comparable[] a) {
    start = System.currentTimeMillis();
    b = new Comparable[a.length];
    naturalMergeSort(a);
    end = System.currentTimeMillis();
    setTime(end - start);
  }

  private void naturalMergeSort(Comparable[] a) {
    int left = 0;
    int right = a.length - 1;
    boolean sorted = false;
    int l = 0;
    int r = right;

    do {
      sorted = true;
      left = 0;
      while (left < right) {
        l = left;
        while (l < right && !isgreater(a[l], a[l + 1])) {
          l++;
        }
        r = l + 1;
        while (r == right - 1 || r < right && !isgreater(a[r], a[r + 1])) {
          r++;
        }
        if (r <= right) {
          merge(a, left, l, r);
          sorted = false;
        }
        left = r + 1;
      }
    } while (!sorted);
  }

  private void merge(Comparable[] a, int left, int middle, int right) {
    int l = left;
    int r = middle + 1;
    for (int i = left; i <= right; i++) {
      if (r > right || (l <= middle && !isgreater(a[l], a[r]))) {
        b[i] = a[l++];
      } else if (l > middle || (r <= right && !isgreater(a[r], a[l]))) {
        b[i] = a[r++];
      }
    }
    for (int i = left; i <= right; i++) {
      a[i] = b[i];
    }
  }
}