import LocalSearch.MinConflicts;
import LocalSearch.SimulatedAnnealing;
import StateManagers.EquationManager;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;


public class UserInterface {
	public void RunSAKQTest(){
		KQueensManager qm = new KQueensManager(8);
		SimulatedAnnealing sa = new SimulatedAnnealing(20,50,0,false, 10000, true);
		sa.setStateManager(qm);
		sa.solve();
		qm.getState().display();
	}
	public void RunSAGCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		SimulatedAnnealing sa = new SimulatedAnnealing(20,10,0.02,0,true, 10000, true);
		sa.setStateManager(man);
		sa.solve();
		man.getState().display();
	}
	
	public void RunSAEQTest(){
		EquationManager man = new EquationManager(10);
		SimulatedAnnealing sa = new SimulatedAnnealing(40,100,0,true, 10000, true);
		sa.setStateManager(man);
		sa.solve();
		man.getState().display();
	}
	
	public void RunMCKQTest(){
		KQueensManager qm = new KQueensManager(1000);
		MinConflicts kmc = new MinConflicts(true);
		kmc.setStateManager(qm);
		kmc.solve();
		//qm.getState().display();
	}	
	public void RunMCGCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		MinConflicts mc = new MinConflicts(true);
		mc.setStateManager(man);
		mc.solve();
		//man.getState().display();
	}
	public void RunMCEQTest(){
		EquationManager man = new EquationManager(200);
		MinConflicts mc = new MinConflicts(true);
		mc.setStateManager(man);
		mc.solve();
		man.getState().display();
	}
	
	
	
	
	public static void main(String[] args) {
		UserInterface s = new UserInterface();
		//s.RunMCGCTest();
		s.RunMCKQTest();
		//s.RunMCEQTest();
		//s.RunSAKQTest();
		//s.RunSAEQTest();
		
		
		
	}
}
