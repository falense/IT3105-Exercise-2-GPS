package LocalSearch;

import StateManagers.LocalStateManager;

public abstract class ConstraintBasedLocalSearch {
	protected LocalStateManager sm;
	public ConstraintBasedLocalSearch(LocalStateManager sm) {
		// TODO Auto-generated constructor stub
		this.sm = sm;
	}
	public abstract void solve();
	public abstract int getStepsToSolve();
	public abstract int getSolutionNumConflicts();
}
