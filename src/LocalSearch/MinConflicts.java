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
			int c = 0;
			while (!sm.done()){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				c++;
			}
			System.out.println("Completed in " + c + " steps");
	}

}
