import edu.princeton.cs.algs4.*;

public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]);    
        int K = Integer.parseInt(args[1]);
        int M = StdIn.readInt();             
        int N = StdIn.readInt();            
        if (M != N) throw new RuntimeException("M does not equal N");
        double[][] p = new double[N][N];   
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble();
        int[] freq = new int[N];          
        int page = 0;
        /*baseado na matriz transition e usando a variável sum como
         *a soma acumulada de probabilidade em uma linha, então se gera
         *aleatoriamente um numero r entre 0...1 e o primeiro j tal que 
         *sum > r será a página para a qual se está indo. Assim entradas
         *da matriz transition com maior probabilidade, geram intervalos 
         *maiores de probabilidade e possuem maior chance de serem atingidas*/
        for (int t = 0; t < T; t++) {
            double r = Math.random(); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            } 
            freq[page]++; 
        } 
        for (int i = 0; i < N; i++) {
            if (i == N - 2*K) {
                StdOut.printf ("\nHUBS\n");
            } else if (i == N - K) 
                StdOut.printf ("\nAUTHORITIES\n");
            StdOut.printf("page %d %8.5f\n", i, (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 
