import LocalSearch.ConstraintBasedLocalSearch;
import LocalSearch.MinConflicts;
import LocalSearch.SimulatedAnnealing;
import StateManagers.EquationManager;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;
import StateManagers.LocalStateManager;
import StateManagers.SudokuManager;
public class StatisticsRunner {
	public static final int maxRuns = 20;
	
	private LocalStateManager[] getProblems(){
		LocalStateManager [] problems = new LocalStateManager[12];
		problems[0] = new KQueensManager(8);
		problems[1] = new KQueensManager(25);
		problems[2] = new KQueensManager(1000);
		problems[3] = new GraphColorManager("graph-color-1.txt");
		problems[4] = new GraphColorManager("graph-color-2.txt");
		problems[5] = new GraphColorManager("graph-color-3.txt");
		problems[6] = new SudokuManager("sudoku1.txt"); 
		problems[7] = new SudokuManager("sudoku2.txt");
		problems[8] = new SudokuManager("sudoku3.txt"); 
		problems[9] = new EquationManager(10); 
		problems[10] = new EquationManager(50);
		problems[11] = new EquationManager(100); 
		return problems;
	}
	private ConstraintBasedLocalSearch[] getSolvers(){
		ConstraintBasedLocalSearch [] solvers = new ConstraintBasedLocalSearch[2];
		//SimulatedAnnealing(int numberNeighbours,double MaxTemprature, double DeltaTemprature, double targetScore,boolean debug, int maxRuns, boolean linear)
		solvers[0] = new SimulatedAnnealing(20,20,0,false, 10000, true);
		solvers[1] = new MinConflicts(false);
		return solvers;
	}
	private void testSolver(ConstraintBasedLocalSearch solver, LocalStateManager sm){
		int totalStepCount = 0;
		int totalConflictsInSolutions = 0;
		double []stepsToSolve = new double[maxRuns];
		double []solutionConflicts = new double[maxRuns];
		double minStepCount = Integer.MAX_VALUE;
		double minSolutionConflicts = Integer.MAX_VALUE;
		LocalStateManager g = null;
		for (int i = 0; i < maxRuns; i++){
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
		double stepCountAvg = totalStepCount/maxRuns;
		double conflictsInSolutionsAvg = totalConflictsInSolutions/maxRuns;
		for (int i = 0; i < maxRuns; i++){
			stepCountStddev += Math.pow((stepsToSolve[i]-stepCountAvg),2);
			conflictsInSolutionsStddev +=  Math.pow((solutionConflicts[i]-conflictsInSolutionsAvg),2);
		}
		stepCountStddev = Math.round(Math.sqrt(stepCountStddev/maxRuns));
		conflictsInSolutionsStddev = Math.round(Math.sqrt(conflictsInSolutionsStddev/maxRuns));
		
		System.out.println("\n\nAlgorithm: " + solver.getName());
		System.out.println("Problem: " + g.getName());
		
		System.out.println("Number of runs: " + maxRuns);

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
				testSolver(solver,sm);
			}
		}
	}
	public static void main(String[] args) {
		StatisticsRunner s = new StatisticsRunner();
		s.testSystem();
	}

}
