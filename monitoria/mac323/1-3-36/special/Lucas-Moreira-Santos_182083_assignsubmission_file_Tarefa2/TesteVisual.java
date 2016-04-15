/***************************************************************/
/**                                                           **/
/**   Lucas Moreira Santos                          9345064   **/
/**   Creative Problem 1.3.36                                 **/
/**   Professor:  Yoshiharu Kohayakawa                        **/
/**   MAC0323 - Algoritmos e Estruturas de Dados II           **/
/**                                                           **/
/***************************************************************/

import java.util.*;

public class TesteVisual<Item> {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int T = Integer.parseInt(args[1]);
    int i, j;
    HashMap<String, Integer> map = new HashMap<String, Integer>();

    for (i = 0; i < T; i++) {
      RandomQueue<Integer> queue = new RandomQueue<Integer>();

      for (j = 0; j < N; j++) {
        queue.enqueue(j);
      }

      j = 0;
      int[] values = new int[N];
      Iterator<Integer> iterator = queue.iterator();
      while (iterator.hasNext()) {
        values[j++] = iterator.next();
      }
      String valuesRepresentation = Arrays.toString(values);

      if (map.containsKey(valuesRepresentation))
        map.put(valuesRepresentation, map.get(valuesRepresentation) + 1);
      else
        map.put(valuesRepresentation, 1);
    }

    Histogram histogram = new Histogram(map.size());

    j = 0;
    for (String key : map.keySet()) {
      int count = map.get(key);
      for (i = 0; i < count; i++)
        histogram.addDataPoint(j);

      j++;
    }

    histogram.draw();
	}
}
