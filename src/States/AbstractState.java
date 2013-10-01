package States;

import java.util.LinkedList;

public abstract class AbstractState {
	public abstract LinkedList<Integer> getVars();
	public abstract int getNumberOfConflicts(int var);
	public abstract LinkedList<Integer> getPossibleValues();
	public abstract void setVariable(int var,int value);

	public boolean isInvolvedInConflict(int var){
		if (getNumberOfConflicts(var) > 0){
			return true;
		}
		else{
			return false;
		}
	}
	public abstract int testNumberOfConflicts(int var, Integer value);
}
