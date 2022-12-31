import SortAlgorithm.*;
import java.util.*;

public class SortMainTest1 {
    public static void main(String[] args) {
        Sort sort = new QuickSort();
        // stable sort : insertion sort, merge sort, **radix sort sort**
        // non-stable sort : selection sort, shell sort, quick sort, heap sort
        Random r = new Random();
        Comparable[] arrTest = new Comparable[10];

        System.out.println("Before Sort");
        for (int i = 0; i < arrTest.length; i++) {
            arrTest[i] = new InsertionOrder(r.nextInt(5));
            System.out.print("[" + ((InsertionOrder) arrTest[i]).getValue() + ","
                    + ((InsertionOrder) arrTest[i]).getInsertionOrderValue() + "]");
        }
        System.out.println();
        System.out.println("After Sort");
        sort.sort(arrTest);
        for (int i = 0; i < arrTest.length; i++) {
            System.out.print("[" + ((InsertionOrder) arrTest[i]).getValue() + ","
                    + ((InsertionOrder) arrTest[i]).getInsertionOrderValue() + "]");
        }
    }
}
