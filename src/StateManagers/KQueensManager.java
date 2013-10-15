package StateManagers;

import States.AbstractState;
import States.ChessBoard;

public class KQueensManager extends LocalStateManager {
	public static final String className = KQueensManager.class.getName();
	private int size;
	
	public KQueensManager(int size) {
		super(new ChessBoard(size));
		this.size = size;
	}
	
	//max conflics - conflicts, more or less.
	public double evaluate(AbstractState state){
		int size = state.getVars().size(); 	
		return (size*size)-state.getNumberOfConflicts();
	}
	
	@Override
	public String getName() {
		return className + " K = " + size;
	}

	@Override
	public LocalStateManager copy() {
		return new KQueensManager(size);
	}

}
