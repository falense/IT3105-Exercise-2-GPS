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
			


			System.out.println("Do you want to play again?");
			System.out.println("1: Yes");
			System.out.println("2: No");
			
			try {	
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				temp = "2";
			}
			if (Integer.parseInt(temp)!=1){
				System.out.println("Exiting GPS");
				System.exit(0);				
			}
			
		
		}
	
	}
	private static ConstraintBasedLocalSearch findMethod(String temp) {
		if(Integer.parseInt(temp)==1)
			return new SimulatedAnnealing(20,100,0,false, 10000, true);
		else
			return new MinConflicts(false);
	}
	private static LocalStateManager findManager(String in) {
		int temp = Integer.parseInt(in);
		System.out.println("Difficulty of puzze: 1: Easy, 2: Medium or 3: Hard.");
		System.out.println("Enter difficulty (1, 2 or 3):");
		String sIn;
		try {	
			sIn = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			sIn = "1";
		}
		int number = Integer.parseInt(sIn);
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
			
			
		default:
			return new KQueensManager(8);
		}
	}
	
	private static void printProblemsMenu() {
		System.out.println("Select Puzzle:");
		System.out.println("1: Graphcoloring");
		System.out.println("2: K-Queens");
		System.out.println("3: Sudoku");
		System.out.println("4: Equation");
		System.out.println("Enter choice:");
		
	}
	
	private static void printSearchMethod(){
		System.out.println("Select Local Search Method:");
		System.out.println("1: Simulated Annealing");
		System.out.println("2: Minimal Conflicts");
		System.out.println("Enter choice:");
	}
}
