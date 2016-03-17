/*
 * Nome: Mathias Van Sluys Menck
 * NumUSP: 4343470
*/

public class WordFrequencies
{

  public static int N = 20;

/*Checa se a palavra atual é igual a uma já lida*/
  public static boolean compara(String[] tex, int[][] repet, String s, int n)
  {
    for(int i=0; i<n; i++)
      if(tex[i].compareTo(s) == 0)/*ocorre se forem iguais*/
      {
        repet[i][1] = repet[i][1]+1; 
        return true;
      }
    return false;
  }

/*Imprime as palavras lidas e suas frequencias*/
  public static void imprime(int[] array, int[][] repet, String[] texto, int n)
  {
    for(int i=n-1; i>=0; i--)
    {
      for(int j=0; j<n; j++)
        if(repet[j][1] == array[i])
        {
          StdOut.printf("%s  %d\n", texto[repet[j][0]], repet[j][1]);
          repet[j][1] = 0;
        }
    }
  }

/*Usado com o mergesort para ordenar o vetor*/
  public static void intercala(int[] v, int l, int q, int r) 
  {
    int i, j, k;                     
    int [] w = new int[r-l];    
    i = l; j = q;                          
    k = 0;                                 
    while (i < q && j < r) {               
      if (v[i] <= v[j])  w[k++] = v[i++]; 
      else  w[k++] = v[j++];              
    }                                      
    while (i < q)  w[k++] = v[i++];        
    while (j < r)  w[k++] = v[j++];        
    for (i = l; i < r; ++i)  v[i] = w[i-l];
  }
/*Ordena o vetor a*/
  public static void merge(int[] a, int l, int r)
  {
    if(l < r-1)
    {
      int q = (l+r)/2;
      merge(a, l, q);
      merge(a, q, r);
      intercala(a, l, q, r);
    }
  }

/*Expande o tamanho do vetor de strings caso necessario*/
  public static String[] expande1(String[] orig)
  {
    String[] temp = new String[N*2];
    for(int i=0; i<N; i++)
      temp[i] = orig[i];
    return temp;
  }

/*Expande o tamanho do vetor de pares caso necessario*/
  public static int[][] expande2(int[][] orig)
  {
    int[][] temp = new int[N*2][2];
    for(int i=0; i<N; i++)
    {
      temp[i][0] = orig[i][0];
      temp[i][1] = orig[i][1];
    }
    N = 2*N;
    return temp;
  }

  public static void main(String[] args)
  {
    String[] texto = new String[N]; /* contém o texto */
    int[][] repet = new int[N][2]; /* contém o indice de uma palavra em texto e em seguida sua frequencia */
    int i; 

/*le o texto*/
    for(i=0; !StdIn.isEmpty(); i++)
    {
      String s = StdIn.readString();
      s = s.toLowerCase();
      int l = s.length();
      for(int j=0; j<l; j++)
      {
        char c = s.charAt(j);
        if(!((c>='a' && c<='z') ||(c<='9' && c>='0') || (c>=191)/*acentos*/))
        {
          s = s.replace(String.valueOf(c), "");
          l--; j--;
        }
      }
      if(!compara(texto, repet, s, i))
      {
        texto[i] = s;
        repet[i][0] = i;
        repet[i][1] = 1;
      }else {i--; }
      if(i+1 == N) /*expande o tamanho de texto e repet para N*2*/
      {
        texto = expande1(texto);
        repet = expande2(repet);
      }
    }


    int[] array = new int[i];
    for(int j=0; j<i; j++) array[j] = repet[j][1];
    merge(array, 0, i);
    imprime(array, repet, texto, i);
  }
}

