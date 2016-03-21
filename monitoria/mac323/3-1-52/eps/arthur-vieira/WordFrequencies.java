/*******************************************************************************
 * MAC0323 2016
 * Creative Exercise 3.1.52 (Word frequencies; IntroCS)
 * 
 * Arthur Vieira Barbosa
 * nUSP 6482041
 ******************************************************************************/

import java.lang.Character;
import java.util.ArrayList;
import java.util.*;
import edu.princeton.cs.algs4.*;

public class WordFrequencies {
    
    // Recebe uma string searching a ser buscada dentre as palavras do ArrayList
    // list, lexicograficamente ordenado. Devolve -1 e aumenta a frequencia da
    // palavra caso encontrada, e, caso nao encontrada, devolve a posicao que a
    // palavra de string searching deveria ter caso estivesse em list de modo
    // que ele se mantivesse ordenado.
    public static int binarySearch(String searching, ArrayList<Word> list) {
        int l, m, r;
        l = 0; r = list.size() - 1;
        
        while (l <= r) {
            m = (l + r) / 2;
            Word w = list.get(m);
            String s = w.string;
            
            if (s.equals(searching)) {
                w.increaseFreq();
                return -1;
            }
            if (searching.compareTo(s) > 0)
                l = m + 1;
            else
                r = m - 1;
        }
        
        return l;
    }
    
    // Recebe uma string text, criando e retornando um ArrayList
    // lexicograficamente ordenado com palavras contidas em text.
    public static ArrayList<Word> createList(String text) {
        ArrayList<Word> list = new ArrayList<Word>();
        
        for (int i = 0, wordStart = 0; i < text.length(); i++) {
            if (!Character.isLetter(text.charAt(i)) && text.charAt(i) != '-') {
                if (i != wordStart) {
                    String s = text.substring(wordStart, i);
                    s = s.toLowerCase();
                    int index = binarySearch(s, list);
                    if (index >= 0) {
                        Word newWord = new Word(s);
                        list.add(index, newWord);
                    }
                }
                wordStart = i + 1;
            }
        }
        
        return list;
    }
    
    // Metodo auxiliar do quicksort.
    // Baseado em http://en.wikipedia.org/wiki/Quicksort
    public static int partition(ArrayList<Word> list, int lo, int hi) {
        Word pivot = list.get(hi);
        int i = lo;
        for (int j = lo; j < hi; j++) {
            Word wordI = list.get(i);
            Word wordJ = list.get(j);
            if (wordJ.compare(pivot) < 0) {
                list.set(i, wordJ);
                list.set(j, wordI);
                i++;
            }
        }
        Word wordHi = list.get(hi);
        Word wordI = list.get(i);
        list.set(hi, wordI);
        list.set(i, wordHi);
        return i;
    }
    
    // Faz o quicksort no ArrayList list a partir do elemento de indice lo ate o
    // elemento de indice hi. Baseado em http://en.wikipedia.org/wiki/Quicksort
    public static void quicksort(ArrayList<Word> list, int lo, int hi) {
        if (lo < hi) {
            int p = partition(list, lo, hi);
            quicksort(list, lo, p-1);
            quicksort(list, p+1, hi);
        }
    }
    
    // Ordena o ArrayList list em ordem decrescente de frequencia, com as
    // palavras de mesma frequencia respeitando a ordem lexicografica.
    public static void sortList(ArrayList<Word> list) {
        quicksort(list, 0, list.size()-1);
    }
    
    // Imprime todas as palavras da lista no formato "palavra frequencia".
    public static void printList(ArrayList<Word> list) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }
    
    public static void main(String[] args) { 
        String text = StdIn.readAll();
        ArrayList<Word> list = createList(text);
        sortList(list);
        printList(list);
    }
}
