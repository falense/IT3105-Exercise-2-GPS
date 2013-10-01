package StateManagers;

import java.util.LinkedList;

import States.AbstractState;

public abstract class LocalStateManager {
	AbstractState state;
	public LocalStateManager(AbstractState state) {
		this.state = state;
	}
	public int getConflictedVariable() {
		// TODO Auto-generated method stub
		LinkedList<Integer> vars = state.getVars();
		return 0;
	}
	boolean isInvolvedInConflict(int var){
		if (state.getNumberOfConflicts(var) > 0){
			return true;
		}
		else{
			return false;
		}
	}

	public void optimizeConflictedVariable(int var) {
		// TODO Auto-generated method stub
		
	}

	public boolean isOptimal() {
		// TODO Auto-generated method stub
		return false;
	}

}
