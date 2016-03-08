/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative exercise 1.6.15 (Hubs and Authorities)                     //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/

/* Faz o mesmo que o programa RandomSurfer, porém imprime também um histograma
// do rank de cada página.
// 
// Nas simulações, foi observado que com poucas páginas e muitos links (N << M)
// é muito comum ver páginas com ranks superiores aos dos Hubs e Authorities.
// Quando o número de páginas é maior que o de links (N >> M) é comum ver os 
// Hubs com ranks maiores. Em ambas as situações os Hubs quase sempre obtiveram
// ranks superiores aos Authorities.
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
        int maxfreq = 0;

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
            if (freq[page] > maxfreq)
               maxfreq = freq[page];            
        }
        
        // plot histogram of frequencies
        StdDraw.clear();
        StdDraw.setXscale(-1, N);
        StdDraw.setYscale(0, maxfreq);
        for (int k = 0; k < N; k++) {
           StdDraw.filledRectangle(k, freq[k]/2.0, 0.25, freq[k]/2.0);
        }
        StdDraw.show(20);
        

        // Print page ranks. 
        for (int i = 0; i < N; i++) {
            StdOut.printf("%7.5f", (double) freq[i] / T); 
        }
        StdOut.println(); 
    }  
} 
