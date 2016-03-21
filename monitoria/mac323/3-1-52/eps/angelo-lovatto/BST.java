/******************************************************************************
 * Nome: Ângelo Gregório Lovatto 
 * Número USP: 9293435
 * 
 * A classe BST é responsável por "embalar" e manejar a classe privada Node na
 * criação e manipulação de árvores binárias de busca, incluindo funções que 
 * adicionam nós, imprimem a árvore e mudam o critério de busca da árvore.
 *
 *
 *****************************************************************************/

import edu.princeton.cs.algs4.*;
public class BST {
    private Node root;

    private class Node {
        private String word;
        private int count;
        private Node left, right;
        private int N;

        private Node() {
            word = null;
            count = 0;
            left = right = null;
            N = 0;
        }

        private int add(String wd) {
            if (word == null) {
                word = wd;
                count = 1;
                N = 1;
                return 1;
            }
            int cmp = wd.compareTo(word);
            if (cmp == 0) {
                count ++;
                return 0;
            }
            if (cmp < 0) {
                if (left == null) left = new Node();
                int n = left.add(wd);
                N += n;
                return n;
            }
            if (right == null) right = new Node();
            int n = right.add(wd);
            N += n;
            return n;
        }

        private int addbyCount(String wd, int i) {
            if (count == 0) {
                word = wd;
                count = i;
                N = 1;
                return 1;
            }
            if (i > count) {
                if (left == null) left = new Node();
                int n = left.addbyCount(wd, i);
                N += n;
                return n;
            }
            if (right == null) right = new Node();
            int n = right.addbyCount(wd, i);
            N += n;
            return n;
        }

        private void dumpTree(Node aux) {
            if (left != null) left.dumpTree(aux);
            left = null;
            aux.addbyCount(word, count);
            if (right != null) right.dumpTree(aux);
            right = null;
        }

        private void printParameters() {
            if (left != null) left.printParameters();
            StdOut.printf("%s %d\n", word, count);
            if (right != null) right.printParameters();
        }
        
    }

    public BST() {
        root = new Node();
    }
    
    public int size() {
        return size(root);
    }
    
    private int size(Node x) {
        if (x.count == 0) return 0;
        else return x.N;
    }
    
    public void addWord(String wd) {
        root.add(wd);
    }

    public void sortbyCount() {
        Node aux = new Node();
        root.dumpTree(aux);
        root = aux;
    }

    public void printTree() {
        root.printParameters();
    }
    
}
    

