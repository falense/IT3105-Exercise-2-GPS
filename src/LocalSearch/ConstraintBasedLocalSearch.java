package LocalSearch;

import StateManagers.LocalStateManager;

public abstract class ConstraintBasedLocalSearch {
	protected LocalStateManager sm;
	public ConstraintBasedLocalSearch(LocalStateManager sm) {
		// TODO Auto-generated constructor stub
		this.sm = sm;
	}
	abstract void solve();
}
