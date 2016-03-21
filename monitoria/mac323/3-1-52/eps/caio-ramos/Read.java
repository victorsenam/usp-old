/* Caio Ramos - NUSP 9292991 - IME USP 2016
    Programa que le da entrada padrao um texto e o separa em palavras (tokens)
    para outro programa ler.
*/


import edu.princeton.cs.algs4.*;

public class Read {
    public static void main(String[] args) {
        String[] entrada = StdIn.readAllStrings(); //le toda a entrada padrao
        for(int i = 0; i < entrada.length; i++) {
            entrada[i] = entrada[i].toLowerCase(); //transforma em minusculas
            //Regular Expression para deixar somente os chars de a-z + acentos + '-'
            entrada[i] = entrada[i].replaceAll("[^a-záàâãéèêíïóôõöúçñ-]", "");
            //RegEx para substituir os hifen duplos em espacos (transforma-los em dois tokens)
            entrada[i] = entrada[i].replaceAll("--", " ");

        }
        for(int i = 0; i < entrada.length; i++) {
            StdOut.println(entrada[i]); //imprime os tokens
        }
    }
}
