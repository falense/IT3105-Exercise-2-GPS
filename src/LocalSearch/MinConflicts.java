package LocalSearch;


public class MinConflicts extends ConstraintBasedLocalSearch {
	public static final String className = MinConflicts.class.getName();
	private int steps = 0;
	@Override
	public String getName(){
		return className;
	}
	
	public MinConflicts(boolean debug) {
		super(debug);
	}
	
	@Override
	public void solve() {
			steps = 0;
			while (!sm.done() && steps < 10000){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				steps++;
				print("Step number: " + steps + " Number of conflicts: " + sm.getState().getNumberOfConflicts());
			}
			print("Completed in " + steps + " steps");
			if (debug) sm.getState().display();
	}
	
	@Override
	public int getStepsToSolve(){
		return steps;
	}

	public void clear() {
		steps = 0;
	}
}
