/*///////////////////////////////////////////////////////////////////////
//                                                                     //
// Nome: Adriano Tetsuaki Ogawa Santin                                 //
// Número USP: 8944387                                                 //
// Creative exercise 3.1.52 (Word frequencies)                         //
//                                                                     // 
///////////////////////////////////////////////////////////////////////*/


public class WordFrequencies {

   // vetor de caracteres da tabela ascii para que a função que lê as palavras
   // já as converta corretamente, e ignore caracteres não alfabéticos.
   static final String car = "                                             -                   abcdefghijklmnopqrstuvwxyz      abcdefghijklmnopqrstuvwxyz                                                                     àáâãä  çèéêëìíîï  òóôõö  ùúûü   àáâãä  çèéêëìíîï  òóôõö  ùùûü   ";
   static final char[] cArr = car.toCharArray();


   // Função lê cada palavra, transforma letras maiúsculas em minúsculas e caracteres
   // não alfabéticos em espaços. Guarda as palavras em um vetor de strings.
   public static String[] readWords () {
      int n = 20;
      String[] words = new String[n];
      int i = 0;
      while (!StdIn.isEmpty ()) {
         char c = StdIn.readChar();
         char[] ch = new char[n];
         ch[0] = cArr[c];
         while (ch[0] == ' ') {
            c = StdIn.readChar();
            ch[0] = cArr[c];
         }
         int j = 0;
         while (ch[j] != ' ') {
            j++;
            c = StdIn.readChar();
            ch[j] = cArr[c];
            if (j == n)
               ch = resizeChar (ch, 2*n);
         }
         words[i] = String.valueOf (ch);
         i++;
         if (i == n)
            words = resizeWords (words, 2*n);
      }
      String[] newWords = new String[i];
      System.arraycopy (words, 0, newWords, 0, i);
      return newWords;
   }

   // Função aumenta o vetor de caracteres caso a palavra lida seja maior
   // que o tamanho primeiramente esperaso (20 caracteres).
   public static char[] resizeChar (char[] arr, int n) {
      char[] largerArr = new char[n];
      System.arraycopy (arr, 0, largerArr, 0, arr.length);
      return largerArr;
   }

   // Função aumenta o vetor de paavras caso o texto tenha mais palavras
   // que o número primeiramente esperaso (20 palavras).
   public static String[] resizeWords (String[] arr, int n) {
      String[] largerArr = new String[n];
      System.arraycopy (arr, 0, largerArr, 0, arr.length);
      return largerArr;
   }

   // Função ordena o vetor de palavras na ordem lexicográfica pelo
   // algoritimo quicksort.
   public static void quickSort (String[] words, int p, int r) {
      int j;
      while (p < r) {
         j = separa (words, p, r);
         if (j - p < r - j) {
            quickSort (words, p, j-1);
            p = j + 1;
         }
         else {
            quickSort (words, j+1, r);
            r = j - 1;
         }
      }
   }

   // Função divide o vetor em dois e coloca as strings "menores" no início
   // e as "maiores" no fim. Usada pelo quicsort.
   public static int separa (String[] words, int p, int r) {
      String c = words[p];
      int i = p+1, j = r;
      while (i <= j) {
         if (words[i].compareTo (c) <= 0)
            i++;
         else if (words[j].compareTo (c) > 0)
            j--;
         else {
            String t = words[i];
            words[i] = words[j];
            words[j] = t;
            i++; j--;
         }
      }
      words[p] = words[j];
      words[j] = c;
      return j;
   }

   // Função conta a frequencia de cada palavra e as coloca numa matriz,
   // cada palavra com sua frequência associada.
   public static String[][] setFreq (String[] words, int n) {
      String[][] freq = new String[n][2];
      int i = 0, j = 0, count = 1;
      
      while (i < n) {
         if (i+1 < n && words[i].compareTo (words[i+1]) == 0) {
            count++;
            i++;
         }
         else {
            freq[j][0] = words[i];
            freq[j][1] = String.valueOf (count);
            count = 1;
            i++;
            j++;
         }
      }
      String[][] newFreq = new String[j][2];
      for (int k = 0; k < j; k++)
         for (int g = 0; g < 2; g++)
            newFreq[k][g] = freq[k][g];
      return newFreq;
   }

   // Função ordena a matriz que contém as palavras e frequências pela ordem
   // de frequências, com as mais altas primeiro.
   public static void SortFreq (String[][] freq, int n) {
      String[] x = new String[2];
      int i;
      for (int j = 1; j < n; j++) {
         x[0] = freq[j][0];
         x[1] = freq[j][1];
         for (i = j-1; i >= 0 && freq[i][1].compareTo (x[1]) < 0; i--) {
            freq[i+1][0] = freq[i][0];
            freq[i+1][1] = freq[i][1];
         }
         freq[i+1][0] = x[0];
         freq[i+1][1] = x[1];
      }
   }
         
   
   public static void main (String[] args) {
      String[] words = readWords ();
      int n = words.length;
      quickSort (words, 0, n - 1);
      String[][] frequencies = setFreq (words, n);
      n = frequencies.length;
      SortFreq (frequencies, n);
      for (int i = 0; i < n; i++)
         StdOut.println (frequencies[i][0] + " " + frequencies[i][1]);
      
   }
}
