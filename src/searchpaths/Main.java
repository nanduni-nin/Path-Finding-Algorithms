package searchpaths;

/**
 *
 * @author Nands
 */
public class Main {

    public static void main(String[] args){
	
        //Create the graph, add nodes, create edges between nodes
        Graph g=new Graph();
        
        //Initializes the nodes 
        Node[] nodes = new Node[25];
        for(int i=1;i<=25;i++){
            nodes[i-1] = new Node(i);                         //create a node 
            g.addNode(nodes[i-1]);                            //add the node to the graph        
        }
                
	g.setRootNode(nodes[1]);                              //set thr source node of the graph
        
        //create connections between nodes
        for (int i=1;i<=20;i++){                            
            if( i%5 != 0){                                    //if nodes are not at the edges of the grid
                g.connectNode(nodes[i-1], nodes[i]);     
                g.connectNode(nodes[i-1], nodes[i+4]);  
            }
            if(i%5 == 0){                                     //if nodes are at edges of the grid
                g.connectNode(nodes[i-1], nodes[i+4]);
            }
        }
        
        //if nodes are on the top row of the grid
        for(int i=21;i<25;i++){
            g.connectNode(nodes[i-1], nodes[i]);
        }
        
        //Creates a timer object
        Timer timer = new Timer();
        timer.start();
        
        //DEPTH FIRST SEARCH
       	System.out.println("******************** DFS Traversal of the grid : ******************** \n");
	g.dfs();                                                //calulates dfs for the grid
        timer.stop();
        System.out.println("Time Spent for DFS in nano seconds : " + timer.getElapsedTime() + "\n");
        
        //BREADTH FIRST SEARCH
        timer.start();
	System.out.println("\n\n\n******************** BFS Traversal of the grid : ******************** \n");
	g.bfs();                                                //calculates bfs for the grid
        timer.stop();
        System.out.println("Time Spent for BFS in nano seconds : " + timer.getElapsedTime() + "\n");         		
    }
}
