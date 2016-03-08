/******************************************************************************
 *  
 *  Nome: Leonardo Padilha
 *  Numero USP: 9298295
 *  
 * Transition : Recebe um numero N, que representa a quantidade de páginas, e 
 * uma sequencia de pares que representam as ligações das páginas (por exemplo,
 * 0 1 representa um link da pagina 0 para a pagina 1). Retorna uma matriz de 
 * probabilidade de transição da página i para a página j.
 ******************************************************************************/

public class Transition {
    public static void main(String[] args) {
        double p;
        int N = StdIn.readInt();           
        int[][] counts = new int[N][N];    
        int[] outDegree = new int[N]; 

        //Recebe os pares de links  
        while (!StdIn.isEmpty())  {
            int i = StdIn.readInt(); 
            int j = StdIn.readInt(); 
            outDegree[i]++; 
            counts[i][j]++; 
        } 
        StdOut.println(N + " " + N); 

        //Escreve na saida padrao as probabilidades 
        for (int i = 0; i < N; i++)  {
            for (int j = 0; j < N; j++) {
                if (outDegree[i] == 0) p = 1.0 / N;
                else p = .90*counts[i][j]/outDegree[i] + .10/N; 
                StdOut.printf("%8.5f ", p); 
            } 
            StdOut.println(); 
        }
    } 
} 