package States;

import java.util.LinkedList;

public class ChessBoard extends AbstractState  {

	@Override
	public LinkedList<Integer> getVars() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfConflicts(int var) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Integer> getPossibleValues() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVariable(int var, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int testNumberOfConflicts(int var, Integer value) {
		// TODO Auto-generated method stub
		return 0;
	}

}
