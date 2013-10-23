import LocalSearch.ConstraintBasedLocalSearch;
import LocalSearch.MinConflicts;
import LocalSearch.SimulatedAnnealing;
import StateManagers.EquationManager;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;
import StateManagers.LocalStateManager;
import StateManagers.SudokuManager;
class Problem{
	
}
public class StatisticsRunner {
	public static int maxRuns;
	LocalStateManager sm;
	ConstraintBasedLocalSearch solver;
	
	public StatisticsRunner(int maxRuns,LocalStateManager sm, ConstraintBasedLocalSearch solver){
		this.maxRuns = maxRuns;
		this.sm = sm;
		this.solver = solver;
	}
	
	public StatisticsRunner(){
		this.maxRuns = 20;
		//return StatisticsRunner(20,null,null);
	}
	
	
	
	private LocalStateManager[] getProblems(){
		LocalStateManager [] someProblems = new LocalStateManager[1];
		someProblems[0] = new SudokuManager("sudoku1.txt");
		/*	
		LocalStateManager [] problems = new LocalStateManager[12];
		problems[0] = new SudokuManager("sudoku1.txt"); 
		problems[1] = new SudokuManager("sudoku2.txt");
		problems[2] = new SudokuManager("sudoku3.txt"); 
		problems[3] = new EquationManager(5); 
		problems[4] = new EquationManager(10);
		problems[5] = new EquationManager(20); 
		problems[6] = new KQueensManager(8);
		problems[7] = new KQueensManager(25);
		problems[8] = new KQueensManager(100);
		problems[9] = new GraphColorManager("graph-color-1.txt");
		problems[10] = new GraphColorManager("graph-color-2.txt");
		problems[11] = new GraphColorManager("graph-color-3.txt");
		*/
		return someProblems;
	}
	private ConstraintBasedLocalSearch[] getSolvers(){
		ConstraintBasedLocalSearch [] solvers = new ConstraintBasedLocalSearch[2];
		solvers[0] = new MinConflicts(false);
		solvers[1] = new SimulatedAnnealing(20,10,0,false, 10000, true);
		return solvers;
	}
	public void testSolver(ConstraintBasedLocalSearch solver, LocalStateManager sm, int runs){
		int totalStepCount = 0;
		int totalConflictsInSolutions = 0;
		double []stepsToSolve = new double[runs];
		double []solutionConflicts = new double[runs];
		double minStepCount = Integer.MAX_VALUE;
		double minSolutionConflicts = Integer.MAX_VALUE;
		LocalStateManager g = null;
		for (int i = 0; i < runs; i++){
			g =  sm.copy();
			solver.setStateManager(g);
			solver.solve();
			solutionConflicts[i] = solver.getSolutionNumConflicts();
			stepsToSolve[i] = solver.getStepsToSolve();
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
		double stepCountAvg = totalStepCount/runs;
		double conflictsInSolutionsAvg = totalConflictsInSolutions/runs;
		for (int i = 0; i < runs; i++){
			stepCountStddev += Math.pow((stepsToSolve[i]-stepCountAvg),2);
			conflictsInSolutionsStddev +=  Math.pow((solutionConflicts[i]-conflictsInSolutionsAvg),2);
		}
		stepCountStddev = Math.round(Math.sqrt(stepCountStddev/runs));
		conflictsInSolutionsStddev = Math.round(Math.sqrt(conflictsInSolutionsStddev/runs));
		
		System.out.println("\n\nAlgorithm: " + solver.getName());
		System.out.println("Problem: " + g.getName());
		
		System.out.println("Number of runs: " + runs);

		System.out.println("\n\t AvgStepCount " + stepCountAvg); 
		System.out.println("\t StddevStepCount " + stepCountStddev); 
		System.out.println("\t MinStepCount " + minStepCount); 
		System.out.println("\n\t AvgConflictsInSolution " + conflictsInSolutionsAvg); 
		System.out.println("\t StddevConflictsInSolution " + conflictsInSolutionsStddev); 
		System.out.println("\t MinConflictsInSolution " + minSolutionConflicts ); 
		
	}
	
	public void testSystem(){
		for (ConstraintBasedLocalSearch solver: getSolvers()){
			for (LocalStateManager sm: getProblems()){
				testSolver(solver,sm,20);
			}
		}
	}
	public static void main(String[] args) {
		StatisticsRunner s = new StatisticsRunner();
		s.testSystem();
	}

}
