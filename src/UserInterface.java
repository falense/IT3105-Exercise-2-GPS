import LocalSearch.MinConflicts;
import LocalSearch.SimulatedAnnealing;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;


public class UserInterface {
	
	public void RunMCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		MinConflicts mc = new MinConflicts();
		mc.setStateManager(man);
		mc.solve();
		KQueensManager qm = new KQueensManager(8);
		SimulatedAnnealing sa = new SimulatedAnnealing(4,100,2,64);
	}
	public static void main(String[] args) {
		//UserInterface s = new UserInterface();
		//s.RunMCTest();
		System.out.println("Test");
		
	}
}
