package StateManagers;

import States.AbstractState;
import States.GraphState;


public class GraphColorManager extends LocalStateManager {
	private String graphfile;
	public static final String className = GraphColorManager.class.getName();
	
	public GraphColorManager(String graphfile) {
		super(new GraphState(graphfile));
		this.graphfile = graphfile;
	}
	
	public String getName(){
		return className + " " + graphfile;
	}

	@Override
	public LocalStateManager copy() {
		return new GraphColorManager(graphfile);
	}

}
