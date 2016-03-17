/*******************************************************************************
* Nome: Leonardo Padilha
* Numero USP: 9298295
* 
* WordFrequencie: Recebe um texto da entrada padrão e retorna uma lista de 
* palavras ordenadas pela sua frequencia no texto.
* Para fazer isso, utilizei uma lista de strings e outra de inteiros de tal 
* forma que o elemento i da lista de inteiros seja a frequencia correspondente
* do elemento i da lista de string. Para imprimir, basta recuperar o primeiro
* elemento maximo da lista de inteiros, imprimi-lo com sua palavra correspondente
* e depois retira-los de suas listas, fazendo isso até as listas estarem vazias.
* É importante ressaltar que a lista de strings sempre estará ordenada 
* lexicograficamente.
*
*******************************************************************************/
import java.util.ArrayList;

public class WordFrequencies {
   //indexMax: recebe uma lista de inteiros list e retorna o endereço
   //do primeiro elemento maximo da lista
   public static int indexMax (ArrayList<Integer> list) {
      int max = 0, index = 0;
      for (int i = 0; i < list.size (); i++)
         if (list.get (i) > max){
            max = list.get (i);
            index = i;
         }
      return index;
   }

   //positionOf: recebe uma lista de string list e uma string w,
   //retorna o indice onde a string i deve ser colocada para a lista
   //continuar ordenada lexicograficamente
   public static int positionOf (ArrayList<String> list, String w) {
      int i;
      for (i = 0; i < list.size (); i++) 
         if (w.compareTo (list.get(i)) < 0)
            break;
      return i;
   }

   //addWord: recebe uma lista de string dic, uma de inteiros freq e uma string a
   //pode colocar a string a na sua posição correta na lista dic e adiciona sua frequencia 
   //correspondente tambem na posição correta ou, se a string a já existir, adiciona 1 na
   //sua frequencia correspondente
   public static void addWord (ArrayList<String> dic, ArrayList<Integer> freq, String a) {
      int index = dic.indexOf (a);

      if (index == -1) {
         int i = positionOf (dic, a);
         dic.add (i, a);
         freq.add (i, 1);
      }
      else {
         int value = freq.get(index) + 1;
         freq.set(index, value);
      }
   }

   //printList: recebe uma lista de string dic e uma lista de inteiros freq
   //e imprime o conteudo das listas ordenados pelas frequencias da lista freq.
   public static void printList (ArrayList<String> dic, ArrayList<Integer> freq) {
      while (!dic.isEmpty()) {
         int index = indexMax (freq);
         StdOut.printf ("%s %d\n", dic.remove(index), freq.remove(index));
      }
   }

   //createList: recebe uma ArrayList de strings dic e outra de inteiros freq,
   //le da entrada padrao um texto e coloca as palavras (ordenadas lexicograficamente)
   //em dic e suas frequencias em freq de tal forma que o elemento i de dic tenha sua
   //frequencia dada pelo elemento i de freq
   public static void createList (ArrayList<String> dic, ArrayList<Integer> freq) {
      while (!StdIn.isEmpty()) {
         String a = StdIn.readString();
         a = a.toLowerCase().replaceAll("\\p{P}", "");
         if (a.length() > 0) addWord (dic, freq, a);
      }
   }

   public static void main (String[] args) {
      ArrayList<String> dicList = new ArrayList<String>();
      ArrayList<Integer> freqList = new ArrayList<Integer>();

      WordFrequencies.createList (dicList, freqList);
      WordFrequencies.printList (dicList, freqList);
   }
}
