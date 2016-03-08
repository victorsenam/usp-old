 /******************************************************************************
 *  
 *  Nome: Leonardo Padilha
 *  Numero USP: 9298295
 *  
 * RandomSurfer: Recebe na linha de comando um valor T representando a quantidade
 * de passos que deverá ser feito. Recebe da entrada padrao um valor N e uma matriz
 * NxN que representa a probabilidade de sair da pagina i e ir para a pagina j.
 * Calcula a probabilidade de estar na i-esimo pagina ao final do T-esimo passo. 
 *  
 ******************************************************************************/

 public class RandomSurfer {
    public static void main(String[] args) {
        int T = Integer.parseInt(args[0]); 
        int M = StdIn.readInt();        
        int N = StdIn.readInt();              
        if (M != N) throw new RuntimeException("M does not equal N");

        double[][] p = new double[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 


        int[] freq = new int[N]; 
        int page = 0;

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
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 