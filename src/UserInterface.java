import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import LocalSearch.ConstraintBasedLocalSearch;
import LocalSearch.MinConflicts;
import LocalSearch.SimulatedAnnealing;
import StateManagers.EquationManager;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;
import StateManagers.LocalStateManager;
import StateManagers.SudokuManager;


public class UserInterface {
	private static BufferedReader br;
	private static boolean debug = false;

	public static void main(String[] args) {
		
		while(true){
			
			String temp;
			br = new BufferedReader(new InputStreamReader(System.in));
		
			printProblemsMenu();
			try {	
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				temp = "2";
			}
			
			LocalStateManager currentManager = findManager(temp);
			
			printSearchMethod();
			
			try {	
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				temp = "1";
			}
			
			ConstraintBasedLocalSearch currentSearch = findMethod(temp);
			
			currentSearch.setStateManager(currentManager);
			currentSearch.solve();
			currentManager.getState().display();

		}
	
	}
	private static ConstraintBasedLocalSearch findMethod(String temp) {
		if(Integer.parseInt(temp)==1)
			return new SimulatedAnnealing(20,100,0,debug, 10000, true);
		else
			return new MinConflicts(debug);
	}
	private static LocalStateManager findManager(String in) {
		if (in=="")
			in="0";
		int temp = Integer.parseInt(in);
		int number = 0;
		String sIn = "0";
		
		if(temp!=5&&temp!=6){
			System.out.println("Difficulty of puzze: 1: Easy, 2: Medium or 3: Hard.");
			System.out.println("Enter difficulty (1, 2 or 3):");
			
			try {	
				sIn = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				sIn = "1";
			}
			
			number = Integer.parseInt(sIn);
		}
		switch (temp){
		case 1:
			return new GraphColorManager("graph-color-"+sIn+".txt");
		case 2:
			switch(number){
			case 2:
				return new KQueensManager(25);
			case 3:
				return new KQueensManager(1000);
			default: 
				return new KQueensManager(8);
			}
		case 3:
			return new SudokuManager("sudoku"+sIn+".txt");
		case 4:
			
			switch(number){
			case 2:
				return new EquationManager(10);
			case 3:
				return new EquationManager(100);
			default: 
				return new EquationManager(4);
			}
		case 5:
			debug = !debug;
			return new KQueensManager(8);
		case 6:
			System.out.println("Exiting GPS");
			System.exit(0);
			
		default:
			System.out.println("No valid choices, using default: K-Queens(8).");
			return new KQueensManager(8);
		}
	}
	
	private static void printProblemsMenu() {
		System.out.println("Select Puzzle:");
		System.out.println("1: Graphcoloring");
		System.out.println("2: K-Queens");
		System.out.println("3: Sudoku");
		System.out.println("4: Equation");
		System.out.println("5: Toggle debug-mode on/off");
		System.out.println("6: Exit program");
		System.out.println("Enter choice:");
		
	}
	
	private static void printSearchMethod(){
		System.out.println("Select Local Search Method:");
		System.out.println("1: Simulated Annealing");
		System.out.println("2: Minimal Conflicts");
		System.out.println("Enter choice:");
	}
}
