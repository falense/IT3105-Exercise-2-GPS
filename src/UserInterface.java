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
	}
	
	public void RunSAKQTest(){
		KQueensManager qm = new KQueensManager(16);
		SimulatedAnnealing sa = new SimulatedAnnealing(20,100,0.02,0);
		sa.setStateManager(qm);
		sa.solve();
	}
	
	
	public static void main(String[] args) {
		UserInterface s = new UserInterface();
		//s.RunMCTest();
		s.RunSAKQTest();
		
		
		
		System.out.println("Test");
		
	}
}
