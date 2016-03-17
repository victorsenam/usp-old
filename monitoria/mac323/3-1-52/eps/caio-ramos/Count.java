/* Caio Ramos - NUSP 9292991 - IME USP 2016
    Metodo que recebe um vetor de String ordenado e faz a contagem das Strings
    repetidas.
*/


public class Count {
    public static Pair[] count (String[] v) {
        //retorna um vetor de pares (string + contagem) para ser ordenado por Pair.sort()
        Pair[] p1 = new Pair[v.length];

        int M = 0; //numero de palavras distintas
        for (int i = 0; i < v.length; i++) {
            if (i == 0 || !v[i].equals(v[i-1]))
                p1[M++] = new Pair(v[i], 0); //cria um novo par caso a palavra seja inedita ou a primeira
            p1[M-1].increment(); //incrementa o contador de um Pair
        }

        //faz uma copia de p1 com p tamanho certo
        Pair[] p2 = new Pair[M];
        System.arraycopy(p1, 0, p2, 0, M);

        return p2;
    }
}
