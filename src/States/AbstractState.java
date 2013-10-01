package States;

import java.util.LinkedList;

public abstract class AbstractState {
	public abstract LinkedList<Integer> getVars();
	public abstract int getNumberOfConflicts(int var);
	public abstract LinkedList<Integer> getPossibleValues();
	public abstract void setVariable(int value);
	
}
