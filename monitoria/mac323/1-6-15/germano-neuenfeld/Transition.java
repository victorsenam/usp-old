import edu.princeton.cs.algs4.*;

public class Transition {
    public static void main(String[] args) {
        int N = StdIn.readInt();           
        int[][] counts = new int[N][N];  
        int[] outDegree = new int[N];   
        /*Para cada vértice i do grafo outDegree conta quantas arestas 
         *saem dele, e counts[i][j] conta quantas arestas vão dele para
         *o vértice j.*/
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N);
        /*Gera a matriz de transição em que para cada entrada da matriz
         *tem-se 90% devido a quantidade de arestas entre i e j em relação
         *a quantidade de arestas que saem de i, mais 10%/N que é a 
         *probabilidade de ir para uma página aleatória*/ 
        for (int i = 0; i < N; i++)  {
            for (int j = 0; j < N; j++) {
                double p;
                if (outDegree[i] > 0)
                   p = .90*counts[i][j]/outDegree[i] + .10/N; 
                else p = 1./N;
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
} 


