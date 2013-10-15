package LocalSearch;





import java.util.ArrayList;

import States.AbstractState;

public class SimulatedAnnealing extends ConstraintBasedLocalSearch{
	
	private double MaxTemprature;
	private double DeltaTemperature;
	private double Temperature;
	private int stepsToSolve = 0;
	private double targetScore;
	private double currentScore;
	private int numberNeighbours;
	
	//example SimulatedAnnealing(20,100,2,0);
	
	public SimulatedAnnealing(int numberNeighbours,double MaxTemprature, double DeltaTemprature, double targetScore){
		this.DeltaTemperature = DeltaTemprature;
		this.numberNeighbours = numberNeighbours;
		this.MaxTemprature = MaxTemprature;
		this.targetScore = targetScore;
	}
	
	
	public void solve(){
		Temperature = MaxTemprature;
		currentScore = -sm.getState().getNumberOfConflicts();
		System.out.println("Current Score = " + (-currentScore));
		//AbstractState currentState = manager.getState();
		
		while (stepsToSolve < 10000 && currentScore < targetScore ){
			
			AbstractState tempState = null;
			AbstractState bestState = null;
			
			ArrayList<AbstractState> newStates= new ArrayList<AbstractState>();
			double tempMaxScore = Double.MIN_VALUE;
			double tempScore = Double.MIN_VALUE;
			
			for (int i = 0 ; i < numberNeighbours ; i++){
				tempState = sm.generateNeighbourState();
				newStates.add(tempState);
				tempScore = -tempState.getNumberOfConflicts();	
				
				//can the original state still be chosen? needs to be addressed maybe added...
				if (tempMaxScore==Double.MIN_VALUE || tempMaxScore < tempScore){
					tempMaxScore = tempScore;
					bestState = tempState;
					
				}
				
			}
			
			
			System.out.println("Best Neighbour Score = " + (-tempMaxScore));
			double q = (tempMaxScore-currentScore)/(-currentScore);
			
			//System.out.println("Value for q = " +q);
			
			
			
			double exponent = (-q/Temperature);
			//System.out.println("Value for exponent = " +exponent);
			
			double p = Math.min(1, 
									Math.pow(2.71828182846, exponent	));				
											
											
			System.out.println("Value for p = " +p);
			
			double x = Math.random();
			
			System.out.println("Value for x = " +x);
			
			if ( x > p ){
				sm.setState(bestState);
				System.out.println("Exploit!");
			} else {
				sm.setState(newStates.get((int)(Math.random()*numberNeighbours)));
				System.out.println("Explore!");
			}
			
			
			//linear
			//Temperature = Math.max(Temperature-DeltaTemperature, 0.1);
			
			//rate of decay
			Temperature = Math.max(Temperature*(1-DeltaTemperature), 0.1); ;
			
			stepsToSolve++;
			currentScore = -sm.getState().getNumberOfConflicts();
			System.out.println("Round number: " +stepsToSolve);
			System.out.println("Current conflicts: " + (-currentScore));
			
	
		}
	} 
	
	@Override
	public int getStepsToSolve() {
		return stepsToSolve;
	}

	@Override
	public int getSolutionNumConflicts() {
		return sm.getState().getNumberOfConflicts();
	}

	@Override
	public void clear() {
		stepsToSolve = 0;
	}

	
}
