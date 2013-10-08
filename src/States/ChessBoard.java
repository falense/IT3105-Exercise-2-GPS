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
	public int testNumberOfConflicts(int var, int value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getValue(int var) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(int var, int value) {
		// TODO Auto-generated method stub
	}

}
