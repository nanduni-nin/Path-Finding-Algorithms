package searchpaths;

/**
 *
 * @author Nands
 */

/*Creates a class for a node in the grid*/
public class Node{
	public int id;                      //name of the node
	public boolean visited=false;       //node is discovered or not
        public Node pred;                   //predecesssor node
            
        //Constructor
	public Node(int id){                
		this.id=id;
	}
        
        //set the predecessor node
        public void setPred(Node n){
            this.pred = n;
        }
        
        //get the name of the node
        public int getId(){
            return id;
        }
        
        //get the predecessor of the node
        public Node getPred(){
            return pred;
        }
}
