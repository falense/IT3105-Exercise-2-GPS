package States;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
	
	private void loadGraph(String filename){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("File not found: " + filename);
		}
		String line = null;
		try {
			int row = 0;
			int nodeCount = 0;
			int edgeCount = 0;
			
			while ((line = reader.readLine()) != null) {
				String substrings[] = line.split(" ");
				if (row == 0){
					nodeCount = Integer.parseInt(substrings[0]);
					edgeCount = Integer.parseInt(substrings[1]);
				}
				else if (row > 0 && row <= nodeCount){
					
				}
				else{
					
				}
				
				row++;
			}
		} catch (IOException e) {
			System.err.println("Reading from graph file failed");
		}
	}
	public Graph(String filename) {
		loadGraph(filename);
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
