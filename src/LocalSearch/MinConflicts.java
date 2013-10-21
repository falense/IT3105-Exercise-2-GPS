package LocalSearch;


public class MinConflicts extends ConstraintBasedLocalSearch {
	public static final String className = MinConflicts.class.getName();
	@Override
	public String getName(){
		return className;
	}
	
	public MinConflicts(boolean debug) {
		super(debug);
	}
	
	private int steps = 0;
	@Override
	public void solve() {
			steps = 0;
			while (!sm.done() && steps < 10000){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				steps++;
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				print("Step number: " + steps + " Number of conflicts: " + sm.getState().getNumberOfConflicts());
			}
			print("Completed in " + steps + " steps");
	}
	
	@Override
	public int getStepsToSolve(){
		return steps;
	}
	@Override
	public int getSolutionNumConflicts(){
		return sm.getState().getNumberOfConflicts();
	}

	@Override
	public void clear() {
		steps = 0;
	}
}
