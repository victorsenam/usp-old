/*******************************************************************************

WordFrequencies

Nome:   Matheus Tavares Bernardino
Nº USP: 9292987
Data:   11/03/2016


Descrição:
Dado um texto direcionado para este programa, o programa imprime uma lista com
todas as palavras do texto, organizadas por ordem decrescente de frequencias em
que cada palavra foi encontrada. Palavras com mesma frequencia são ordenas
lexicograficamente.

Definição:
São consideradas palavras, sequencias de caracteres válidos. Estes
caracteres, podem ser letras (maiúsculas, mínusculas, com ou sem acento), hífens
e números. (exclui-se os demais)


Compilação:    javac-algs4 WordFrequencies.java
Execução:      java-algs4 WordFrequencies < entrada.txt

Ex de execução:

Comando1:
   $cat entrada.txt
Saída:
    O sapo não lava o pé. Não lava porque não quer. Mas que chulé!
Comando 2:
   $java-algs4 WordFrequencies < entrada teste.txt
Saída:
    não  3
    lava  2
    o  2
    chulé  1
    mas  1
    porque  1
    pé  1
    que  1
    quer  1
    sapo  1

*******************************************************************************/


import edu.princeton.cs.algs4.*;


/*Esta classe é utilizada para implementar uma lista encadeada de celulas, onde
cada célula contem uma string s e uma referência para a próxima célula.
Nota de autoria: Esta classe é baseada na classe homonima do site: 
https://goo.gl/t15QvS
*/
class Celula {
    String s;
    int freq;
    Celula prox;

    public Celula (String s, Celula prox) {
        this.s = s;
        freq = 1;
        this.prox = prox;
    }
}


public class WordFrequencies {


    /*Organiza a lista encadeada com base na frequencia de cada palavra.
    Nota de autoria: função baseada em implementações do selectionsort,
    disponíveis na web*/
    public static Celula sort (Celula cabeça) {
        Celula novaCabeça, aux, anti, antj;
        novaCabeça = cabeça;
        antj = null;
        anti = null;
        for (Celula i = cabeça; i.prox != null; i = i.prox) {
            for (Celula j = i.prox; j != null; j = j.prox) {
                if (j.freq > i.freq) {
                    if (i == novaCabeça) {
                         if (j == i.prox) {
                            novaCabeça = j;
                            i.prox = j.prox;
                            j.prox = i;
                        }
                        else {
                            novaCabeça = j;
                            aux = i.prox;
                            i.prox = j.prox;
                            j.prox = aux;
                            antj.prox = i;
                        }
                    }
                    else {
                        if (j == i.prox) {
                            i.prox = j.prox;
                            j.prox = i;
                            anti.prox = j;
                        }
                        else {
                            aux = i.prox;
                            i.prox = j.prox;
                            j.prox = aux;
                            antj.prox = i;
                            anti.prox = j;
                        }
                    }
                    aux = i;
                    i = j;
                    j = aux;
                }
                antj = j;
            }
            anti = i;
        }
        return novaCabeça;
    }


    /*Cria, a partir de um texto da entrada padrão, uma lista encadeada contendo
    todas as palavras deste texto, lexicograficamente ordenadas, com suas
    respectivas frequências e retorna a cabeça da lista.*/
    public static Celula read () {
        Celula cabeça, aux, i;
        cabeça = null;
        while (!StdIn.isEmpty()) {
            String s = Palavras.proximaPalavra ();

            //acha posição para inserir string s na lista encadeada
            aux = null;
            for (i = cabeça; i != null && s.compareTo (i.s) > 0; i = i.prox)
                aux = i;

            if (aux == null) {
                if (i != null &&              //s pertence a celula cabeça, logo
                    s.compareTo(i.s) == 0)    //apenas aumenta frequencia.
                    i.freq++;
                else {                        //insere celula na cabeça da
                    Celula nova;              //lista.
                    nova = new Celula (s, i);
                    cabeça = nova;
                }
            }
            else if (i == null) {             //insere celula no fim da lista
                Celula nova;
                nova = new Celula (s, null);
                aux.prox = nova;
            }
            else if (s.compareTo(i.s) == 0) { //celula com string s já existente
               i.freq++;                      //logo apenas aumenta frequência.
            }
            else {                            //insere entre celulas aux e i
                Celula nova;
                nova = new Celula (s, i);
                aux.prox = nova;
            }
        }
        return cabeça;
    }

    //imprime uma lista ordenada a partir da primeira celula, cabeça.
    public static void print (Celula cabeça) {
        Celula i;
        for (i = cabeça; i != null; i = i.prox)
            System.out.printf ("%s %d\n", i.s, i.freq);
    }

    public static void main (String[] args) {
        Celula cabeça;
        cabeça = read ();
        cabeça = sort (cabeça);
        print (cabeça);
    }

}
