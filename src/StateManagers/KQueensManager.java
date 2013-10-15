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
	
	
	
	public double evaluate(AbstractState state){
		int size = state.getVars().size(); 	
		return (size*size)-state.getNumberOfConflicts();
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
