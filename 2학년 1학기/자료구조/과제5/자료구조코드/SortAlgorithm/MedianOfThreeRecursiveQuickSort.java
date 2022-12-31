package SortAlgorithm;

import java.lang.Comparable;

public class MedianOfThreeRecursiveQuickSort extends Sort {
    public MedianOfThreeRecursiveQuickSort() {

    }

    public void sort(Comparable[] a) {
        start = System.currentTimeMillis();
        motQsort(a, 0, a.length - 1);
        end = System.currentTimeMillis();
        setTime(end - start);
    }

    private void threeSort(Comparable[] a, int front, int mid, int rear) {
        if (isless(a[mid], a[front]))
            swap(a, front, mid);
        if (isless(a[rear], a[mid]))
            swap(a, mid, rear);
        if (isless(a[mid], a[front]))
            swap(a, front, mid);
    }

    public void motQsort(Comparable[] arr, int front, int rear) {
        int i, j, mid = front + (rear - front) / 2;
        Comparable pivot = front + (rear - front) / 2;
        threeSort(arr, front, mid, rear); // 3개 우선정렬
        if (rear - front + 1 > 3) {
            pivot = arr[mid];
            swap(arr, mid, rear - 1);
            i = front;
            j = rear - 1;
            while (true) {
                while (isless(arr[++i], pivot) && i < rear)
                    ;
                while (isless(pivot, arr[--j]) && front < j)
                    ;
                if (i >= j)
                    break;
                swap(arr, i, j);
            }
            swap(arr, i, rear - 1);
            motQsort(arr, front, i - 1);
            motQsort(arr, i + 1, rear);
        }
    }
}// source : https://m.blog.naver.com/occidere/220870294816
