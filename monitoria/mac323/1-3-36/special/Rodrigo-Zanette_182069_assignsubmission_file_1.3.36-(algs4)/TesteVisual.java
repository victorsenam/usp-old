import java.util.*;
import edu.princeton.cs.algs4.*;
/*Nome: Rodrigo Zanette de Magalhães*/
/*Nº USP: 9298090                   */
/*Observação: o RandomIterator é uma classe privada dentro dessa versão modificada*/
/*da RandomQueue que acompanha este cliente                                       */
public class TesteVisual {
    private static int fatorial (int k) {
        if (k==0) { return 1; }
        else { return k*fatorial(k-1); }
    }
    
    private static boolean comp_vectors (int[] a, int[] b) {
        boolean flag=true;
        for (int i=0; i<a.length; i++) {
            if (a[i]!=b[i+1]) { flag=false; break; }
        }
        return flag;
    }
    
    private static boolean can_perm (int[] perm, int k, int m) {
        /*recebe subpermutação de 1 até k e retorna se perm[1], perm[2],
        (...), perm[k], m é uma subpermutação */
        for (int i=1; i<=k; i++) { if (m==perm[i]) { return false; } }
        return true;
    }
    
    private static int find_perm (int[] a) {
        /*constrói, seguindo a ordem lexicográfica, a permutação que
        corresponde ao vetor dado; retorna o índice que enumera tal
        permutação*/
        int k, n=a.length, m=0, indice=0;
        int[] perm = new int[n+1];
        for (k=0; k<=n; k++) { perm[k]=k; }
        k=n;
        
        while (true) {
            if (k >= n) { 
                if (comp_vectors (a, perm)) break;
                indice++;
                m = n+1; /*para forçar backtracking*/
            } 
            while (m <= n && !can_perm (perm, k, m)) { m++; }
            if (m <= n) { /*pode continuar subpermutando*/
                perm[k+1] = m;
                m = 1;
                k++; 
            }
            else { // backtrack 
                if (k < 1) break;
                m = perm[k] + 1; 
                k--;
            }
        }
        return indice;
    }
    
    public static void main (String args[]) {
        int N=Integer.parseInt (args[0]);
        int T=Integer.parseInt (args[1]);
        
        int i, j, fatN=fatorial (N);
        RandomQueue<Integer> A = new RandomQueue<Integer>();
        int[] freq = new int[fatN];
        /*freq guarda a frequência de cada uma das permutações; as
        permutações são tradas como se tivessem enumeradas (a partir
        do 0) em ordem lexicográfica
        exemplo: 1 2 3 4 -> 0
                 1 2 4 3 -> 1...
        e assim por diante*/
        int[] aux = new int[N];
        
        /*definindo propriedades gráficas do histograma (melhor
        visualização)*/
        StdDraw.setCanvasSize (1440, 750);
        StdDraw.setXscale (0, fatN);
        if (N==6) {
            if (T>=100000) {
                StdDraw.setYscale (0, 0.03125);
            }
            else if (T>=1000 && T<100000) {
                StdDraw.setYscale (0, 0.125);
            }
            else {
                StdDraw.setYscale (0, 1);
            }
        }
        else if (N>3) {
            if (T>=100000) {
                StdDraw.setYscale (0, 0.125);
            }
            else if (T>=1000 && T<100000) {
                StdDraw.setYscale (0, 0.5);
            }
            else {
                StdDraw.setYscale (0, 1);
            }
        }
        else {
            StdDraw.setYscale (0, 1);
        }
        /*fim*/
        
        for (i=0; i<fatN; i++) { freq[i]=0; }
        for (j=0; j<T; j++) {
            for (i=1; i<=N; i++) { A.enqueue (i); }
            for (i=0; i<N; i++) { aux[i] = A.rIterator().next(); }
            freq[find_perm(aux)]++;
        }
        for (i=0; i<fatN; i++) {
            StdDraw.filledRectangle ((double) i+0.5, (double) freq[i]/(2*T),
            0.5, (double) freq[i]/T);
        }
    }
}

