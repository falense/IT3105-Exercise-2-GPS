package StateManagers;

import java.util.LinkedList;
import java.util.Random;

import States.AbstractState;

public abstract class LocalStateManager {
	AbstractState state;
	public LocalStateManager(AbstractState state) {
		this.state = state;
	}
	public int getConflictedVariable() {
		// TODO Auto-generated method stub
		LinkedList<Integer> vars = state.getVars();
		LinkedList<Integer> conflicted = new LinkedList<Integer>();
		for (Integer var: vars){
			if(state.isInvolvedInConflict(var)){
				conflicted.add(var);
			}
		}
		int rindex = new Random().nextInt(conflicted.size());		
		return conflicted.get(rindex);
	}
	public void optimizeConflictedVariable(int var) {
		int minConflicts = -1;
		LinkedList<Integer> minConfValues = new LinkedList<Integer>();
		for (Integer value: state.getPossibleValues()){
			int conflicts = state.testNumberOfConflicts(var,value);
			if (minConflicts == -1 || minConflicts >= conflicts){
				if (minConflicts > conflicts){
					minConfValues.clear();

					minConflicts = conflicts;
				}
				minConfValues.add(value);
			}
		}

		int rindex = new Random().nextInt(minConfValues.size());	
		
		state.setVariable(var, minConfValues.get(rindex));
		
	}
	public int getNumberOfConflicts(){
		int total = 0;
		for (Integer var: state.getVars()){
			total += state.getNumberOfConflicts(var);
		}
		return total;
	}
	public boolean isOptimal() {
		// TODO Auto-generated method stub
		if (getNumberOfConflicts() > 0)
			return false;
		else
			return true;
	}

}
