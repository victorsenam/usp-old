import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class GeoInto {
    public static int long2int(long x){
        long r;
            if (x > Integer.MAX_VALUE) r = x % Integer.MAX_VALUE;
            else r = x;

        int saida = (int) r;
        return saida;
    }

    public static double[] ReadArea (String filename) throws Exception{
        double[] coord = new double[4]; 

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(filename));
        NodeList nodeList = document.getElementsByTagName("bounds");

        double minLat  = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("minlat").getNodeValue());
        double minLon  = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("minlon").getNodeValue());
        double maxLat  = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("maxlat").getNodeValue());
        double maxLon  = Double.parseDouble(nodeList.item(0).getAttributes().getNamedItem("maxlon").getNodeValue());



        coord[0] = minLat;
        coord[1] = minLon;
        coord[2] = maxLat;
        coord[3] = maxLon;

        return coord;
    }

    public static int SizeXML(String filename) throws Exception{
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(filename));
        NodeList nodeList = document.getElementsByTagName("node");

        int nodeLength = nodeList.getLength();

        return nodeLength;
    }

        

	public static RedBlackBST<Integer, Location> BuildMap(String filename, RedBlackBST<Long, Integer> identifierRB, int graphsize) throws Exception{
		RedBlackBST<Integer, Location> Map = new RedBlackBST<Integer, Location>();

        int identifier = 0; 

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new File(filename));
        NodeList nodeList = document.getElementsByTagName("node");
        for(int x = 0, size = nodeList.getLength(); x < size; x++) {
        	Long id  = Long.parseLong(nodeList.item(x).getAttributes().getNamedItem("id").getNodeValue());

            //keep 0 < id < MAX int to be a int value
            //int id2 = long2int(id);
        	float lat  = Float.parseFloat(nodeList.item(x).getAttributes().getNamedItem("lat").getNodeValue());
        	float lon  = Float.parseFloat(nodeList.item(x).getAttributes().getNamedItem("lon").getNodeValue());
            
            
            identifierRB.put(id, identifier);

        	Location loc = new Location(lat, lon, identifier);
        
        	Map.put(identifier, loc);
            
            identifier++;
        }

        return Map;
	}


    public static int CompareDistance(Location t, Location x, Location y) throws Exception{
        double dist1 = t.distanceTo(x);
        double dist2 = t.distanceTo(y);
        
        if      (dist1 >= dist2)         return x.id();
        else    /*(dist1 =< dist2)*/     return y.id();
    }
    
    public static int FindClosest(RedBlackBST<Integer, Location> Map, Location loc, EdgeWeightedDigraph Graph) throws Exception{
        int closer = 0;
        double dist = 0.0, min = Double.MAX_VALUE;
        Location aux;

        for(int key : Map.keys()){
            aux = Map.get(key);
            dist = loc.distanceTo(aux);
            if ((dist < min) && (Graph.outdegree(key) > 0)){
                min = dist;
                closer = key;
            }
        }
        
        return closer;
    }

    public static void main(String[] args) throws Exception{
    	String filename = args[0];
    	RedBlackBST<Long, Location> Map;
        
    }
}
