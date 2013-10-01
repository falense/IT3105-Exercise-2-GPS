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
	public int getNumberOfConflicts(){
		int conflicts = 0;
		for (Node n: neighbours){
			if (n.color == color){
				conflicts++;
			}
		}
		return conflicts;
	}
	public void setColor(int color){
		this.color = color;
	}
	public int getColor() {
		return this.color;
	}
}

public class Graph extends AbstractState{
	private LinkedList<Node> nodes = new LinkedList<Node>();
	private int K;
	public Graph(int K) {
		// TODO Auto-generated constructor stub
		this.K = K;
	}
	
	
	@Override
	public LinkedList<Integer> getVars() {
		LinkedList<Integer> vars = new LinkedList<Integer>();
		for (int i = 0; i < nodes.size(); i++)
			vars.add(i);
		return vars;
	}

	@Override
	public int getNumberOfConflicts(int var) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Integer> getPossibleValues() {
		LinkedList<Integer> values = new LinkedList<Integer>();
		for (int i = 0; i < K; i++)
			values.add(i);
		return values;
	}

	@Override
	public void setVariable(int var, int value) {
		// TODO Auto-generated method stub
		if (var > 0 && var < nodes.size()){
			Node n = nodes.get(var);
			n.setColor(value);
		}
	}

	@Override
	public int testNumberOfConflicts(int var, Integer value) {
		// TODO Auto-generated method stub
		if (var > 0 && var < nodes.size()){
			Node n = nodes.get(var);
			int oldcolor =  n.getColor();
			n.setColor(var);
			int conflicts = n.getNumberOfConflicts();
			n.setColor(oldcolor);
			return conflicts;
		}
		return -1;
	}
	
}
