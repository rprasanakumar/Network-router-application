
/*		Author: PRASANNA KUMAR RAJENDRAN
*		800#  : 800938424
*		email : prajend1@uncc.edu
		Programming language: JAVA
		Compiler version: java8
*/
This zip contains the Execution file for Graph.

	This implementation is realized in JAVA programming language.

Graph-->Graph.java

Graph.java:
	This java is implemented in with three Classes with main routine execution in Graph Class.

	1. processRequest is a boolean method, which takes query to be processed and Graph g as an input. This method is used to read the input query and redirect to appropriate method of execution.
	2. printEntireGraph	 is a void method, which prints the entire vertex name of the Graph g 
	3. addEdge is a void method, which adds Source and Dest edge in this order and with the given weight.
	4. deleteEdge is a void method, which removes the given Source and Dest.
	5. makeEdgeDown is a void method, which makes the given Source and Dest edge down.
	6. makeEdgeUp is a void method, which makes the given Source and Dest edge up.
	7. makeVertexDown is a void method, which makes the given Source vertex down.
	8. makeVertexUp is a void method, which makes the given Source vertex up.
	9. printPath for printing the path after Dijstra's is run.
	10. findShortestPathHeap, is the driver method for finding the shortest path using Dijstra's algorithm.
	
	
			Analysis:
			   
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
	
	11. findPath, is the driver method for finding the reachable path in the Graph.
	

	Logic:
		This program mainly uses the Dijkstra's algorithm and Binary Heap implementation.
		
		
		
DataStructure:


HeapImpl Class: Contains insert, which is used to insert the Vertex into the Queue and returns the position; HeapExtractMin, used to get the minimum element in the Heap; decreaseKey is used to maintain the heap order, taking the Current position of the Vertex and Vertex itself; heapMinHeapify is used to maintain the HeapOrder after Extracting of minimum element;



	HashSet and TreeSet collection datastructure is used in the implementation of the Reachable .
	where the key is String and value is INteger. 

			


Execution steps:

<Default operating system considered is Windows>
	1. Extract the zip in the Desktop.
	2. In command prompt Navigate to the javac path, usually(C:\Program Files\Java\jdk1.8.0_73\bin\) key in path of the above extracted JAVA file in the desktop.
	3. Run javac <"path of the above desktop folder">\Graph.java 
	4. Class files are generated through the step 3 and 4
	5. now, navigate to the "path of the above desktop folder"
	6. For performing Opertions on a Graph.txt file, Key in java Graph <txt file name>
	7. Control awaits for your query
	8. Different query like "path Belk Education" can be tried



Conditions succeeded:

	> The program as succeeded the example input sets upto 150 Vertex Graph

	
	> Time complexity taken is less than 60 ms
	
	


