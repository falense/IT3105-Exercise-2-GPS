import LocalSearch.ConstraintBasedLocalSearch;
import LocalSearch.MinConflicts;
import StateManagers.GraphColorManager;
import StateManagers.LocalStateManager;
class Problem{
	
}
public class StatisticsRunner {
	public static final int maxRuns = 200;
	
	private LocalStateManager[] getProblems(){
		LocalStateManager [] problems = new LocalStateManager[3];
		problems[0] = new GraphColorManager("graph-color-1.txt");
		problems[1] = new GraphColorManager("graph-color-2.txt");
		problems[2] = new GraphColorManager("graph-color-3.txt");
		return problems;
	}
	private void testSolver(LocalStateManager sm){
		int totalStepCount = 0;
		int totalConflictsInSolutions = 0;
		double []stepsToSolve = new double[maxRuns];
		double []solutionConflicts = new double[maxRuns];
		double minStepCount = Integer.MAX_VALUE;
		double minSolutionConflicts = Integer.MAX_VALUE;
		LocalStateManager g = null;
		ConstraintBasedLocalSearch mc;
		for (int i = 0; i < maxRuns; i++){
			g =  sm.copy();
			mc = new MinConflicts(g);
			mc.solve();
			solutionConflicts[i] = mc.getSolutionNumConflicts();
			stepsToSolve[i] = mc.getStepsToSolve();
			totalStepCount += stepsToSolve[i];
			totalConflictsInSolutions += solutionConflicts[i];
			if (solutionConflicts[i] < minSolutionConflicts){
				minSolutionConflicts = solutionConflicts[i];
				
			}
			if (stepsToSolve[i] < minStepCount){
				minStepCount = stepsToSolve[i];
			}
		}
		double stepCountStddev = 0;
		double conflictsInSolutionsStddev = 0;
		double stepCountAvg = totalStepCount/maxRuns;
		double conflictsInSolutionsAvg = totalConflictsInSolutions/maxRuns;
		for (int i = 0; i < maxRuns; i++){
			stepCountStddev += Math.pow((stepsToSolve[i]-stepCountAvg),2);
			conflictsInSolutionsStddev +=  Math.pow((solutionConflicts[i]-conflictsInSolutionsAvg),2);
		}
		stepCountStddev = Math.round(Math.sqrt(stepCountStddev/maxRuns));
		conflictsInSolutionsStddev = Math.round(Math.sqrt(conflictsInSolutionsStddev/maxRuns));
		
		System.out.println("\n\nAlgorithm: " + MinConflicts.className);
		System.out.println("Problem: " + g.getName());
		
		System.out.println("Number of runs: " + maxRuns);

		System.out.println("\n\t AvgStepCount " + stepCountAvg); 
		System.out.println("\t StddevStepCount " + stepCountStddev); 
		System.out.println("\t MinStepCount " + minStepCount); 
		System.out.println("\n\t AvgConflictsInSolution " + conflictsInSolutionsAvg); 
		System.out.println("\t StddevConflictsInSolution " + conflictsInSolutionsStddev); 
		System.out.println("\t MinConflictsInSolution " + minSolutionConflicts ); 
		
	}
	public void testMinConflicts(){
		for (LocalStateManager sm: getProblems()){
			testSolver(sm);
		}
	}
	public static void main(String[] args) {
		StatisticsRunner s = new StatisticsRunner();
		s.testMinConflicts();
	}

}
