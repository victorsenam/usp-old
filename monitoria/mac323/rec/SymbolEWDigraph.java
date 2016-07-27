public class SymbolEWDigraph {
    private RedBlackBST<Integer, Location> Map;       
    private EdgeWeightedDigraph G;
    private Digraph D;

    public static int long2int(long x){
    	long r;
            if (x > Integer.MAX_VALUE) r = x % Integer.MAX_VALUE;
            else r = x;

        int saida = (int) r;
    	return saida;
    }

    // Mudei a ordem aqui

    public SymbolEWDigraph(String filenameADJ, String filenameXML) throws Exception {

        int xmlSize = GeoInto.SizeXML(filenameXML);
        G = new EdgeWeightedDigraph(xmlSize);
	D = new Digraph(xmlSize);
        In in = new In(filenameADJ);
        String removeHeader;

        // 1. builds a RBBST that relate map id with an identifier
        // between 0 and graph size - 1
        RedBlackBST<Long, Integer> Identifier = new RedBlackBST<Long, Integer>();
        
        // 2. pass builds the index by reading Locations to associate
        // distinct Locations with an index
    	Map = GeoInto.BuildMap(filenameXML, Identifier, xmlSize);

        
        // remove adjlist header
        removeHeader = in.readLine();
        removeHeader = in.readLine();
        removeHeader = in.readLine();

        // 3. pass builds the digraph by connecting first vertex on each
        // line to others with the adj file

        while (in.hasNextLine()) {
            String[] a = in.readLine().split(" ");

            long auxA0 = Long.parseLong(a[0]);
            long vKey = auxA0;
            int identifier_vKey = Identifier.get(vKey);
            Location v = Map.get(identifier_vKey);

            if(a.length > 1){
                for(int i = 1; i < a.length; i++){

                    long auxA1 = Long.parseLong(a[i]);
                    long wKey = auxA1;
                    int identifier_wKey = Identifier.get(wKey);

                    Location w = Map.get(identifier_wKey);

                    double dist = v.distanceTo(w);

                    DirectedEdge e = new DirectedEdge(identifier_vKey, identifier_wKey, dist);
                    G.addEdge(e);
		    D.addEdge(identifier_vKey, identifier_wKey);
                    
                }
            }
        }
    }

    public boolean contains(int s) {
        return Map.contains(s);
    }

    public Location index(int s) {
        return Map.get(s);
    }


    public EdgeWeightedDigraph G() {
        return G;
    }
    
    public Digraph D(){
	return D;
    }

    public RedBlackBST<Integer, Location> Map(){
    	return Map;
    }

    public static void main(String[] args) throws Exception{
        String filenameADJ  = args[0];
        String filenameXML = args[1];
        SymbolEWDigraph sg = new SymbolEWDigraph(filenameADJ, filenameXML);
        EdgeWeightedDigraph G = sg.G();
        
        int s = 1;
        DijkstraSP sp = new DijkstraSP(G, s);
        // print shortest path
        for (int t = 0; t < G.V(); t++) {
            if (sp.hasPathTo(t)) {
                StdOut.printf("%d to %d (%.2f) \n  huezito   ", s, t, sp.distTo(t));
                if (sp.hasPathTo(t)) {
                    for (DirectedEdge e : sp.pathTo(t)) {
                        StdOut.print(e + "   ");
                    }
                }
                StdOut.println();
            }
            else {
                //StdOut.printf("%d to %d         no path\n", s, t);
            }
        }
    }
}
