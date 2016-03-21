/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 */
 
import edu.princeton.cs.algs4.*;
public class WordFrequencies {

   public static ListaPalavras colhePalavras() {
      ListaPalavras lista = new ListaPalavras();
      String palavra = "";
      
      while (StdIn.hasNextChar()) {
         char c = StdIn.readChar();
         if (Character.isLetter(c) || c == '-' && palavra.length() > 0)
            palavra += c;
         else
            if (palavra.length() > 0) {
               lista.coloca(new Palavra(palavra));
               palavra = "";
            }
      }
      
      if (palavra.length() > 0) lista.coloca(new Palavra(palavra));  // Sobra
      return lista;
   }
   
   public static void main(String[] args) {
      ListaPalavras palavras = colhePalavras();
      palavras.ordenaFrequencias();
      int quantidade = palavras.getQuantidade();
      
      for (int i = quantidade - 1; i >= 0; i--)
         StdOut.println(palavras.getPalavra(i).getTexto() + " " + 
                        palavras.getPalavra(i).getFrequencia());
   }

}
