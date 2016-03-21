/******************************************************************************
 *  Nome: Nicolas Nogueira Lopes da Silva
 *  Nº USP: 9277541
 *
 *  Compilacao: javac-algs4 WordFrequencies.java
 *  Execucao: java-algs4 WordFrequencies < input.txt
 *
 *  Recebe um arquivo de texto e gera uma lista de frequencia de palavras
 *  ordenado pela frequencia de cada palavra em ordem decrescente.
 *
 *  % java-algs4 WordFrequencies < input.txt > output.txt
 *
 *  O TreeMap utilizado tem para cada entrada a chave sendo uma palavra do
 *  texto e o valor a sua frequencia.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordFrequencies {

    public static void main(String[] args) {
        TreeMap<String, Integer> dicionario = new TreeMap<String, Integer>();
        String texto = StdIn.readAll();
        String[] palavras = texto.split("[^a-zA-Z\u00C0-\u00FF\\-]");
        for (String palavra : palavras) {
            palavra = palavra.toLowerCase().replaceAll("[^\\p{L}\\p{Nd}\\-]+", "");
            if (!palavra.equals(""))
                if (dicionario.containsKey(palavra))
                    dicionario.put(palavra, dicionario.get(palavra) + 1);
                else
                    dicionario.put(palavra, 1);   
        }

        //TreeMap por padrao esta ordenado pelas chaves em ordem lexicografica

        //cmp define a regra pela qual TreeMap e ordenado

        ComparadorValores cmp = new ComparadorValores(dicionario);         
        Map<String, Integer> dicionarioOrd = new TreeMap<String, Integer>(cmp);
        dicionarioOrd.putAll(dicionario);

        //dicionarioOrd consiste no dicionario reordenado pelos valores
        //em ordem decrescente

        for (String chave : dicionarioOrd.keySet())
            System.out.println(chave + " " + dicionario.get(chave));
    }
}
