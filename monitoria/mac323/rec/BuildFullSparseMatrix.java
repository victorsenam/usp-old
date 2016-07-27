import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


public class BuildFullSparseMatrix {
    public static SparseMatrix transition;

    public BuildFullSparseMatrix (){
    }

    // leitura arquivos 
    public static SparseMatrix mountTransitionMatrix(String file, double alpha) {
        try {
            RedBlackBST<Integer, Integer> st = new RedBlackBST<Integer, Integer>();
            Scanner sc = new Scanner(new File(file));
            int nodes = 0;
            int edges = 0;

            String line = sc.nextLine();
            line = sc.nextLine();
            sc.findInLine("# Nodes: (\\d+) Edges: (\\d+)");
            MatchResult result = sc.match();
            nodes = Integer.parseInt(result.group(1));
            edges = Integer.parseInt(result.group(2));

            line = sc.nextLine();
            line = sc.nextLine();

            transition = new SparseMatrix(nodes);

            while(sc.hasNext()){
                int s = sc.nextInt();
                int r = sc.nextInt();

                // Checando se a aresta de s pra r já foi vista no grafo
                double linksBetweenSandR = transition.get(s, r);

                if(linksBetweenSandR == 0) transition.put(s, r, 1);
                // Se não foi, insere a aresta
                // Se já foi inserido, atualiza a quantidade de vezes que apareceu
                else
                    transition.put(s, r, ++linksBetweenSandR);

                // Quantidade de arestas saindo de um nó, que é diferente da quantidade de não nulos,
                // pois pode haver mais de um link saindo do mesmo nó para outro mesmo nó
                Integer value = st.get(s);
                if(value == null)
                    st.put(s, new Integer(1));
                else
                    st.put(s, new Integer(value.intValue() + 1));
            }

             for(int i = 0; i < transition.dimension(); i++){
                    Integer numLinks = st.get(i);

                    if(numLinks == null )   continue;

                    for(int column : transition.rows[i].st.keys()){

                        double normalizedValue = alpha * transition.rows[i].get(column) / numLinks.intValue();
                        transition.put(i, column, normalizedValue);
                    }
                }
        }
        catch(Exception e){
            e.printStackTrace();
        }

	    return transition;
    }

    
    public static SparseMatrix sparseMatrixFromDigraph(Digraph D2, double alpha) {
     	SparseMatrix m = new SparseMatrix(D2.V());

    	for(int v = 0; v < D2.V(); v++){
    		int linksFromV = D2.outdegree(v);

            if(linksFromV == 0) continue;

    		for(int col : D2.adj(v)){
    			double jumpProb = alpha/linksFromV;
    			double probFromVtoCol = m.get(v,col);
    			if(probFromVtoCol == 0)
    				m.put(v,col, jumpProb);
    			else
    				m.put(v,col, (probFromVtoCol + jumpProb));
    		}
    	}

    	return m;
    }

    public static void main(String[] args){
        transition = mountTransitionMatrix(args[0], 0.9);
        StdOut.println(transition.toString());
    }

}

