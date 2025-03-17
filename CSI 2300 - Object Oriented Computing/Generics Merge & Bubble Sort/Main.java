

public class Main {
    
    public static void main(String[] args){
        Integer[] array = {5, 3, 8, 6, 2, 7, 1, 4};
        int n = array.length;
        System.out.print("Current Array: ");
        for (int i = 0; i < n; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();


        System.out.print("Bubble Sort: ");

        Integer[] sortedArray = bubbleSort(array);
        for (int i = 0; i < n; i++){
            System.out.print(sortedArray[i] + " ");
        }
        System.out.println();
        System.out.print("Merge Sort: ");
        Integer[] sortedArray2 = mergeSort(array, 0, n - 1);
        for (int i = 0; i < n; i++){
            System.out.print(sortedArray2[i] + " ");
        }
    }

    public static <T extends Comparable<T>> T[] bubbleSort(T[] array){
        int n = array.length;

        for (int i = 0; i < n - 1; i++){
            for (int j = i + 1; j < n; j++){
                if (array[i].compareTo(array[j]) > 0){
                    T temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
        
    }

    public static <T extends Comparable<T>> T[] mergeSort(T[] array, int l, int r){
        if (l < r) {
            int m = l + (r - l) / 2;

            //recursively break down the array into halves
            mergeSort(array, l, m); // first half
            mergeSort(array, m + 1, r); // second half

            merge(array, l, m, r);
        }
        return array;
        
    }

    public static <T extends Comparable<T>> void merge(T[] array, int l, int m, int r){
        int n1 = m - l + 1;
        int n2 = r - m;

        @SuppressWarnings("unchecked")
        T[] left = (T[]) new Comparable[n1];
        @SuppressWarnings("unchecked")
        T[] right = (T[]) new Comparable[n2];

        // copying data to temp arrays
        for (int i = 0; i < n1; i++){
            left[i] = array[l + i];
        }
        for (int i = 0; i < n2; i++){
            right[i] = array[m + 1 + i];
        }

        int i = 0, j = 0, k = l;
        
        while(i < n1 && j < n2){
            if (left[i].compareTo(right[j]) < 0){
                array[k] = left[i];
                i++;
            }
            else{
                array[k] = right[j];
                j++;
            }
            k++;
        }

        while (i < n1){
            array[k] = left[i];
            i++;
            k++;
        }
        while (j < n2){
            array[k] = right[j];
            j++;
            k++;
        }
    }

}
