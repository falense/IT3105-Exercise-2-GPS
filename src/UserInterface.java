import LocalSearch.MinConflicts;
import StateManagers.GraphColorManager;


public class UserInterface {
	
	public void RunMCTest(){
		GraphColorManager man = new GraphColorManager();
		MinConflicts mc = new MinConflicts(man);
		mc.solve();
	}
}
