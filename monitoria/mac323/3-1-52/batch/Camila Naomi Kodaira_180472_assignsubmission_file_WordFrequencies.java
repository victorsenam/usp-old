/* **************************************** */
/*  Nome: Camila Naomi Kodaira              */
/*  Numero USP: 4266602                     */
/* **************************************** */

/* ******************************************* */
/* Word Frequencies                            */
/* Esse programa le um texto escrito no        */
/* terminal e imprime, tambem no terminal, uma */
/* lista de palavras organizadas de acordo com */
/* a frequencia em que cada palavra aparece no */
/* texto, caso mais de uma palavra tenha a     */
/* mesma frequencia, o programa os organiza    */
/* de acordo com a ordem lexicografica.        */
/*                                             */
/* Observacoes:                                */
/* O programa nao funciona direito com letras  */
/* acentuadas.                                 */
/* O programa considera que um texto acabou    */
/* ao ler EOF, entao se voce esta escrevendo   */
/* diretamente no terminal, ao finalizar seu   */
/* texto pressione Ctrl+D (o equivalente ao    */
/* EOF no terminal)                            */
/*                                             */
/* Linha de Comando:                           */
/* $ java-introcs WordFrequencies              */
/* [Seu texto aqui]                            */
/*                                             */
/* Output:                                     */
/* [palavra] [frequencia]                      */
/* (^ Lista de Palavras)                       */
/* ******************************************* */

/* ******************************************* */
/* AVISO: Eu nao consegui testar se o programa */
/* compila ou roda com '-algs4', pois o meu    */
/* computador nao reconhece o comando, ele     */
/* compila e roda com '-introcs', com certeza  */
/* para o proximo exercicio ja terei corrigido */
/* o problema                                  */
/* Atenciosamente,                             */
/* Camila Naomi                                */
/* ******************************************* */

public class WordFrequencies {
  private String pal;
  private int freq;

  // Construtor da classe WordFrequencies
  public WordFrequencies (String palavra, int frequencia) {
    pal = palavra.toLowerCase ();
    freq = frequencia;
  }

  // Uma adaptacao do algoritmo Merge Sort, usado para ordenar vetores,
  // para que ele ordene lexograficamente um vetor v de WordFrequencies
  // de tamanho [p ... r - 1].
  // O algoritmo original pode ser encontrado no site:
  // http://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
  public static void MergeAlfanum (int p, int r, WordFrequencies[] v) {
    if (p < r - 1) {
      int q = (p + r) / 2;
      MergeAlfanum (p, q, v);
      MergeAlfanum (q, r, v);
      IntercalaA (p, q, r, v);
    }
  }

  // Uma adaptacao do algoritmo Merge Sort, usado para ordenar vetores,
  // para que ele ordene de forma decrescente em relacao a frequencia um
  // vetor v de WordFrequencies de tamanho [p ... r - 1].
  // O algoritmo original pode ser encontrado no site:
  // http://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
  public static void MergeFreq (int p, int r, WordFrequencies[] v) {
    if (p < r - 1) {
      int q = (p + r) / 2;
      MergeFreq (p, q, v);
      MergeFreq (q, r, v);
      IntercalaF (p, q, r, v);
    }
  }

  // Uma adaptacao da funcao Intercala do algoritmo Merge Sort,
  // a funcao recebe dois vetores ordenados lexicograficamente,
  // v[p ... q - 1] e v[q ... r - 1] e os rearranja formando um
  // vetor ordenadp v[p ... r - 1].
  // O algoritmo original pode ser encontrado no site:
  // http://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
  public static void IntercalaA (int p, int q, int r, WordFrequencies[] v) {
    int i, j, k;
    WordFrequencies[] w;
    i = p;
    j = q;
    k = 0;
    w = new WordFrequencies[r - p];
    
    while (i < q && j < r) {
      if (v[i].pal.compareTo (v[j].pal) <= 0)  w[k++] = v[i++];
      else  w[k++] = v[j++];
    }
    while (i < q)  w[k++] = v[i++];
    while (j < r)  w[k++] = v[j++];
    for (i = p; i < r; ++i)  v[i] = w[i-p];
  }

  // Uma adaptacao da funcao Intercala do algoritmo Merge Sort,
  // a funcao recebe dois vetores ordenados de forma decrescente
  // em relacao a frequencia, v[p ... q - 1] e v[q ... r - 1] e
  // os rearranja formando um vetor ordenadp v[p ... r - 1].
  // O algoritmo original pode ser encontrado no site:
  // http://www.ime.usp.br/~pf/algoritmos/aulas/mrgsrt.html
  public static void IntercalaF (int p, int q, int r, WordFrequencies[] v) {
    int i, j, k;
    WordFrequencies[] w;
    i = p;
    j = q;
    k = 0;
    w = new WordFrequencies[r - p];
      
    while (i < q && j < r) {
      if (v[i].freq >= v[j].freq)  w[k++] = v[i++];
      else  w[k++] = v[j++];
    }
    while (i < q)  w[k++] = v[i++];
    while (j < r)  w[k++] = v[j++];
    for (i = p; i < r; ++i)  v[i] = w[i-p];
  }

  // Essa funcao recebe um vetor de WordFrequencies, v[0 ... n - 1],
  // o organiza de forma lexicografica, retira as palavras
  // duplicadas enquanto conta a frequencia de cada uma e retorna
  // um inteiro qu representa o tamanho do novo vetor sem as
  // palavras duplicadas.
  public static int Cont (WordFrequencies [] v, int n) {
    int i, novoi;
    i = 0;
    novoi = 0;

    // Organiza Lexicograficaente
    MergeAlfanum (0, n, v);

    // Verifica frequencia
    while (i < n - 1) {
      v[novoi] = v[i];
      v[novoi].freq = 1;
      while (i < n - 1 && v[i].pal.compareTo (v[i + 1].pal) == 0) {
	v[novoi].freq++;
	i++;
      }
      novoi++;
      i++;
    }
    if (i == n) i--;
    if (v[i].pal.compareTo (v[i - 1].pal) != 0) {
      v[novoi] = v[i];
      v[novoi].freq = 1;
      novoi++;
    }
    return novoi;
  }

  // Funcao toString da classe WordFrequencies
  // Quando utilizada, imprime um WordFrequencies no formato:
  // [pal] [freq]
  public String toString () {
    return pal + " " + freq;
  }

  // Dado um vetor de WordFrequencies v[0 ... n - 1], retorna um
  // vetor de mesmo conteudo, mas tamanho v[0 ... (n * 2) - 1]
  public static WordFrequencies[] MaiorVetor (WordFrequencies[] v, int n) {
    WordFrequencies[] temp = new WordFrequencies[n * 2];
    int i;
    for (i = 0; i < n / 2; i++)
      temp[i] = v[i];
    return temp;
  }
    
  // Essa funcao le um texto do terminal e retorna um vetor de
  // WordFrequencies onde todas as palavras do terminal estao
  // nas Strings pal e todas as frequencias sao inicializadas
  // com sendo 1
  // Obs:
  // Essa funcao nao considera numeros como palavras
  // Essa funcao considera Strings com caracteres que nao fazem
  // parte do alfabeto e nao sao "-" como duas palavras separadas
  // (Ex: "aaa.bb" = "aaa" e "bb")
  public static WordFrequencies[] LeTexto () {
    int i, j, x, temp;
    char c;
    String aux;
    x = 20;
    WordFrequencies[] v = new WordFrequencies[x];
    i = 0;
    j = 0;
    while (!StdIn.isEmpty ()) {
      temp =  0;
      aux = StdIn.readString ();

      // Verificando caracteres
      for (c = 0; c < 255; c++) {
	if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && c != '-') { 
	  aux = aux.replace (String.valueOf (c), " ");
        }
      }
      aux = aux.trim();

      // Separando "aaa.bb" como "aaa" e "bb" e
      // Preenchendo o vetor de WordFrequencies
      for (i = aux.indexOf (" "); i >= 0; i = aux.indexOf (" ")) {
	v[j] = new WordFrequencies (aux.substring (temp,i), 1);
	temp = i + 1;
	while (aux.charAt (temp) == ' ')
	  temp = i++;
        aux = aux.substring (temp);
	j++;
	if (j % 20 == 0) {
	  v = MaiorVetor (v, x);
	  x = x * 2;
	}
      }
      if (j % 20 == 0) {
	v = MaiorVetor (v, x);
	x = x * 2;
      }
      v[j] = new WordFrequencies (aux, 1);
      j++;
    }
    WordFrequencies[] fim = new WordFrequencies[j];
    for (i = 0; i < j; i++)
      fim[i] = v[i];
    return fim;
  }

  // Essa funcao imprime uma lista com todas as palavras e frequencias
  // do vetor de WordFrequencies, v[0 ... n - 1], no formato
  // especificado na funcao toString
  public static void Imprime (WordFrequencies[] v, int n) {
    int i;
    for (i = 0; i < n; i++) {
      StdOut.println (v[i]);
    }
  }
    
  public static void main (String[] Args) {
    int n;
    WordFrequencies [] dicio = LeTexto();
    n = dicio.length;
    n = Cont (dicio, n);
    MergeFreq (0, n, dicio);
    Imprime (dicio, n);    
  }
}
