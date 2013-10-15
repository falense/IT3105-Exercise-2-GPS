import LocalSearch.MinConflicts;
import StateManagers.GraphColorManager;
import StateManagers.KQueensManager;


public class UserInterface {
	
	public void RunMCTest(){
		GraphColorManager man = new GraphColorManager("graph-color-3.txt");
		MinConflicts mc = new MinConflicts();
		mc.setStateManager(man);
		mc.solve();
		KQueensManager qm = new KQueensManager(4);
	}
	public static void main(String[] args) {
		UserInterface s = new UserInterface();
		s.RunMCTest();
	}
}
