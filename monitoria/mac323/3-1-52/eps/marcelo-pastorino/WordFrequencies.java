/******************************************************************************
 * Nome: Marcelo Baiano Pastorino Trylesinski
 * Número USP: 9297996
 *
 *  Compilation:  javac-algs4 WordFrequencies.java
 *  Execution:    java-algs4 WordFrequencies
 *
 *  WordFrequencies eh um programa que recebe na entrada padrao um texto.
 *  Conta a quantidade de vezes que determinada string aparece no texto, 
 *  usando case-insensitive, e imprime na saida padrao a string e a quantidade
 *  de vezes que ela apareceu.
 *
 *  * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 *
 *  P: Como o problema foi resolvido?
 *  R: Primeiramente coloquei todas as strings do texto num vetor de strings.
 *     Depois fui colocando essas strings no Map e contando as mesmas. Ao fazer
 *     isso, o Map não estava ordenado pelos value. Logo, utilizei uma função
 *     para fazer um Map ordenado da forma que eu queria. Nessa função utilizei 
 *     uma List para pegar os elementos do Map e ordenar de acordo com o que 
 *     devia ser feito para resolver o problema. Para ordenar, utilizei a função
 *     sort da classe Collections.
 * 
 *  Obs.: Não sei se esse é o formato de comentário que o senhor queria. Favor me 
 *        avisar no comentário de feedback. 
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordFrequencies {

    public static void main (String[] args) {
        Map<String, Integer> dic = new HashMap<String, Integer> ();
        String[] words = StdIn.readAllStrings ();
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase ().replaceAll ("[^a-zA-ZÀ-ÿ-]", "");
            if (words[i].length () > 0) {
                if (!dic.containsKey (words[i])) {
                    dic.put (words[i], 1);
                } else {
                    dic.put (words[i], dic.get(words[i]) + 1);
                }
            }
        }

        Map<String, Integer> dicSorted = sort (dic);        

        for (Map.Entry<String, Integer> entry : dicSorted.entrySet()) {
            String key = entry.getKey ();
            Integer value = entry.getValue ();
            StdOut.println (key + " " + value);
        }
    }

    private static Map<String, Integer> sort (Map<String, Integer> dic) {
        List<Map.Entry<String, Integer>> list = 
            new LinkedList<Map.Entry<String, Integer>> (dic.entrySet ());

        Collections.sort (list, new Comparator<Map.Entry<String, Integer>> () {
            public int compare (Map.Entry<String, Integer> o1,
                                Map.Entry<String, Integer> o2) {
                if (o1.getValue ().compareTo (o2.getValue ()) < 0)
                    return 1;
                else if (o1.getValue ().compareTo (o2.getValue ()) > 0)
                    return -1;
                else
                    return (o1.getKey ().compareTo (o2.getKey ()));
            }
        });

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer> ();
        Iterator<Map.Entry<String, Integer>> it = list.iterator ();

        while (it.hasNext ()) {
            Map.Entry<String, Integer> entry = it.next();
            sortedMap.put (entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
