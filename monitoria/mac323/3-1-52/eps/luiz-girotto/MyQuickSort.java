import edu.princeton.cs.algs4.*;
public class MyQuickSort {
    
    // Este modulo final pega as palavras e as frequencias
    // recebidas do ultimo modulo, os insere novamente na
    // estrutura de dados WD, ordena os elementos de acordo
    // com a sua frequencia
     
    private WF[] array;
    private int length;
 
    public void sortFrequency(WF[] inputArr, int low, int high) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = high;
        quickSortFrequency(low, length - 1);
    }
    
    public void sortWords(WF[] inputArr, int low, int high) {
         
        if (inputArr == null || inputArr.length == 0) {
            return;
        }
        this.array = inputArr;
        length = high;
        quickSortWords(low, length - 1);
    }
 
    private void quickSortFrequency(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        int pivot = array[lowerIndex+(higherIndex-lowerIndex)/2].freq;
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].freq < pivot) {
                i++;
            }
            while (array[j].freq > pivot) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSortFrequency(lowerIndex, j);
        if (i < higherIndex)
            quickSortFrequency(i, higherIndex);
    }
    
    private void quickSortWords(int lowerIndex, int higherIndex) {
         
        int i = lowerIndex;
        int j = higherIndex;
        // calculate pivot number, I am taking pivot as middle index number
        String pivot = array[lowerIndex+(higherIndex-lowerIndex)/2].word;
        // Divide into two arrays
        while (i <= j) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[i].word.compareTo(pivot) < 0) {
                i++;
            }
            while (array[j].word.compareTo(pivot) > 0) {
                j--;
            }
            if (i <= j) {
                exchangeNumbers(i, j);
                //move index to next position on both sides
                i++;
                j--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < j)
            quickSortWords(lowerIndex, j);
        if (i < higherIndex)
            quickSortWords(i, higherIndex);
    }
 
    private void exchangeNumbers(int i, int j) {
        int temp = array[i].freq; String tempword = array[i].word;
        array[i].freq = array[j].freq; array[i].word = array[j].word;
        array[j].freq = temp; array[j].word = tempword;
    }
    
    public static WF[] expande (WF[] arr, int N) {
        WF[] arrnew = new WF[N*2];
        System.arraycopy( arr, 0, arrnew, 0, N);
        return arrnew;
    }
     
    public static void main(String a[]){
        
        int i, j, k, cont, truelength, partlength;
        int N = 1;
        String ph;
        WF[] arr = new WF[N];
        
        for (i = 0; !StdIn.isEmpty(); i++) {
            if (i == N) {
                arr = expande (arr, N);
                N = N*2;
            }
                
            arr[i] =  new WF();
            ph = StdIn.readString();
            for (j = 1; j < ph.length() - 1; j++)
                if (ph.charAt(j) <= 48 || ph.charAt(j) >= 57) break;
            arr[i].freq = Integer.parseInt(ph.substring(0, j));
            arr[i].word = ph.substring(j);
        }
        
        for (truelength = 0; ;truelength++) if (truelength == N || arr[truelength] == null) break;
        MyQuickSort sorter = new MyQuickSort();
        sorter.sortFrequency(arr, 0, truelength);
        
        for (i = N-1; i >= 0; i--) {
            if (arr[i] !=  null) {
                k = arr[i].freq;
                for (j = i-1, cont = 1; j >= 0; j--, cont++)
                    if (arr[j].freq != k) break;
                j++;
                i = j;
                cont = j + cont;
                sorter.sortWords (arr, j, cont);
                for ( ; j < cont; j++)
                    System.out.printf ("%s %d\n", arr[j].word, arr[j].freq);
            }
        }
        
    }
}


