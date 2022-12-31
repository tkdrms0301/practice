public class BSInsertSortInt{
    public int search(int[] arr, int left, int right, int x) {
        
        if (right >= left) {
            int mid = left + ((right-left)/ 2);
            
            if (arr[mid] == x)
                return mid;
            if (arr[mid] > x)
                return search(arr, left, mid-1, x);
            return search(arr, mid+1, right, x);
        }
        return left;
    }
    
    
    public int[] sort(int[] arr) {
        
        int temp,find_Index,j;    
    
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
}