package States;

import java.util.LinkedList;

class Node{
	private LinkedList<Node> neighbours = new LinkedList<Node>();
	private int color;
	public boolean hasConflicts(){
		for (Node n: neighbours){
			if (n.color == color){
				return true;
			}
		}
		return false;
	}
}

public class Graph extends AbstractState{
	
}
