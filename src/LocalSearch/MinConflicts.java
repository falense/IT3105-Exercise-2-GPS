package LocalSearch;

import StateManagers.LocalStateManager;

public class MinConflicts extends ConstraintBasedLocalSearch {
	public static final String className = MinConflicts.class.getName();
	public MinConflicts() {
		super();
	}
	
	private int steps = 0;
	@Override
	public void solve() {
			steps = 0;
			while (!sm.done() && steps < 10000){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				steps++;
				System.out.println("Step number: " + steps);
			}
			System.out.println("Completed in " + steps + " steps");
	}
	
	public int getStepsToSolve(){
		return steps;
	}
	public int getSolutionNumConflicts(){
		return sm.getState().getNumberOfConflicts();
	}

	@Override
	public void clear() {
		steps = 0;
	}
}
