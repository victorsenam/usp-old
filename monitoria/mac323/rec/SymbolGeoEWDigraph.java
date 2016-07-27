public class SymbolGeoEWDigraph {
	public static double[] Mouse(){
		StdOut.println("Clique em dois pontos da figura para definir o caminho.");
		double[] xy = new double[4];
			xy[0] = xy[1] = xy[2] = xy[3] = 0;

		boolean primeiroClick = true;
		boolean segundoClick  = false;

		//wait until user put 2 clicks
		while (true){
			if (StdDraw.mousePressed() && primeiroClick){
        		xy[0] = StdDraw.mouseX();
        		xy[1] = StdDraw.mouseY();
        		primeiroClick = false;
        	}

        	else if (StdDraw.mousePressed() && segundoClick){
        		xy[2] = StdDraw.mouseX();
        		xy[3] = StdDraw.mouseY();

        		// check for avoid 2 clicks with same coordinates
        		if (xy[2] == xy[0]){
        			xy[2] = 0;
        			xy[3] = 0;
        		}
        	}

        	if (!primeiroClick){
        		segundoClick = true;
        	}

        	if (xy[0] != 0 && xy[2] != 0) break;
        }

        return xy;
	}

	public static void Plot(double[] area, SymbolEWDigraph sg, String mode){
		EdgeWeightedDigraph G = sg.G();

		StdDraw.setCanvasSize(900, 650);
		StdDraw.setYscale(area[0], area[2]);
		StdDraw.setXscale(area[1], area[3]);

		RedBlackBST<Integer, Location> Map = sg.Map();

		StdOut.println("Ploting started.");
		StdDraw.setPenColor(StdDraw.BLACK);
		if (mode.equals("vertices")){
			for (int i : Map.keys()){
				Location loc = Map.get(i);
				StdDraw.point(loc.longitude(), loc.latitude());

			}
		}

		else if (mode.equals("arestas")){
			for (int v = 0; v < G.V(); v++){
				//Location loc = Map.get(v);
				//StdDraw.point(loc.latitude(), loc.longitude());

				for (DirectedEdge e : G.adj(v)){
					Location locFrom = Map.get(e.from());
					Location locTo   = Map.get(e.to());
					
					StdDraw.line(locFrom.longitude(), locFrom.latitude(), locTo.longitude(), locTo.latitude());
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledCircle(locFrom.longitude() - (locFrom.longitude() - locTo.longitude())/4, 
                     						locFrom.latitude() - (locFrom.latitude() - locTo.latitude())/4,
                    																				.00003);
                    StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}

		else if (mode.equals("all")){
			for (int v = 0; v < G.V(); v++){
				Location loc = Map.get(v);
				StdDraw.point(loc.latitude(), loc.longitude());

				for (DirectedEdge e : G.adj(v)){
					Location locFrom = Map.get(e.from());
					Location locTo   = Map.get(e.to());
					StdDraw.point(locFrom.longitude(), locFrom.latitude());
					StdDraw.point(locTo.longitude(), locTo.latitude());
					StdDraw.line(locFrom.longitude(), locFrom.latitude(), locTo.longitude(), locTo.latitude());
                    StdDraw.setPenColor(StdDraw.RED);
                    StdDraw.filledCircle(locFrom.longitude() - (locFrom.longitude() - locTo.longitude())/4, 
                     						locFrom.latitude() - (locFrom.latitude() - locTo.latitude())/4,
                    																				.00003);
                    StdDraw.setPenColor(StdDraw.BLACK);
				}
			}
		}
		else {
			StdOut.println("Invalid mode! Choose between vertices and all.");
		}
		StdOut.println("Ploting finished.");

	}

	public static void Replot(double[] area, SymbolEWDigraph sg, String mode){
		StdDraw.clear();
		Plot(area, sg, mode);
	}

	public static void ReplotClean(double[] area, SymbolEWDigraph sg, String mode){
		StdDraw.clear();
		Plot(area, sg, mode);
	}

	public static void main (String[] args){

	}
}
