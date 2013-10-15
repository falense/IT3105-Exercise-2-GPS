package StateManagers;

import States.AbstractState;
import States.ChessBoard;

public class KQueensManager extends LocalStateManager {
	private String className = KQueensManager.class.getName();
	int size;
	
	

	public KQueensManager(int size) {
		super(new ChessBoard(size));
		this.size = size;
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	//max conflics - conflicts, more or less.
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
		return new KQueensManager(size);
	}

}
