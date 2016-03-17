/******************************************************************************
 *  Nome: Fabio Henrique Kiyoiti dos Santos Tanaka
 *  Numero Usp: 9297982
 *  Compilation:  javac WordFrequencies.java
 *  Execution:    java WordFrequencies < input.txt
 *
 *  Este programa le um texto e ordena suas palavras por numero de ocorrencias
 *  e caso haja o mesmo valor, por ordem alfabetica.
 *
 *  Ele primeiro le um texto e armazena suas palavras, quando encontra uma
 *  palavra que ja esta no dicionario ele so aumenta o contador dela. Este
 *  processo sempre mantem o dicionario de palavras em ordem alfabetica. Apos 
 *  isso ele ordena pelo numero de ocorrencias,e ja que anteriormente ja estava 
 *  ordem alfabetica, ele nao precisa ordenar novamente para mante-la.
 *
 *  % more input.txt
 *  Este eh um exemplo com repeticoes, ele nao contem acentos ou caracteres como 
 *  cedilha pois parece que eles nao sao aceitos como comentarios no java, mas
 *  eles sao lidos normalmente pelo programa.
 *
 *  % java WordFrequencies < input.txt
 *  como 2
 *  eles 2
 *  nao 2
 *  sao 2
 *  aceitos 1
 *  acentos 1
 *  caracteres 1
 *  cedilha 1
 *  com 1
 *  comentarios 1
 *  contem 1
 *  eh 1
 *  ele 1
 *  este 1
 *  exemplo 1
 *  java 1
 *  lidos 1
 *  mas 1
 *  no 1
 *  normalmente 1
 *  ou 1
 *  parece 1
 *  pelo 1
 *  pois 1
 *  programa 1
 *  que 1
 *  repeticoes 1
 *  um 1
 *
 ******************************************************************************/
import java.util.*;
import edu.princeton.cs.algs4.*;
public class WordFrequencies {

    // "tamanho" eh a quantidade de palavras no dcionario e n a quantidade maxima
    //  que cabe no dicionario.
    static palavra[] dicionario;
    static int tamanho, n;

    // Esta classe guarda uma palavra e seu numero de ocorrencias no texto
    public static class palavra {
        private final String conteudo;
        private int contador;

        public palavra(String texto, int numero) {
            conteudo = texto;
            contador = numero;
        }

        public void imprime() {
            if (conteudo.length() != 0)
                System.out.println (conteudo + " " + contador);
        }
    }
    
    // Esta funcao coloca a palavra que esta em dicionario[i] na posicao
    // dicionario[j], sem retirar ou mudar a ordem de palavras do dicionario
    static void substitui (int i, int j) {
        palavra aux;
        aux = dicionario[i];
        int cont;
        for (cont = i - 1; cont >= j; cont--) {
            dicionario[cont+1] = dicionario[cont];
        }
        dicionario[j] = aux;
    }

    // Ordena um vetor de palavras palavras pelo numero de ocorrencias
    static void ordena () {
        int i, j;
        for (i = 1; i < tamanho; i++) {
            j = i-1;
            while (j >= 0) {
                if (dicionario[i].contador > dicionario[j].contador){
                    j--;
                }
                else
                    break;
            }
            if (dicionario[i].contador > dicionario[j+1].contador) {
                substitui(i, j+1);
            }
        }

    }

    // Dobra o tamanho de uma array de palavras
    static void ExpandeDicionario () {
        palavra[] nova = new palavra[2*tamanho];
        int i;
        for (i = 0; i < tamanho; i++)
            nova[i] = dicionario[i];
        n *= 2;
        dicionario = nova;
    }

    // Realiza busca binaria de uma string em uma array de palavras
    static int BinSearch (String texto) {
        int e, m, d;
        m = -1;
        e = 0;
        d = tamanho-1;
        while (e <= d) {
            m = (e + d)/2;
            if ((dicionario[m].conteudo).compareTo(texto) == 0)
                return m;
            if ((dicionario[m].conteudo).compareTo(texto) < 0) 
                e = m + 1;
            else d = m - 1;
        }
        return m;
    }

    // Le a proxima string e a adapta ao formato correto (retira pontuacoes e deixa
    // tudo em minusculo).
    static String ProximaPalavra() {
        String aux, texto = new String();
        aux = new String();
        int i;
        char n;
        texto = StdIn.readString();
        texto = texto.toLowerCase();
        for (i = 0; i < texto.length(); i++) {
            n = texto.charAt(i);
            // Confere se n eh alfanumerico
            if (!((n >= 'a' && n <= 'z') || (n >= '0' && n <= '9') || (n >= 191))) {
                aux = String.valueOf(n);
                texto = texto.replace(aux, "");
                i--;
            }
        }
        return texto;

    }

    // Insere uma string no dicionario, mantendo-o em ordem alfabetica, caso 
    // ela ja exista, aumenta seu contador
    static void InserePalavra (String texto) {
        int i, j;

        if (tamanho == 0) 
            i = 0;
        else {
            i = BinSearch (texto);
            if ((dicionario[i].conteudo).compareTo(texto) < 0)
                i++;
            else if ((dicionario[i].conteudo).compareTo(texto) == 0) {
                dicionario[i].contador++;
                return;
            }
        }

        if (n == tamanho) {
            ExpandeDicionario();
        }
        for (j = tamanho-1; j >= i; j--) {
            dicionario[j+1] = dicionario[j];
        }
        dicionario[i] = new palavra (texto, 1);
        tamanho++;

    }

    // Constroi o dicionario
    static void ConstroiDic () {
        String texto = new String();
        while (!StdIn.isEmpty()) {
            texto = ProximaPalavra();
            InserePalavra (texto);
        }

    }

    public static void main(String[] args) {
        int i;
        tamanho = 0;
        n = 8;
        dicionario = new palavra[n];
        
        ConstroiDic();
        ordena ();

        //impressao do dicionario
        for (i = 0; i < tamanho; i++) {
            dicionario[i].imprime();
        }

    }
}
