package StateManagers;

import java.util.LinkedList;
import java.util.Random;

import States.AbstractState;
import States.ChessBoard;

public class KQueensManager extends LocalStateManager {
	private String className = KQueensManager.class.getName();
	
	

	public KQueensManager(AbstractState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}
	
	
	
	public double evaluate(AbstractState state){ //Should become getNumberOfConflicts
		/*
		double score = 0;
		
		LinkedList<Integer> variables = state.getVars();
		LinkedList<Integer> values = state.getPossibleValues();
		
		for (int i : variables){
			int x = state.getValue(i);
			
		}
		*/
		
		
		return state.getNumberOfConflicts();
		
	}
	


	@Override
	public String getName() {
		return className;
	}

	@Override
	public LocalStateManager copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
