
import java.util.*;
import edu.princeton.cs.algs4.*;
public class WordFrequencies 
{   
    public static void verifyWords (String[] array)
    {
        int lengthArray = array.length;

        for (int i = 0; i < lengthArray; i++)
        {
            array[i] = array[i].toLowerCase ();
            array[i] = array[i].replaceAll ("[^(a-z|\\-)]", "");
        }
    }
    
    public static int[] countFrequency (String[] array)
    {
        int lengthArray = array.length;
        int[] frequencyArray = new int[lengthArray];
        
        for (int i = 0; i < lengthArray; i++)
        {
            if (!array[i].equalsIgnoreCase (""))
            {
                frequencyArray[i]++;
                for (int j = i + 1; j < lengthArray; j++)
                {
                    if (array[i].equalsIgnoreCase (array[j]))
                    {
                        frequencyArray[i]++;
                        array[j] = "";
                    }
                }
            }
        }
        
        return frequencyArray;
    }
    
    public static void sortArray (String[] a, int[] b)
    {
        int lengthA = a.length;
        int tempB;
        String tempA;
        for (int i = 0; i < lengthA - 1; i++)
        {
            for (int j = i + 1; j < lengthA; j++)
            {
                if (b[i] < b[j] || 
                   (b[i] == b[j] && a[i].compareTo (a[j]) > 0))
                {
                    tempB = b[i];
                    b[i] = b[j];
                    b[j] = tempB;
                    
                    tempA = a[i];
                    a[i] = a[j];
                    a[j] = tempA;
                }
            }
        }  
    }    
        
    public static void printArray (String[] a, int[] b)
    {
        int lengthA = a.length;
        for (int i = 0; i < lengthA; i++)
        {
            if (b[i] == 0) break;
            StdOut.println (a[i] + " " + b[i]); 
        } 
    }
    
    public static void main(String[] args) 
    { 
        int lengthArgs = 1000000;
        String[] aux = new String[lengthArgs];
        Scanner in = new Scanner(System.in);
        int len = 0;
        while( in.hasNext() ) aux[len++] = in.next();
        String[] w = new String[len];
        for (int i = 0; i < len; i++) w[i] = aux[i];
        
        verifyWords (w);
        int[] frequencyW = countFrequency (w);
        sortArray(w, frequencyW);
        printArray(w, frequencyW);  
    }
}
