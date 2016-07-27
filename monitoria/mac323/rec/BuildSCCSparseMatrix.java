import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;

public class BuildSCCSparseMatrix {
    public static Digraph D;
    private static RedBlackBST<Integer,Integer> traduzVertices;
    private static int traducao, verticeZero;

    public BuildSCCSparseMatrix() {
        traduzVertices = new RedBlackBST<Integer, Integer>();
        traducao = 0;
    }

    public static void buildDigraph(String file) {
	    traduzVertices = new RedBlackBST<Integer, Integer>();
        try {

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

            D = new Digraph(nodes);

            while(sc.hasNext()){
                int s = sc.nextInt();
                int r = sc.nextInt();

                D.addEdge(s,r);
            }

        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    public static Digraph biggestSCCDigraph() {
        return biggestSCCDigraph(D);
    }

    public static Digraph biggestSCCDigraph(Digraph D2) {
    	//retorna digrafo contendo apenas a maior componente fortemente conexa
    	TarjanSCC tarjan = new TarjanSCC(D2);
    	int[] verticesInSCC = new int[tarjan.count()];
    	int biggestSCCCount = 0;
    	int biggestSCC = 0;

    	//acha qual e a maior componente fortemente conexa
    	for (int v = 0; v < D2.V() ; v++) {
    		int scc = tarjan.id(v);
    		verticesInSCC[scc]++;
    		if(verticesInSCC[scc] > biggestSCCCount){
    			biggestSCCCount = verticesInSCC[scc];
    			biggestSCC = scc;
    		}
    	}

        for(int v = 0; v < D2.V(); v++){
            if(tarjan.id(v) == biggestSCC)
                traduzVertices.put(v,traducao++);
        }

    	Digraph sccGraph = new Digraph(biggestSCCCount);

    	for (int v = 0; v < D2.V() ; v++) {
    		int scc = tarjan.id(v);

    		if(scc != biggestSCC) continue;
    		for(int neighbor : D2.adj(v)){
    			int neighborSCC = tarjan.id(neighbor);
    			if( neighborSCC != biggestSCC ) continue;
    			sccGraph.addEdge(traduzVertices.get(v), traduzVertices.get(neighbor));
    		}
    	}

    	return sccGraph;
    }

    public static SparseMatrix biggestSCCSparseMatrix(double alpha) {
        return biggestSCCSparseMatrix(D ,alpha);
    }

    public static SparseMatrix biggestSCCSparseMatrix(Digraph D2, double alpha) {
    	Digraph scc = biggestSCCDigraph(D2);
	    return BuildFullSparseMatrix.sparseMatrixFromDigraph(scc, alpha);

    }

    public static void main(String[] args) {
    	buildDigraph(args[0]);
    	SparseMatrix m = biggestSCCSparseMatrix(0.9);
    	StdOut.println(m.toString());
    }

}

