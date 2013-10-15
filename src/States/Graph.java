package States;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

class Node{
	private LinkedList<Node> neighbours = new LinkedList<Node>();
	private int color;
	private double x,y;
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
	private void setPosition(double x, double y){

		this.x = x;
		this.y = y;
	}
	public void addNeighbour(final Node n){
		neighbours.add(n);
	}
	public Node(double x, double y, int color) {
		setPosition( x,y);
		setColor(color);
	}
}

public class Graph extends AbstractState{
	private Node nodes[] = null;
	private int K = 4;
	private final String className = Graph.class.getName();
	
	private void loadGraph(String filename){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
		}
		String line = null;
		Node nodeList[] = null;
		try {
			int row = 0;
			int nodeCount = 0;
			int edgeCount = 0;
			
			while ((line = reader.readLine()) != null) {
				String substrings[] = line.split(" ");
				if (row == 0){
					nodeCount = Integer.parseInt(substrings[0]);
					edgeCount = Integer.parseInt(substrings[1]);
					nodeList = new Node[nodeCount];
					//System.out.println(className + ": nodeCount " + nodeCount + " edgeCount " + edgeCount);
				}
				else if (row > 0 && row <= nodeCount){
					int nodeIndex = Integer.parseInt(substrings[0]);
					double xcoord = Double.parseDouble(substrings[1]);
					double ycoord = Double.parseDouble(substrings[2]);
					nodeList[nodeIndex] = new Node(xcoord,ycoord,new Random().nextInt(K));
				}
				else{
					int nodeIndex1 = Integer.parseInt(substrings[0]);
					int nodeIndex2 = Integer.parseInt(substrings[1]);
					Node n1 = nodeList[nodeIndex1];
					Node n2 = nodeList[nodeIndex2];
					n1.addNeighbour(n2);
					n2.addNeighbour(n1);
				}
				
				row++;
			}
			nodes = nodeList;
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
		for (int i = 0; i < nodes.length; i++)
			vars.add(i);
		return vars;
	}

	@Override
	public int getNumberOfConflicts(int var) {
		if (var >= 0 && var < nodes.length)
			return nodes[var].getNumberOfConflicts();
		else{
			throw new IndexOutOfBoundsException(className + "GetValue index out of bounds");
		}
	}

	@Override
	public LinkedList<Integer> getPossibleValues() {
		LinkedList<Integer> values = new LinkedList<Integer>();
		for (int i = 0; i < K; i++)
			values.add(i);
		return values;
	}

	@Override
	public int getValue(int var) {
		if (var >= 0 && var < nodes.length)
			return 0;
		else{
			throw new IndexOutOfBoundsException(className + "GetValue index out of bounds");
		}
	}
	@Override
	public void setValue(int var, int value) {
		if (var >= 0 && var < nodes.length)
			nodes[var].setColor(value);
		else{
			throw new IndexOutOfBoundsException(className + "SetValue index out of bounds");
		}
	}
	@Override
	public AbstractState copy() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void display() {
		// TODO Auto-generated method stub
		
	}


	
}
