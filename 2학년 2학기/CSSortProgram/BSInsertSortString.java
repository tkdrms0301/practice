public class BSInsertSortString{
    public int compareTo(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        int limit = 0;

        if(len1 > len2)
            limit = len2;
        else
            limit = len1;

        char charsOfStr1[] = str1.toCharArray();
        char charsOfStr2[] = str2.toCharArray();

        for(int i = 0; i < limit; i++){
            char charOfstr1 = charsOfStr1[i];
            char charOfstr2 = charsOfStr2[i];

            if(charOfstr1 != charOfstr2) {
                return charOfstr1 - charOfstr2;
            }
        }
        return len1 - len2;
    }
    public int search(String[] arr, int left, int right, String x) {

        if (right >= left) {
            int mid = left + ((right-left)/ 2);

            int compare = compareTo(arr[mid], x);

            if (compare == 0)
                return mid;

            if (compare > 0)
                return search(arr, left, mid-1, x);

            return search(arr, mid+1, right, x);
        }
        return left;
    }


    public String[] sort(String[] arr) {

        String temp;
        int find_Index,j;

        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            find_Index= search(arr, 0, i, temp);

            for (j = i - 1; j >= find_Index; j--) {
                arr[j+1]=arr[j];
            }

            arr[find_Index]=temp;
        }
        return arr;
    }


    public class sort {
    }
}