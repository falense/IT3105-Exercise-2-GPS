import LocalSearch.MinConflicts;
import StateManagers.GraphColorManager;


public class UserInterface {
	
	public void RunMCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		MinConflicts mc = new MinConflicts();
		mc.setStateManager(man);
		mc.solve();
	}
	public static void main(String[] args) {
		UserInterface s = new UserInterface();
		s.RunMCTest();
	}
}
