// Autor:  Luiz Felipe Moumdjian Girotto                                                   
// Numero USP: 8941189                                    
// Data: 2016-02-28   

//////// *** O comando utilizado para rodar este programa é o seguinte: *** ////////
//////// *** java WordFrequencies | java Quick | java WordFrequencies2 | java MyQuickSort *** ////////

//////// *** Este programa se utiliza das bibliotecas StdIn, StdOut e StdRandom *** ////////
import edu.princeton.cs.algs4.*;
public class WordFrequencies {
    public static void main(String[] args) {
        
        // Este primeiro modulo recebe o texto pela entrada
        // padrao e devolve um arquivo de texto com cada
        // palavbra do texto original em lower case, sendo
        // exibida uma palavra por linha no arquivo de texto.
        
        while (!StdIn.isEmpty())  {
            System.out.printf ("%s\n", StdIn.readString().toLowerCase());
        }
        
    }
}
