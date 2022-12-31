package SortAlgorithm;

import java.lang.Comparable;

public class Sort {
    long start;
    long end;
    long resultTime;

    public Sort() {

    }

    public void sort(Comparable[] a) {

    }

    public void sort(int[] a) {

    }

    public boolean isless(Comparable i, Comparable j) {
        return (i.compareTo(j) < 0);
    }

    public boolean isgreater(Comparable i, Comparable j) {
        return (i.compareTo(j) > 0);
    }

    public void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public double getTime() {
        return (end - start) / 1000.00000;
    }

    public void setTime(long time) {
        resultTime = time;
    }
}
