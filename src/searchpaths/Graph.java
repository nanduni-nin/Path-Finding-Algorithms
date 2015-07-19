package searchpaths;

/**
 *
 * @author Nands
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*Class for the grid and the implementation of DFS and BFS algorithms*/
public class Graph 
{
	public Node rootNode;                           //source node
	public ArrayList<Node> nodes=new ArrayList();   //list of nodes in the graph
	public int[][] adjMatrix;                       //edges will be represented as an adjacency Matrix
	int size;                                       //size of the nodes-ArrayList
        
        //set the starting node or the source node
	public void setRootNode(Node n){
		this.rootNode=n;
                this.rootNode.setPred(null);            //source node has no predecessor
	}
	
        //get the source node
	public Node getRootNode(){
		return this.rootNode;
	}
	
        //add nodes to the nodes-ArrayList
	public void addNode(Node n){
		nodes.add(n);
	}
	
	//This method will be called to connect two nodes
	public void connectNode(Node start,Node end){            
                //initialize the adjecency matrix
		if(adjMatrix==null){
			size = nodes.size();
			adjMatrix=new int[size][size];
		}
                
                //Get the starting and ending indices of nodes
		int startIndex=nodes.indexOf(start);
		int endIndex=nodes.indexOf(end);
                
                //connect the two nodes
		adjMatrix[startIndex][endIndex]=1;
		adjMatrix[endIndex][startIndex]=1;
        }
	
        //This method will return the child nodes which are adjacent to the parent node but not discovered yet
	private Node getUnvisitedChildNode(Node n){		
		int index = nodes.indexOf(n);                       //index of the parent node
		int j=0;
                //This loop finds all the neighbouring nodes around a node
		while(j<size){
                        //if the nodes are adjacent and not visted
			if(adjMatrix[index][j]==1 && ((Node)nodes.get(j)).visited==false){
                                Node child = (Node)nodes.get(j);
                                child.setPred(n);                   //set the predecessor node of the child node as parent node
				return child;
			}
			j++;
		}
		return null;
	}
	
	//BFS traversal of the grid is performed by the bfs() function
	public void bfs(){
		
		//BFS uses Queue data structure
		Queue q=new LinkedList();
                ArrayList<Node> queueList = new ArrayList();                    //For the purpose of demonstrating the results
                
                //add the source node to the queue
		q.add(this.rootNode); 
                queueList.add(rootNode);
                System.out.println("QUEUE:");
                System.out.print(rootNode.id); 
                printNode(rootNode); 
                rootNode.visited=true;                                          //rootNode is explored
                
                //This loop runs until the queue has elements
		while(!q.isEmpty()){
			Node n=(Node)q.remove();                                //dequeue element from the queue
			
                        Node child=null;
                        //This loop is to traverse through all the child nodes and discover them 
			while((child=getUnvisitedChildNode(n))!=null){
				child.visited=true;                             //child node is discovered
				q.add(child);                                   //add the child node to the queue
                                queueList.add(child);
                                
                                //print the current queue
                                System.out.println("");
                                for(int y=0;y<q.size();y++){
                                    Node b = (Node) ((LinkedList)(q)).get(y);
                                    System.out.print(b.id + " ");
                                }
                                printNode(child);
			}
		}
                
                //Print final results
                Node temp = null;
                //Traverse to the destination node
                for(int p= 0; p<nodes.size();p++){
                    if(((Node)(nodes.get(p))).getId() == 25){                   //destination node found
                        temp = ((Node)(nodes.get(p)));
                        break;
                    }
                }
                
                //This loop will print the nodes in the order of how they discovered
                System.out.println("\n\nORDER OF EXPLORED ELEMENTS:");
                for(int u=0;u<queueList.size();u++){
                    System.out.print(queueList.get(u).id+" ");
                }
                
                //This will print the path from source to destination                
                System.out.println("\n\nTRAVERSAL ORDER\n");
                ArrayList order = new ArrayList();                              
                
                order.add(temp.id);                                          //Add the destination node to the order-ArrayList
                //This loop backtracks from the destination up to the source to create the required path 
                while(temp.getPred()!=null){
                    order.add(temp.getPred().getId());                      //get the id of the predecessor
                    temp = temp.getPred();
                }
                
                //Print the resultatant path
                for(int h = order.size()-1 ; h>=0; h--){
                    System.out.print(order.get(h) + " -> ");
                }                
                System.out.print("Destination reached\n");
                
                //print the no of expored nodes
                System.out.println("\nNo of nodes expanded: " + queueList.size());
                //print the cost of the route
                System.out.println("Total Cost: " + (order.size()-1));
                
		//Clear visited property of nodes
		clearNodes();
	}
	
	//DFS traversal of the grid is performed by the dfs() function
	public void dfs()
	{
		//DFS uses Stack data structure
		Stack s=new Stack();
                
                //push the source node to the stack
		s.push(this.rootNode);
                
                //print the stack
                System.out.println("STACK:");                            
		rootNode.visited=true;                                      //node is discovered
		System.out.print("\n" +rootNode.id + "pushed\n");
                System.out.print(rootNode.id);    
                
                //This loop runs until the stack has elements
		while(!s.isEmpty()){
			Node n=(Node)s.peek();                              //get the topmost element of the stack
			
                        Node child=getUnvisitedChildNode(n);                //get the child node
			//if there are child nodes to discover
                        if(child!=null){
				child.visited=true;                         //child node is discovered
				s.push(child);                              //push the child node to the stack
                                
                                //print the resultant stack
                                System.out.print("\n" +child.id + "pushed");
                                System.out.println("");
                                for(int y=0;y<s.size();y++){
                                    Node b = (Node)(s.get(y));
                                    System.out.print(b.id + " ");
                                }                                
			}
			else{                                               //if there are no child nodes to discover
				s.pop();
			}
		}   
                
                //Print final results
                Node temp = null;
                //Traverse to the destination node
                for(int p= 0; p<nodes.size();p++){
                    if(((Node)(nodes.get(p))).getId() == 25){                //destination node found
                        temp = ((Node)(nodes.get(p)));
                        break;
                    }
                }
                
                //This will print the path from source to destination
                System.out.println("\n\nTRAVERSAL ORDER\n");
                ArrayList order = new ArrayList();
                
                order.add(temp.id);                                         //Add the destination node to the order-ArrayList
                //This loop backtracks from the destination up to the source to create the required path 
                while(temp.getPred()!=null){
                    order.add(temp.getPred().getId());                      //get the id of the predecessor
                    temp = temp.getPred();
                }
                
                //Print the resultatant path
                for(int h = order.size()-1 ; h>=0; h--){
                    System.out.print(order.get(h) + " -> ");
                }                
                System.out.print("Destination reached\n");
                
                //print the no of expored nodes
                System.out.println("\nNo of nodes expanded: " + order.size());
                //print the cost of the route
                System.out.println("Total Cost: " + (order.size()-1));
                
		//Clear visited property of nodes
		clearNodes();
	}
	
	
	//Method for clearing predecessors and visited property of nodes
	private void clearNodes(){
		int i=0;
		while(i<size){
			Node n=(Node)nodes.get(i);
			n.visited=false;            //assigned as an unvisited node
                        n.pred = null;              //no predecessor
			i++;
		}
	}
	
	//Method for printing the node name when removed from the queue
	private void printNode(Node n)
	{
		
	}

	
	
	

}
