/* Crative Exercise 1.3.36 - RandomQueue
    MAC 323 - Yoshiharu Kohayakawa
    Otávio Vasques - 8944665
    */
    
import java.util.Iterator;
import edu.princeton.cs.algs4.*;

public class TesteVisual {

    public static int factorial (int n) {
        // Recebe N e retorna N!
        if (n == 0)
            return 1;
        else
            return n*factorial(n-1);
    }

    public static int inString (String[] set, String item) {
        // Recebe um vetor de strings e uma string e verifica se essa string
        // esta no vetor. Retorna o indice da posição encontrada ou -1 caso
        // não exista uma ocorrência da string.
        int N = set.length;
        for (int i = 0; i < N; i++)
            if (item.equals(set[i]))
                return i;
        return -1;
    }

    public static double maximum (double[] array) {
        // Recebe um vetor de doubles e retorna o maior valor entre eles.
        double max = array[0];
        int N = array.length;
        for (int i = 0; i < N; i++)
            if (array[i] > max)
                max = array[i];
        return max;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        RandomQueue<Integer> numbers = new RandomQueue<Integer>();
        String perm = "";
        String[] permutations = new String[factorial(N)];
        double[] counts = new double[factorial(N)];
        int size = 0;
        int temp;

        // Incicializando o RandomQueue neumbers
        for (int i = 0; i < N; i++)
            numbers.enqueue(i+1);

        for (int i = 0; i < T; i++) {
            perm = "";

            // Seq
            for (Integer j : numbers)
                perm += j + " ";

            // Count
            temp = inString(permutations, perm);
            if (temp >= 0)
                counts[temp]++;
            else {
                permutations[size] = perm;
                counts[size] = 1;
                size++;
            }
        }

        // Eliminando permutações que não apreceram
        double[] defcounts = new double[size];
        for (int i = 0; i < size; i++)
            defcounts[i] = counts[i];

        // Print para obtenção de dados
        // for (int i = 0; i < size; i++)
        //  System.out.println(i + " " + counts[i]);

        // Desenho do histograma
        double max = maximum(defcounts);
        StdDraw.setYscale(-1, max + 1);  // to leave a little border
        StdStats.plotBars(defcounts);
    }
}
