package StateManagers;

import java.util.LinkedList;
import java.util.Random;

import States.AbstractState;
import States.ChessBoard;

public abstract class LocalStateManager {
	AbstractState state;

	public abstract String getName();
	
	public AbstractState generateNeighbourState(){
		AbstractState newState = state.copy();
		
		LinkedList<Integer> variables = newState.getVars();
		LinkedList<Integer> values = newState.getPossibleValues();
	
		int y = variables.get(new Random().nextInt(variables.size()));
		int x = values.get(new Random().nextInt(values.size()));	
		newState.setValue(y, x);
		return newState;
	}
	
	public LocalStateManager(AbstractState state) {
		this.state = state;
	}
	public int getConflictedVariable() {
		LinkedList<Integer> conflicted = new LinkedList<Integer>();
		for (Integer var: state.getVars()){
			if(state.isInvolvedInConflict(var)){
				conflicted.add(var);
			}
		}
		int rindex = new Random().nextInt(conflicted.size());		
		return conflicted.get(rindex);
	}
	public void optimizeConflictedVariable(int var) {
		int minConflicts = Integer.MAX_VALUE;
		LinkedList<Integer> minConfValues = new LinkedList<Integer>();
		for (Integer value: state.getPossibleValues()){
			int conflicts = state.testNumberOfConflicts(var,value);
			if (minConflicts >= conflicts){
				if (minConflicts > conflicts){
					minConfValues = new LinkedList<Integer>(); 
					minConflicts = conflicts;
				}
				minConfValues.add(value);
			}
		}
		//System.out.println("Variable " + var + " has minConflicts "  + minConflicts + "(" + minConfValues.size() + ") Total number of conflicts " + state.getNumberOfConflicts());
		int rindex = new Random().nextInt(minConfValues.size());	
		
		state.setValue(var, minConfValues.get(rindex));
	}
	
	
	
	
	public abstract double evaluate(AbstractState state);

	

	public void setState(AbstractState bestState) {
		this.state = bestState;
		
	}

	public boolean done(){
		return state.isOptimal();
	}
	public AbstractState getState(){
		return state;
	}
	public abstract LocalStateManager copy();
}
