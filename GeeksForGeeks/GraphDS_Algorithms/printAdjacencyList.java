/* package whatever //do not write package name here
Print adjacency list
 Given number of edges 'E' and vertices 'V' of a bidirectional graph. Your task is to build a graph through adjacency list and print the adjacency list for each vertex.

Input:
The first line of input is T denoting the number of testcases.Then first line of each of the T contains two positive integer V and E where 'V' is the number of vertex and 'E' is number of edges in graph. Next, 'E' line contains two positive numbers showing that there is an edge between these two vertex.

Output:
For each vertex, print their connected nodes in order you are pushing them inside the list . See the  given  example.

Constraints:
1 <= T <= 200
1 <= V <= 103
1 <= E = V*(V-1)

Example:
Input:
1
5 7
0 1
0 4
1 2
1 3
1 4
2 3
3 4

Output:
0-> 1-> 4 
1-> 0-> 2-> 3-> 4 
2-> 1-> 3 
3-> 1-> 2-> 4 
4-> 0-> 1-> 3

Explanation:
Testcase 1: Given graph has 5 nodes and 7 edges. After creating adjacency list of given graph, we have list as:
0-> 1-> 4 
1-> 0-> 2-> 3-> 4 
2-> 1-> 3 
3-> 1-> 2-> 4 
4-> 0-> 1-> 3


*/


import java.util.*;
import java.lang.*;
import java.io.*;

public class PrintAdjacencyList {
	public static void main (String[] args) {
	    
	    AdjacencyListGraph adjListGraph = null;
	    
		Scanner scan = new Scanner(System.in);
		
		int T = scan.nextInt();
		//System.out.println(T);
		int V, E;
		for(int i = 0; i < T; i++){
		    V = scan.nextInt();
		    //System.out.println(V);
		    E = scan.nextInt();
		    //System.out.println(E);
		    adjListGraph = new AdjacencyListGraph(V, E);
    		for(int j = 0; j < E; j++){
    		    adjListGraph.addEdge(scan.nextInt(), scan.nextInt());
    		}
    		adjListGraph.printEdges();
    		adjListGraph = null;
		}
	}
}

class AdjacencyListGraph{
    private int numOfVertex;
    private int numOfEdges;
    private Map<Integer, List<Integer>> verticies;
    public AdjacencyListGraph(int numOfVertex, int numOfEdges)
    {
        this.numOfVertex = numOfVertex;
        this.numOfEdges = numOfEdges;
        
        this.verticies = new HashMap<Integer, List<Integer>>();
        
        for(int i = 0; i < numOfVertex; i++){
            this.verticies.put(i, new LinkedList<Integer>());
        }
        
    }
    
    public void addEdge(int fromVertex, int toVertex){
        List<Integer> connectedLinks = this.verticies.get(fromVertex);
        connectedLinks.add(toVertex);
        this.verticies.put(fromVertex, connectedLinks);
        connectedLinks = this.verticies.get(toVertex);
        connectedLinks.add(fromVertex);
        this.verticies.put(toVertex, connectedLinks);
    }
    
    public void printEdges(){
        for(int i = 0; i < numOfVertex; i++){
            System.out.print(i);
            List connectedLinks = this.verticies.get(i);
            for(int j = 0; j < connectedLinks.size(); j++){
                System.out.print("-> " + connectedLinks.get(j));
            }
            System.out.println();
        }
    }
}