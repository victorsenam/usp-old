/*
 * NOME:   Éderson Cássio Lacerda Ferreira
 * N. USP: 9297975
 */

// Para comparar corretamente letras acentuadas!
// Ref.: http://docs.oracle.com/javase/7/docs/api/java/text/Collator.html
import java.text.Collator;

public class Palavra {

   private String texto;
   private int frequencia;

   public Palavra(String texto) {
      this.texto = texto.toLowerCase();
      this.frequencia = 1;
   }
   
   public void aumenta() {
      this.frequencia++;
   }
   
   public String getTexto() {
      return texto;
   }
   
   public int getFrequencia() {
      return frequencia;
   }
   
   public boolean mesmaQue(Palavra outra) {
      return outra != null && outra.getTexto().equals(texto);
   }
   
   public boolean vemAntesDe(Palavra outra) {
      return outra != null && 
             Collator.getInstance().compare(texto, outra.getTexto()) < 0;
   }   

}
