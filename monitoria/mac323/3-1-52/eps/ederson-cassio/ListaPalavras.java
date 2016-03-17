/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 */
 
public class ListaPalavras {

   private int quantidade;
   private Palavra[] palavras;
   
   public ListaPalavras() {
      quantidade = 0;
      palavras = new Palavra[100];  // Alocação inicial
   }
   
   public void coloca(Palavra palavra) {
      int posicao = buscar(palavra);
      Palavra existente = palavras[posicao];
      
      if (existente != null && existente.mesmaQue(palavra))
         existente.aumenta();
      else
         inserir(palavra, posicao);
   }
   
   public void ordenaFrequencias() {
      QuickSort.ordenar(palavras, 0, quantidade-1);
   }
   
   public int getQuantidade() {
      return quantidade;
   }
   
   public Palavra getPalavra(int posicao) {
      return palavras[posicao];
   }
   
   // Acha a posição da palavra OU a posição de inserção!
   // Ref: http://www.ime.usp.br/~pf/algoritmos/aulas/bubi.html
   private int buscar(Palavra palavra) {
      int esq = -1;
      int dir = quantidade;
      
      while (esq < dir - 1) {
         int meio = (esq + dir) / 2;
         if (palavras[meio].vemAntesDe(palavra))
            esq = meio;
         else
            dir = meio;
      }
      
      return dir;
   }
   
   private void inserir(Palavra palavra, int posicao) {
      if (quantidade == palavras.length) realocar();
      
      for (int i = quantidade; i > posicao; i--)
         palavras[i] = palavras[i-1];
      
      palavras[posicao] = palavra;
      quantidade++;
   }
   
   private void realocar() {
       int tamanho = palavras.length * 2;
       Palavra novoPalavras[] = new Palavra[tamanho];
       
       for (int i = 0; i < palavras.length; i++)
         novoPalavras[i] = palavras[i];
       
       palavras = novoPalavras;
   }
   

}
