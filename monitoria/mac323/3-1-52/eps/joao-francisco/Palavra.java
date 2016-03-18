/* Define Palavra como uma junção de string com uma contagem
//
// Autor: João Francisco Lino Daniel
//              7578279
*/

public class Palavra implements Comparable<Palavra> {
    private String conteudo;
    private int frequencia;

    public Palavra(String conteudo, int frequencia) {
        this.conteudo = conteudo;
        this.frequencia = frequencia;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public int getFrequencia() {
        return this.frequencia;
    }

    public int compareTo(Palavra outra) {
        return outra.getFrequencia() - this.frequencia;
    }

    public String toString() {
        return this.conteudo + " " + this.frequencia;
    }
}
