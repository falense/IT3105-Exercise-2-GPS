package LocalSearch;


public class MinConflicts extends ConstraintBasedLocalSearch {
	public static final String className = MinConflicts.class.getName();
	public MinConflicts() {
		super();
	}
	
	private int steps = 0;
	@Override
	public void solve() {
			steps = 0;
			while (!sm.done()){
				int var = sm.getConflictedVariable();
				sm.optimizeConflictedVariable(var);
				steps++;
				/*try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
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
