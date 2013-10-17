package LocalSearch;

import StateManagers.LocalStateManager;

public abstract class ConstraintBasedLocalSearch {
	protected LocalStateManager sm;
	private boolean debug;
	public ConstraintBasedLocalSearch(boolean debug) {
		this.debug = debug;
	}
	public void setStateManager(LocalStateManager sm){
		this.sm = sm;
		clear();
	}
	public void print(String out){
		if (debug) System.out.println(out);
	}
	public abstract void clear();
	public abstract void solve();
	public abstract int getStepsToSolve();
	public abstract int getSolutionNumConflicts();
	public abstract String getName();
}
