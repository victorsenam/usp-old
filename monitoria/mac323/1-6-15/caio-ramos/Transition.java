/* Caio Ramos - NUSP 9292991 - IME-USP 2016
 * 
 * Programa que gera a matriz de transicao.
 * 
 */


public class Transition {


    public static void main(String[] args) {

        int N = StdIn.readInt();           // number of pages
        int[][] counts = new int[N][N];    // counts[i][j] = # links from page i to page j
        int[] outDegree = new int[N];      // outDegree[i] = # links from page i to anywhere

        // Accumulate link counts.  
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 


        // Print probability distribution for row i. 
        for (int i = 0; i < N; i++)  {

            // Print probability for column j. 
            for (int j = 0; j < N; j++) {
				double p;
				
				//trata o caso do site nao conter nenhuma ligacao
				if(outDegree[i] != 0) p = .90*counts[i][j]/outDegree[i] + .10/N;
				else p = 1.0/N;
                
                StdOut.printf("%7.5f ", p); 
            } 
            StdOut.println(); 
        } 
    } 
}
