package LocalSearch;

import StateManagers.LocalStateManager;

public abstract class ConstraintBasedLocalSearch {
	protected LocalStateManager sm;
	public ConstraintBasedLocalSearch() {
	}
	public void setStateManager(LocalStateManager sm){
		this.sm = sm;
		clear();
	}
	public abstract void clear();
	public abstract void solve();
	public abstract int getStepsToSolve();
	public abstract int getSolutionNumConflicts();
}
