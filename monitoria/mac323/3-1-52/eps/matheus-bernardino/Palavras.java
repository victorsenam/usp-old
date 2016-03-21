/*******************************************************************************

Palavras

Nome:   Matheus Tavares Bernardino
Nº USP: 9292987
Data:   11/03/2016


Descrição:
Esta classe transforma palavras de um texto direcionado para a entrada padrão
em Strings. São consideradas palavras, sequencias de caracteres válidos. Estes
caracteres, podem ser letras (maiúsculas, mínusculas, com ou sem acento), hífens
e números. (exclui-se os demais)


Compilação:    javac-algs4 Palavras.java
Execução:      java-algs4 Palavras < entrada.txt

Ex de execução:

Comando 1:
    $ cat entrada.txt
Saída:
    O sapo não lava o pé. Não lava porque não quer. Mas que chulé!

Comando 2:
    $ java-algs4 Palavras < entrada.txt
    
Saída:
    o
    sapo
    não
    lava
    o
    pé
    não
    lava
    porque
    não
    quer
    mas
    que
    chulé

*******************************************************************************/

import edu.princeton.cs.algs4.*;

public class Palavras {

    /*retorna true se o caracter é valido, isto é, pode formar uma palavra, e
    false caso contrário. São considerados caracteres validos as letras
    (maiúsculas e mínusculas, com e sem acentos) os números e o hífen.*/
    private static boolean caracterValido (char c) {
        if (c <= 37 || (c >= 39 && c <= 44) || (c >= 58 && c <= 64) ||
        (c >= 91 && c <= 96) || (c >= 123 && c <= 191) || c == 46 ||
        c == 47 || c == 215 || c == 216 || c == 247 || c == 248)
            return false;
        return true;
    }

    /*retorna em uma string a proxima palavra de um texto na entrada padrão.
    Observação: método assume que a entrada padrão não está vazia.
    */
    public static String proximaPalavra () {
        char c;
        String s;
        do{
            c = StdIn.readChar ();
            s = new String ();
            while (caracterValido (c)) {
                s = s.concat (Character.toString(c));
                c = StdIn.readChar ();
            }
            s = s.toLowerCase ();
        } while (s.compareTo("") == 0);
        return s;
    }

    //método para testes
    public static void main (String[] args) {
         while (!StdIn.isEmpty()) {
            String s = proximaPalavra ();
            System.out.printf ("%s\n", s);
         }
    }

}
