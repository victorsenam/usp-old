/******************************************************************************
 * Name: Marcos Vinicius do Carmo Sousa
 * NUSP: 9298274
 *
 *  Compilation:  javac-algs4 WordFrequencies.java
 *  Execution:    java-algs4 WordFrequencies
 *  A program that reads in a text file and prints out a list of the words 
 *  in decreasing order of frequency.
 ******************************************************************************/

import edu.princeton.cs.algs4.*;

class Node {
   Node left;
   Node right;
   int key;
   int freq;
   String wd;
}

public class WordFrequencies {
   public static void insercao (int n, int[] v, String[] words) {
      int i, j, x;
      String z;
      for (j = 1; j < n; ++j) {
         x = v[j];
         z = words[j];
         for (i = j - 1; i >= 0 && v[i] > x; --i) {
            v[i+1] = v[i];
            words[i+1] = words[i];
         }
         v[i+1] = x;
         words[i+1] = z;
      }
   }
   public static Node BuildTree (Node tree, Node novo) {
      Node son, father;
      if (novo == null) return tree;
      if (tree == null) return novo;
      son = tree;
      father = son;
      while (son != null) {
         father = son;
         if (son.wd.compareTo(novo.wd) == 0) {
            son.freq++;
            return tree;
         }
         else if (son.wd.compareTo(novo.wd) < 0)
            son = son.left;
         else 
            son = son.right;
      }
      if (father.wd.compareTo(novo.wd) < 0)
         father.left = novo;
      else
         father.right = novo;
      return tree;
   }

   public static void read (Node h, String[] words, int[] v, int i, int cont) {
      Node[] pilha = new Node[cont];
      int t;
      pilha[t=0] = h;
      while (t >= 0) {
         h = pilha[t--];
         words[i] = h.wd;
         v[i] = h.freq;
         i++;
         if (h.left != null) pilha[++t] = h.left;
         if (h.right != null) pilha[++t] = h.right;
      }
   }
   public static void ReadTree (Node tree) {
      if (tree != null) {
         ReadTree (tree.right);
         StdOut.println (tree.wd);
         ReadTree (tree.left);
      }
   }

   public static String Word()  {
      String s;
      s = StdIn.readString ();
      return s;
   }
   public static void main (String[] args) {
      int cont = 0;
      int i = 0;
      Node tree = null;
      String s;
      s = "";
      while (!StdIn.isEmpty ()) { 
         s = Word();
         s = s.toLowerCase ();
         s = s.replaceAll ("[^\\p{L}\\p{Nd}\\-]+", ""); // regex to avoid nonalphanumeric chars 
         if (s != null) {
            Node novo = new Node ();
            novo.left = null;
            novo.right = null;
            novo.freq = 1;
            novo.wd = s;
            tree = BuildTree (tree, novo);
            cont++;
         }
      }
      int[] v = new int[cont];
      String[] words = new String[cont];
      read (tree, words, v, i, cont);
      insercao (cont, v, words); 
      for (int j = cont - 1; j >= 0; j--) {
         if (words[j] != null) {
            StdOut.print (words[j]+ " ");
            StdOut.println (v[j]);
         }
      }
   }
}











































