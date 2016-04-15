import java.util.*;
import edu.princeton.cs.algs4.*;
/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 *
 * Uso:
 * java-algs4 TesteVisual N T
 */

import java.util.HashMap;
import java.util.Set;

public class TesteVisual {

   public static void main(String[] args) {
      int N = Integer.parseInt(args[0]);
      int T = Integer.parseInt(args[1]);
      
      RandomQueue<Integer> sequencia = new RandomQueue<Integer>();
      for (int i = 1; i <= N; i++) sequencia.enqueue(i);
      
      HashMap<String, Integer> permutacoes = new HashMap<String, Integer>();
      int qtdMax = 0;
      for (int p = 1; p <= T; p++) {
         String permutacao = "";
         for (Integer i : sequencia)
            permutacao += i + " ";
         
         Integer qtd = permutacoes.get(permutacao);
         if (qtd == null) {
            qtd = 1;
            permutacoes.put(permutacao, qtd);
         }
         else {
            qtd++;
            permutacoes.put(permutacao, qtd);
         }
         
         if (qtd > qtdMax) qtdMax = qtd;
      }
      
      Set<String> perms = permutacoes.keySet();
      StdDraw.setCanvasSize(800, 600);
      StdDraw.setXscale(-1, perms.size()+1);
      StdDraw.setYscale(-1, qtdMax+1);
      
      double x = 0.5;
      for (String p : perms) {
         StdDraw.line(x, 0, x, permutacoes.get(p));
         x += 1.0;  
      }
      
      // Uma marca para certificar que todas as permutações couberam na tela
      StdDraw.setPenRadius(0.01);
      StdDraw.setPenColor(StdDraw.BLUE);
      StdDraw.point(x, 0);
   }

}
