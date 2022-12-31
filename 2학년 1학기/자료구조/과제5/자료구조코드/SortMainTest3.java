import java.util.*;

import SortAlgorithm.*;

public class SortMainTest3 {
    public static void main(String[] args) {
        Sort sort;
        CreateArray rc = new CreateArray();

        int[] arrCase1 = rc.createRandomUnsignedArray(50000);
        int[] arrCase2 = Arrays.copyOf(arrCase1, arrCase1.length);
        int[] arrCase3 = Arrays.copyOf(arrCase1, arrCase1.length);
        Comparable[] arrCase4 = new Comparable[50000];

        for (int i = 0; i < arrCase4.length; i++) {
            arrCase4[i] = arrCase1[i];
        }

        sort = new RadixSortHexadecimal();
        sort.sort(arrCase1);
        System.out.println("radix hexadecimal : " + sort.getTime());

        sort = new RadixSortMaskingShift();
        sort.sort(arrCase2);
        System.out.println("radix maskingshift : " + sort.getTime());

        sort = new RadixSortDecimal();
        sort.sort(arrCase3);
        System.out.println("radix decimal : " + sort.getTime());

        sort = new NaturalMergeSort();
        sort.sort(arrCase4);
        System.out.println("array sort : " + sort.getTime());
    }
}
