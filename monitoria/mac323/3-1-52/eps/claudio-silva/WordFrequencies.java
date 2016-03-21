import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Comparator;
import java.util.HashMap; 
import java.util.LinkedHashMap; 

public class WordFrequencies {

    public static void main(String[] args) {
    	
    	String text = StdIn.readAll();
    	text = text.replaceAll("[\\s,\n,\t]","" );//LIMPA A STRING REMOVENDO ESPACOS

		HashMap<String, Integer> map = new HashMap<String, Integer>();// MEU HASHMAP
		for(String key : text.split("")) {// SE JA ESTA NOHASH SOMA 1
		  if(map.containsKey(key))
		  	map.put(key, map.get(key) + 1);
		  else // CASO NAO INICIASSE ESSE VALOR
		  	map.put(key,1);
		}

		StdOut.print(ordenaHash(map));

    }
public static LinkedHashMap ordenaHash(HashMap myMap) {
   
   //PEGA VALORES DO MAP
   List meusValores = new ArrayList(myMap.values());
   Collections.sort(meusValores);
   
   // PEGA AS CHAVEZ DO MAP
   List keys = new ArrayList(myMap.keySet());
   Collections.sort(keys);

   // CRIA UM MAP PARA QUARGAR OS PARES ORDENADOS
   LinkedHashMap ordenado = new LinkedHashMap();

 	// CONTEUDO PARA PERCORRER
   Iterator valores = meusValores.iterator();
   while (valores.hasNext()) {
       Object val = valores.next();
       Iterator chavez = keys.iterator();

       while (chavez.hasNext()) {
           String aux = val.toString();
           Object key = chavez.next();
           String tmp = myMap.get(key).toString();

           // COMPARA E TROCA OS PARA ORDENACAO
           if (tmp.equals(aux)){
               keys.remove(key);
               myMap.remove(key);
               ordenado.put((String)key, (int)val);
               break;
           }
       }
   }
   return ordenado;
}


}
