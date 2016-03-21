/* Lê um texto da entrada padrão e cria uma lista de palavras únicas
// ordenadas pela frequência de ocorrências
// "palavra" se define por uma sequência de caracteres alfanuméricos
// ligados ou não por hífen ('-'): "arroz", "123feijão", "guarda-chuva"
//
// Autor: João Francisco Lino Daniel
//              7578279
*/

import edu.princeton.cs.algs4.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class WordFrequencies {

    public static boolean isAcceptable(char c) {
        return Character.isDigit(c) || Character.isLetter(c) || c == '-';
    }

    public static List<String> isolaPalavras(String linha) {
        List<String> palavras = new ArrayList<>();
        while (linha.length() > 0) {
            int i = 0;

            // remove caracteres não aceitáveis do começo da string
            while (i < linha.length() && !isAcceptable(linha.charAt(i))) {
                i++;
            }
            linha = linha.substring(i);

            // identifica primeira sequência de caracteres aceitáveis
            i = 0;
            while (i < linha.length() && isAcceptable(linha.charAt(i))) {
                i++;
            }

            String s = linha.substring(0, i);   // copia sequência
            s = s.toLowerCase();                // garante lowercase
            palavras.add(s);                    // adiciona à lista
            linha = linha.substring(i);         // remove da linha
        }

        return palavras;
    }

    public static List<Palavra> stringToPalavra (List<String> in) {
        List<Palavra> lista = new ArrayList<>();
        String atual, anterior = in.get(0);
        int contador = 1, i = 1;

        while (i < in.size()) {
            atual = in.get(i++);
            if (atual.equals(anterior)) {
                contador++;
            } else {
                lista.add(new Palavra(anterior, contador));
                contador = 1;
            }
            anterior = atual;
        }

        return lista;
    }

    public static void main(String[] args) {

        // ler texto e criar lista de palavras
        List<String> palavras = new ArrayList<>();
        while (!StdIn.isEmpty()) {
            String linha = StdIn.readLine();
            palavras.addAll(isolaPalavras(linha));
        }

        // ordenar lista de palavras
        Collections.sort(palavras);

        // contar repetições e adicionar na nova lista
        List<Palavra> lista = stringToPalavra(palavras);

        // anular para facilitar a identificação do gc
        palavras = null;

        // ordenar pelo padrão definido em Palavra.compareTo: por frequência
        Collections.sort(lista);

        for (Palavra p: lista) {
            StdOut.println(p);
        }
    }
}
