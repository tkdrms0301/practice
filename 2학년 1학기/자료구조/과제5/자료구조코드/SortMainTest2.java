import java.util.Arrays;
import java.util.Collections;

import SortAlgorithm.*;

public class SortMainTest2 {
    public static void main(String[] args) {
        Sort sort;
        CreateArray rc = new CreateArray();
        // createRandomStudentArray, createRandomStudentArray,
        // createRandomStudentArray, createRandomStudentArray

        Comparable[] arr;
        sort = new QuickSort();
        // insertion sort, BSInsertionSort, ShellSort, QuickSort
        // MedianOfThreeRecursiveQuickSort, RecursiveMergeSort, IterativeMergeSort
        // NaturalMergeSort, HeapSort, BubbleSort, ArraysSort, CollectionsSort

        arr = rc.createRandomIntArray(10000);
        // 1000, 5000, 10000, 25000, 50000

        // random order value
        //
        // increase order value
        // Arrays.sort(arr);
        // decrease order value
        // Arrays.sort(arr, Collections.reverseOrder());

        sort.sort(arr);
        System.out.println(sort.getTime());

        // Quick Sort 도중 데이터 수가 30000보다 클 때 에러로 인해 결과 산출 못하였음 StackOverflow 발생
    }
}
