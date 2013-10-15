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
		man.getState().display();
	}
	
	public void RunSAKQTest(){
		KQueensManager qm = new KQueensManager(16);
		SimulatedAnnealing sa = new SimulatedAnnealing(20,100,0.02,0);
		sa.setStateManager(qm);
		sa.solve();
	}
	
	public void RunMCKQTest(){
		KQueensManager qm = new KQueensManager(8);
		MinConflicts kmc = new MinConflicts();
		kmc.setStateManager(qm);
		kmc.solve();
	}
	public void RunSAGCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		SimulatedAnnealing sa = new SimulatedAnnealing(20,100,0.02,0);
		sa.setStateManager(man);
		sa.solve();
	}
	
	public static void main(String[] args) {
		UserInterface s = new UserInterface();
		//s.RunMCTest();
		s.RunSAGCTest();
		
		
		
		System.out.println("Test");
		
	}
}
