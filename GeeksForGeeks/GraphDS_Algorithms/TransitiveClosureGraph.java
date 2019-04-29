
import java.util.*;
import java.lang.*;
import java.io.*;
/*
Given a directed graph, find out if a vertex j is reachable from another vertex i for all vertex pairs (i, j) in the given graph. Here reachable mean that there is a path from vertex i to j. The reach-ability matrix is called transitive closure of a graph.

Input:
First line consists of T test cases. First line of every test case consists of N , denoting number of vertices. Second line consists of N*N spaced integer(Only 0 and 1), denoting the edge b/w i to j.

Output:
Single line output, print the trasitive closure formed of graph.

Constraints:
1<=T<=100
1<=N<=100

Example:
Input:
1
4
1 1 0 1 0 1 1 0 0 0 1 1 0 0 0 1
Output:
1 1 1 1 0 1 1 1 0 0 1 1 0 0 0 1
*/
/*
Logic: Traverse each node and update the entire row if there is link from i vertex to j vertex. Update entire i row with j row. Peform DFS first and then update.
DFS will ensure the traversed node is also a TransitiveClosureGraph.
*/

class TransitiveClosureGraph {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int T = scan.nextInt();
		int N;
		int[] input;
		while(T > 0){
		    N = scan.nextInt();
		    input = new int[N*N];
		    for(int i = 0; i < N*N; i++){
		        input[i] = scan.nextInt();
		    }
		    Graph g = new Graph(N, input);
		    g.findTransitiveClosure();
		    g.printTransitiveResult();
		    T--;
		}
	}
}

class Graph{
    private int vertex;
    private int edges[][];
    
    private int transitiveResult[][];
    public Graph(int vertex, int[] input){
        this.vertex = vertex;
        edges = new int[this.vertex][this.vertex];
        transitiveResult = new int[this.vertex][this.vertex];
        for(int i = 0; i < this.vertex; i++){
            for(int j = 0; j < this.vertex; j++){
                this.edges[i][j] = input[i*this.vertex + j];
                this.transitiveResult[i][j] = input[i*this.vertex + j];
            }
        }
    }
    
    public void findTransitiveClosure(){
        boolean visited[] = new boolean[this.vertex];
        for(int i = 0; i < visited.length; i++)
            visited[i] = false;
        //printMatrix(edges);
        for(int i = 0; i < this.vertex; i++)    {
            //if(visited[i] != true){
            findTransitiveClosureUtil(i, visited);
            //}
            //System.out.println("ROW : " + i);
            //printMatrix(transitiveResult);
            //System.out.println();
        }
        
            
    }
    
    private void findTransitiveClosureUtil(int row, boolean[] visited){
        visited[row] = true;
        for(int j = 0; j < this.vertex; j++){
            
            if(transitiveResult[row][j] == 1 && visited[j] != true){
                findTransitiveClosureUtil(j, visited);
            }
            if(transitiveResult[row][j] == 1 && row != j){
                updateCurrentRow(row, j);
            }
            
        }
        this.transitiveResult[row][row] = 1;
    }
    
    private void updateCurrentRow(int currentRow, int DFSRow){
        for(int k = 0; k < this.vertex; k++){
            this.transitiveResult[currentRow][k] = this.transitiveResult[currentRow][k] | this.transitiveResult[DFSRow][k];
            //if(currentRow == 1 && k == 4 && DFSRow == 0){
                //System.out.println("Check - " + this.transitiveResult[currentRow][k] + "|"+ this.transitiveResult[DFSRow][k] + " : " + (this.transitiveResult[currentRow][k] | this.transitiveResult[DFSRow][k]) + "");
            //}
        }
    }
    
    public void printTransitiveResult(){
        for(int i = 0; i < this.vertex; i++){
            for(int j = 0; j < this.vertex; j++){
                System.out.print(this.transitiveResult[i][j] + " ");
            }
        }
        System.out.println();
    }
    
    private void printMatrix(int[][] matrix){
        for(int i = 0; i < this.vertex; i++){
            for(int j = 0; j < this.vertex; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
    }
        
}