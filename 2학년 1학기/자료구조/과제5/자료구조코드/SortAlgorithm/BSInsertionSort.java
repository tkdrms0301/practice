package SortAlgorithm;

import java.lang.Comparable;

public class BSInsertionSort extends Sort {
    public BSInsertionSort() {

    }

    public int serach(Comparable[] arr, int left, int right, Comparable x) {

        if (right >= left) {
            int mid = left + ((right - left) / 2);
            if (arr[mid] == x)
                return mid;
            if (!isless(arr[mid], x))
                return serach(arr, left, mid - 1, x);
            return serach(arr, mid + 1, right, x);
        }
        return left;
    }

    public void sort(Comparable[] arr) {
        start = System.currentTimeMillis();
        Comparable tmp;
        int find_Index, j;
        for (int i = 1; i < arr.length; i++) {
            tmp = arr[i];
            find_Index = serach(arr, 0, i, tmp);
            for (j = i - 1; j >= find_Index; j--) {
                arr[j + 1] = arr[j];
            }
            arr[find_Index] = tmp;
        }
        end = System.currentTimeMillis();
        setTime(end - start);
    }
}