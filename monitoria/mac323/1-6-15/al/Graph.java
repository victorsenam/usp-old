public class Graph {
    static int n, m, h, a;
    static double qha, restQha;
    static boolean[] hubs, authorities;
    static int[] inDegree, outDegree;
    static int[][] connections;
    
    public static void restartArray (boolean[] array)
    {
        int n = array.length;
        for (int i = 0; i < n; i++) array[i] = false; 
    }
    
    public static int searchPage (boolean[] array, int random)
    {
        int begin = (random + 1) % n;
        int end = random % n;
        
        while (begin != end) //while the loop doesn't verify the whole array
        {
            if (!array[begin]) return begin; //a free page has been found
                
            begin++;
            begin %= n;
        }      
        return -1; //there is no free page
    }  
    
    public static void createHubsAndAuthorities (boolean isHub, int quantity, boolean[] array)
    {
        int n = connections[0].length;
        boolean[] pages = new boolean[n];
        int rha, rp;
        
        for (int i = 1; i <= quantity; i++)
        {
            //create the hub/authority
            rha = StdRandom.uniform (0, n);
            
            if (array[rha]) rha = searchPage (array, rha); //if a page has already been selected, search a page that has not 
            array[rha] = true;
            
            //randomly select the pages that wiil be connected with the hub/authority
            for (int j = 1; j <= qha; j++)
            {
                rp = StdRandom.uniform (0, n);
                if (pages[rp]) rp = searchPage (pages, rp);
                pages[rp] = true;
                
                if (isHub)
                {
                    connections[rp][rha]++;
                    outDegree[rp]++;
                    inDegree[rha]++;
                }
                else 
                {
                    connections[rha][rp]++; 
                    outDegree[rha]++;
                    inDegree[rp]++;
                }
            }    
            
            restartArray(pages);
        }
    }    
    
    public static int respectLimit (boolean[] array, int[] typeDegree)
    {
        int n = array.length;
        int random = StdRandom.uniform (0, n);
        
        while (true)
        {
            if (array[random] || typeDegree[random] + 1 < qha)
            {
                typeDegree[random]++;
                return random;
            }
            
            random = (random + 1) % n;
        }
    }
    
    public static void createGraph ()
    {
        int a, b;
        for (int i = 1; i <= restQha; i++)
        {
            a = respectLimit(authorities, outDegree); //respect the limit of authorities
            b = respectLimit(hubs, inDegree); //respect the limit of hubs
            connections[a][b]++;
        }
    } 
    
    public static void printPairs ()
    {
        int n = connections[0].length;
        
        //print hubs
        /*
        StdOut.print ("Hubs: ");
        for (int i = 0; i < n; i++)
        {
            if (hubs[i]) StdOut.print (i + "  ");
        }
        */
        
        StdOut.println ();
        
        //print authorities
        /*
        StdOut.print ("Authorities: ");
        for (int i = 0; i < n; i++)
        {
            if (authorities[i]) StdOut.print (i + "  ");
        }
        */
        
        StdOut.println ();
        
        //print pairs
        StdOut.println (n);
        int count = 0;
        for (int i = 0; i < n; i++)
        {
            count = 0;
            for (int j = 0; j < n; j++)
            {
                if (connections[i][j] > 0)
                {
                    count = 0;
                    while (count < connections[i][j])
                    {
                        StdOut.print (i + " " + j + "  ");
                        count++;
                    }
                }
            }
            
            if (count != 0) StdOut.println ();
        } 
    }  
    
    public static boolean findException ()
    {
        if (n < 0 || m < 0 || h < 0 || a < 0)
        {
            StdOut.println ("Os valores nao devem ser menores que zero");
            return true;
        }
        if (h + a > n)
        {
            StdOut.println ("A soma de hubs e de authorities nao deve ser maior que o numero de paginas");
            return true;
        }
        
        if (qha * (h + a) > m) 
        {
            StdOut.println ("Sao necessarios, no minimo, " + qha * (h + a) + " arestas para gerar os hubs e os authorities");
            return true;
        }
        
        return false;  
    }
    
    public static void main(String[] args) 
    { 
        n = Integer.parseInt (args[0]); //number of pages
        m = Integer.parseInt (args[1]); //number of edges
        h = Integer.parseInt (args[2]); //number of hubs
        a = Integer.parseInt (args[3]); //number of authorities
        
        connections = new int[n][n];
        hubs = new boolean[n];
        authorities = new boolean[n];
        inDegree = new int[n];
        outDegree = new int[n];
        qha = Math.ceil (0.1 * n); //minimum quantity of pages to connect with hubs/authorities
        restQha = m - qha * (h + a); //edges available to the pages that are neither hubs nor authorities
        
        boolean found = findException ();
        
        if (!found)
        {
            //create hubs
            createHubsAndAuthorities (true, h, hubs); 
            
            //create authorities
            createHubsAndAuthorities (false, a, authorities); 
            
            //create graph
            createGraph ();
            
            //print the pairs
            printPairs ();
        }
    }
}
