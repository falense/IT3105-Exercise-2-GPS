package LocalSearch;

import StateManagers.LocalStateManager;

public class MinConflicts extends ConstraintBasedLocalSearch {

	public MinConflicts(LocalStateManager sm) {
		super(sm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public
	void solve() {
			while (!sm.isOptimal()){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				
			}
	}

}
