/* Caio Ramos - NUSP 9292991 - IME-USP 2016
 * 
 * Programa que 'plota' o passeio de um navegante na rede se sites gerada
 * 		seguindo os devidos criterios.
 * 
 * Conclusao: Podemos concluir que o hub (penultima barra da direita) na grande maioria
 * 	das vezes consegue mais acessos que todos os outros sites, incluindo a
 * 	autoriadade(ultima barra da direita). Ela, por sua vez, nao consegue tantos acessos quanto os outros
 * 	sites com criterio aleatorio.
 * 
 */

public class RandomSurferHistogram {
    public static void main(String[] args) {


        int T = Integer.parseInt(args[0]);    // number of moves
        int N = StdIn.readInt();              // number of pages
        StdIn.readInt();                      // ignore integer required by input format

        // Read transition matrix.
        double[][] p = new double[N][N];     // p[i][j] = prob. that surfer moves from page i to page j
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) 
                p[i][j] = StdIn.readDouble(); 


        int[] freq = new int[N];            // freq[i] = # times surfer hits page i
 
        // Start at page 0. 
        int page = 0;

        for (int t = 0; t < T; t++) {

            // Make one random move. 
            double r = Math.random(); 
            double sum = 0.0; 
            for (int j = 0; j < N; j++) {
                // Find interval containing r. 
                sum += p[page][j]; 
                if (r < sum) {
                    page = j;
                    break;
                } 
            } 
            freq[page]++; 

            // plot histogram of frequencies
            StdDraw.clear();
            StdDraw.setXscale(-1, N);
            StdDraw.setYscale(-1, t);
            for (int k = 0; k < N; k++) {
                StdDraw.filledRectangle(k, freq[k]/2.0, 0.25, freq[k]/2.0);
            }
            StdDraw.show(20);

        } 




        // Print page ranks. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%8.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 
