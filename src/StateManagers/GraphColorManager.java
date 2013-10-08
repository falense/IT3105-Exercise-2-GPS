package StateManagers;

import States.AbstractState;
import States.Graph;

public class GraphColorManager extends LocalStateManager {

	public GraphColorManager() {
		super(new Graph("graph-color-3.txt"));
	}

}
