/*
 * Author Prasanna Kumar R
 * 800938424
 * prajend1@uncc.edu
 */



import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

class Edge implements Comparable<Vertex>{
    public Vertex adjVertex;
    public final double dist;
    public boolean isDown;
    public Edge( Vertex adjVertex, double dist) {
       this.adjVertex = adjVertex;
       this.dist = dist;
    }
@Override
	
	public int compareTo(Vertex ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
	
	
 }
// Represents a vertex in the graph.
class Vertex implements Comparable<Vertex>
{
    public String     name=null;   // Vertex name
    public TreeMap<String,Edge> adjEdge;    // Adjacent vertices
    public Vertex     prev;   // Previous vertex on shortest path
    public double        dist;   // .Distance of path
    public boolean    isDown;   // Distance of path
    public Comparable visited;
    public int vertexPosition;
   // public Map<String, Double> weightnext = new HashMap<String, Double>();
    public Vertex( String nm )
      { name = nm; adjEdge = new TreeMap<String,Edge>( ); reset( ); }

    public void reset( )
      { dist = Graph.INFINITY; prev = null;vertexPosition=0; }

	@Override
	
	public int compareTo(Vertex ob) {
        String tempA = this.toString();
        String tempB = ob.toString();
         
        return tempA.compareTo(tempB);
    }
	
	 public String toString() {
	        return name.toString();
	    }

   
}

//Implemented based on the Lap practice file

public class Graph
{
    public static final int INFINITY = Integer.MAX_VALUE;
    private Map<String,Vertex> vertexMap = new TreeMap<String,Vertex>( );
    
    /**
     * A main method that:
     * 1. Reads a file containing edges (supplied as a command-line parameter);
     * 2. Forms the graph;
     * 3. Repeatedly prompts for two vertices and
     *    runs the shortest path algorithm.
     * The data file is a sequence of lines of the format
     *    source destination and weight
     */
    public static void main( String [ ] args )
    {
        Graph g = new Graph( );
        try
        {
        	//String filePath = System.getProperty("user.dir") + "/"+ args[0];
    				
            FileReader fin = new FileReader( args[0]);
            Scanner graphFile = new Scanner( fin );

            // Read the edges and insert
            String line;
            while( graphFile.hasNextLine( ) )
            {
                line = graphFile.nextLine( );
                StringTokenizer st = new StringTokenizer( line );

                try
                {
                    if( st.countTokens( ) != 3 )
                    {
                        System.err.println( "Skipping ill-formatted line " + line );
                        continue;
                    }
                    String source  = st.nextToken( );
                    String dest    = st.nextToken( );
                    double	distance = Double.parseDouble(st.nextToken());
                    g.addEdge( source, dest,distance );
                    g.addEdge( dest, source,distance );
                }
                catch( NumberFormatException e )
                  { System.err.println( "Skipping ill-formatted line " + line ); }
             }
         }
         catch( IOException e )
           { System.err.println( e ); }

        // System.out.println( "File read..." );
        // System.out.println( g.vertexMap.size( ) + " vertices" );

         Scanner in = new Scanner( System.in );
         while( processRequest( in, g ) )
             ;
    }
    
    /**
     * Added for printing the whole graph
     */
    public static void printEntireGraph(Graph g){
    	
    	for(String v :g.vertexMap.keySet()){
    		
    		System.out.println(v+ ((g.vertexMap.get(v).isDown)?" DOWN":""));
    		
    		for (Edge adj :g.vertexMap.get(v).adjEdge.values()){
    				System.out.print('\t');
    				System.out.print(adj.adjVertex.name+" ");
    				System.out.print(adj.dist+ ((adj.isDown)?" DOWN":""));
    				System.out.println();
    		}
    		
    	}
    	
    	
    }
    /**
     * Add a new edge to the graph.
     */
    public void addEdge( String sourceName, String destName , Double distance)
    {
        Vertex v = getVertex( sourceName );
        Vertex w = getVertex( destName );
        v.adjEdge.put(w.name, new Edge(w,distance) );
       
       // v.weightnext.put(w.name,(Double) distance);
    }
    
    /**
     * Deletes the edge in the graph linking between the Vertices
     */
    public void deleteEdge( String sourceName, String destName)
    {
    	Vertex v = vertexMap.get( sourceName );
        Vertex w = vertexMap.get( destName );
        if(v!=null && w!=null)
        	v.adjEdge.remove(w.name);
       
       // v.weightnext.put(w.name,(Double) distance);
    }
    /**
     * Make the edge in the graph linking between the Vertices to down
     */
    public void makeEdgeDown( String sourceName, String destName)
    {
    	Vertex v = vertexMap.get( sourceName );
        Vertex w = vertexMap.get( destName );
        if(v!=null && w!=null)
        	v.adjEdge.get(w.name).isDown =true;
        else if(v==null){
        	System.out.println("Invalid Source vertex");
        }
        else if(w==null){
        	System.out.println("Invalid dest vertex");
        }
       // v.weightnext.put(w.name,(Double) distance);
    }
    /**
     * Make the edge in the graph linking between the Vertices to up
     */
    public void makeEdgeUp( String sourceName, String destName)
    {
        Vertex v = vertexMap.get( sourceName );
        Vertex w = vertexMap.get( destName );
        if(v!=null && w!=null)
        	v.adjEdge.get(w.name).isDown =false;
        else if(v==null){
        	System.out.println("Invalid Source vertex");
        }
        else if(w==null){
        	System.out.println("Invalid dest vertex");
        }
       
       // v.weightnext.put(w.name,(Double) distance);
    }
    public void makeVertexDown( String sourceName)
    {
        Vertex v = vertexMap.get( sourceName );
        if(v!=null)
        	v.isDown =true;
        else
        	System.out.println("Invalid vertex");
       
       // v.weightnext.put(w.name,(Double) distance);
    }
    /**
     * Make the edge in the graph linking between the Vertices to up
     */
    public void makeVertexUp( String sourceName)
    {
        Vertex v = vertexMap.get( sourceName );
        if(v!=null)
        	v.isDown =false;
        else
        	System.out.println("Invalid vertex");
        
       
       // v.weightnext.put(w.name,(Double) distance);
    }
    /**
     * Driver routine to print total distance.
     * It calls recursive routine to print shortest path to
     * destNode after a shortest path algorithm has run.
     */
    public void printPath( String destName )
    {
    	 DecimalFormat df = new DecimalFormat("###.##");
        Vertex w = vertexMap.get( destName );
        if( w == null )
            throw new NoSuchElementException( "Destination vertex not found" );
        else if( w.dist == INFINITY )
            System.out.println( destName + " is unreachable" );
        else
        {
            //System.out.print( "(Distance is: " + w.dist + ") " );
            printPath( w );
            System.out.println(df.format(w.dist) );
        }
    }
    
   
    /**
     * If vertexName is not present, add it to vertexMap.
     * In either case, return the Vertex.
     */
    private Vertex getVertex( String vertexName )
    {
        Vertex v = vertexMap.get( vertexName );
        if( v == null )
        {
            v = new Vertex( vertexName );
            vertexMap.put( vertexName, v );
        }
        return v;
    }

    /**
     * Recursive routine to print shortest path to dest
     * after running shortest path algorithm. The path
     * is known to exist.
     */
    private void printPath( Vertex dest )
    {
        if( dest.prev != null )
        {
            printPath( dest.prev );
           // System.out.print( " to " );
        }
        System.out.print( dest.name +" " );
    }
    
    /**
     * Initializes the vertex output info prior to running
     * any shortest path algorithm.
     */
    private void clearAll( )
    {
        for( Vertex v : vertexMap.values( ) )
            v.reset( );
    }

    /**
     * Single-source unweighted shortest-path algorithm.
     */
    public void unweighted( String startName )
    {
        clearAll( ); 

        Vertex start = vertexMap.get( startName );
        if( start == null )
            throw new NoSuchElementException( "Start vertex not found" );

        Queue<Vertex> q = new LinkedList<Vertex>( );
        q.add( start ); start.dist = 0;

        while( !q.isEmpty( ) )
        {
            Vertex v = q.remove( );

            for( Edge w : v.adjEdge.values() )
            {
                if( w.adjVertex.dist == INFINITY )
                {
                	w.adjVertex.dist = v.dist + 1;
                    w.adjVertex = v;
                    q.add( w.adjVertex );
                }
            }
        }
    }
   
    // Implemented using the Heap Data Structure
    
    /**
     * Implementation of Dijkstra's Shortest path algorithm using Heap data structure
     * Binary heap is considered here.
     * Vertex is stored as a vertex array object
     * Reset all the vertex of the graph
     * Initialize the source distance to zero and prev to null 
     * Insert the source vertex into the Queue
     * Loop through the adj vertex in the Edge, update the distance.
     * Populate the Queue with the Vertex if its position 
     * Maintain Heap order by running DecreaseKey.
     * Running time Should be O(logV)
     * Opertions 1. insert-- O(logV)
     * 			 2. HeapExtractMin-- O(logV)
     * 			 3. DecreaseKey -- O(logV)
     */
    public void findShortestPathHeap( Graph gr,String sourceName, String destName)
    {
    	int vertex_size = gr.vertexMap.values().size();
    	HeapImpl mh = new HeapImpl(vertex_size);
        Vertex v = getVertex( sourceName );
        Vertex processing;
        for(Vertex ver: gr.vertexMap.values()){
        	ver.reset();
        }
       v.dist=0;
       v.prev=null;
       mh.insert(v);
       while (mh!=null && !mh.isEmpty()){
    	   processing= mh.HeapExtractMin();
    	   if(processing.dist==Integer.MAX_VALUE){
    		   break;
    	   }
    	   for(Edge adjEdge: processing.adjEdge.values()){
    		   
    		   if(!adjEdge.isDown && !adjEdge.adjVertex.isDown ){
    			   if(adjEdge.adjVertex.dist>(processing.dist+adjEdge.dist)){
    				  //Q.delete(adjEdge.adjVertex);
    				   adjEdge.adjVertex.dist= processing.dist+adjEdge.dist;
    				   adjEdge.adjVertex.prev=processing;
    				   if(adjEdge.adjVertex.vertexPosition==0)
    					   adjEdge.adjVertex.vertexPosition= mh.insert( adjEdge.adjVertex);
    				   else
    					   mh.decreaseKey(adjEdge.adjVertex.vertexPosition-1, adjEdge.adjVertex);
    				   
    			   }
    		   }
    		   
    		   
    	   }
       }
    }
    /**
     * This Method prints all the vertex in the sorted order
     */
    public void findPath(Vertex v, Graph g)
    {
    	Queue<Vertex> q = new LinkedList<Vertex>();
    	Set<String> visited= new HashSet<String>();
    	Set<String> edges= new TreeSet<String>();
    	
    	 	q.add(v);
    	 	visited.add(v.name);
    	 	
    	 	while(!q.isEmpty()){
    	 		Vertex vertex = q.poll();
    	 		 
    	   for(Edge adjEdge: vertex.adjEdge.values()){
    		   if(!visited.contains(adjEdge.adjVertex.name) && !adjEdge.adjVertex.isDown && !adjEdge.isDown){
    			   q.offer(adjEdge.adjVertex);
    			   visited.add(adjEdge.adjVertex.name);
    			   
    			   edges.add(adjEdge.adjVertex.name);
    			   
    		   }
    	   }
    	   
    	 }
    	 	for(String str: edges){
     		   System.out.print('\t');
     		   System.out.println(str);
     	   }
    	 	
       }
       // v.weightnext.put(w.name,(Double) distance);
    

    /**
     * Process a request; return false if end of file.
     */
    public static boolean processRequest( Scanner in, Graph g )
    {
        try
        {
        	
        	Scanner scan = new Scanner(System.in);
        	String query = scan.next();
        	
        	if("print".equals(query)){
        		
        		printEntireGraph(g);
        	}
        	else if("addedge".equalsIgnoreCase(query)){
        		String source = scan.next();
        		String dest = scan.next();
        		double distance = scan.nextDouble();
        		g.addEdge( source,dest, distance);
        	}else if("deleteedge".equalsIgnoreCase(query)){
        		String source = scan.next();
        		String dest = scan.next();
        		g.deleteEdge(source,dest);
        	}else if("edgedown".equalsIgnoreCase(query)){
        		String source = scan.next();
        		String dest = scan.next();
        		g.makeEdgeDown(source,dest);
        	}else if("edgeup".equalsIgnoreCase(query)){
        		String source = scan.next();
        		String dest = scan.next();
        		g.makeEdgeUp( source,dest);
        	}else if("vertexdown".equalsIgnoreCase(query)){
        		String source = scan.next();
        		g.makeVertexDown(source);
        	}else if("vertexup".equalsIgnoreCase(query)){
        		String source = scan.next();
        		g.makeVertexUp(source);
        	}else if("path".equalsIgnoreCase(query)){
        		String source = scan.next();
        		String dest = scan.next();
        		g.findShortestPathHeap(g, source, dest);
        		g.printPath(dest);
        	}else if("reachable".equalsIgnoreCase(query)){
        		for(Vertex v: g.vertexMap.values()){
        			if(!v.isDown){
        					System.out.println(v.name);
        			//System.out.print('\t');
        					g.findPath(v,g);
        			}
        		}
        		
        	}
        	
           //System.out.print( "Enter start node: " );
            //String startName = in.nextLine( );

            //System.out.print( "Enter destination node: " );
            //String destName = in.nextLine( );

            //g.unweighted( startName );
            //g.printPath( destName );
        }
        catch( NoSuchElementException e )
          { return false; }
        return true;
    }
  
    
    
  class HeapImpl {

	  private Vertex[] ver;// Array for holding the Vertex in the path, this is of type Vertex
	  private int size =0;
        public HeapImpl(int size) {

        	 this.ver= new Vertex[size];
        }

       public int insert(Vertex item) {

            ver[size++]= item;
            item.vertexPosition=size-1;
            int i = size - 1;
            int parent = parent(i);
            while (parent != i && ver[i].dist < ver[parent].dist) {

                swapping(i, parent);
                i = parent;
                parent = parent(i);
            }
            return size;
         }

        public void buildHeapTree() {

            for (int i = ver.length / 2; i >= 0; i--) {
                heapMinHeapify(i);
            }
        }

        public Vertex HeapExtractMin() {
        	
        	
       // Size of the queue is one, then minimum is that element

           if (size == 1) {
            	
                Vertex min = ver[0];
                size--;
                return min;
            }

            // Getting the last element and making it root element of the binary tree
            Vertex min = ver[0];
            Vertex lastItem = ver[size-1];
            ver[0]= lastItem;
            size--;
            // Calling heapMinHeapify to maintain heap property
            heapMinHeapify(0);
            return min;
        }

        public void decreaseKey(int queuePostion, Vertex vertex) {

        
            if(ver[queuePostion].dist > vertex.dist){
            ver[queuePostion] = vertex;
            int parent = parent(queuePostion);

            // Looping to obtain heap order
            while (queuePostion > 0 && (ver[parent].dist >ver[queuePostion].dist )) {

                swapping(queuePostion, parent);
                queuePostion = parent;
                parent = parent(parent);
            }
            }
        }

        private void heapMinHeapify(int ele) {

            int left = left(ele);
            int right = right(ele);
            int min = -1;
            if (left <= size - 1 && ver[left].dist < ver[ele].dist) {
                min = left;
            } else {
                min = ele;
            }

            if (right <= size - 1 && ver[right].dist < ver[min].dist) {
                min = right;
            }

            // 
            // Still if "i" is not the smallest, then exchange it with the smallest and recurse heapMinHeapify till smallest element
            if (min != ele) {

                swapping(ele, min);
                heapMinHeapify(min);
            }
        }

        

        public boolean isEmpty() {

            return size == 0;
        }

        private int right(int ele) {

            return 2 * ele + 2;
        }

        private int left(int ele) {

            return 2 * ele + 1;
        }

        private int parent(int ele) {

            if (ele % 2 == 1) {
                return ele / 2;
            }

            return (ele - 1) / 2;
        }
        

        private void swapping(int ele, int parent) {

            Vertex temp = ver[parent];
            ver[parent]=ver[ele];
            ver[ele]=temp;
           
        }

    }
    	
       	
    	
    }
