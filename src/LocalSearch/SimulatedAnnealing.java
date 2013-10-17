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
	private int maxRuns;
	private boolean linear;
	
	//example SimulatedAnnealing(20,100,2,0);
	
	public SimulatedAnnealing(int numberNeighbours,double MaxTemprature, double DeltaTemprature, double targetScore,boolean debug, int maxRuns, boolean linear){
		super(debug);
		this.DeltaTemperature = DeltaTemprature;
		this.numberNeighbours = numberNeighbours;
		this.MaxTemprature = MaxTemprature;
		this.targetScore = targetScore;
		this.maxRuns = maxRuns;
		this.linear = linear;
	}
	
	//sets deltatemp to decrease linearly throughout the run
	public SimulatedAnnealing(int numberNeighbours,double MaxTemprature, double targetScore,boolean debug, int maxRuns, boolean linear){
		super(debug);
		double temp;
		if (linear){
			temp = MaxTemprature/maxRuns;
		} else {
			temp = MaxTemprature/maxRuns;
		}
		this.DeltaTemperature = 
		this.numberNeighbours = numberNeighbours;
		this.MaxTemprature = MaxTemprature;
		this.targetScore = targetScore;
		this.maxRuns = maxRuns;
		this.linear = linear;
	}
	
	public void solve(){
		Temperature = MaxTemprature;
		currentScore = -sm.getState().getNumberOfConflicts();
		print("Current Score = " + (-currentScore));
		//AbstractState currentState = manager.getState();
		
		while (stepsToSolve < maxRuns && currentScore < targetScore ){
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
					print("Best Neighbour Score = " + (-tempMaxScore));
				}
				
			}
			
			double q = (tempMaxScore-currentScore)/(-currentScore);
			//System.out.println("Value for q = " +q);
			
			double exponent = (-q/Temperature);
			//System.out.println("Value for exponent = " +exponent);
			
			double p = Math.min(1, Math.pow(2.71828182846, exponent	));				
			
			double x = Math.random();
			
			if ( x > p ){
				sm.setState(bestState);
				print("Exploit!");
			} else {
				sm.setState(newStates.get((int)(Math.random()*numberNeighbours)));
				print("Explore!");
			}
			
			//temperature:
			if (linear){
				Temperature = Math.max(Temperature-DeltaTemperature, 0.1); //linear
			}
			else {
				Temperature = Math.max(Temperature*(1-DeltaTemperature), 0.1);	//rate of decay
			}
			
			stepsToSolve++;
			currentScore = -sm.getState().getNumberOfConflicts();
			print("Round number: " +stepsToSolve);
			print("Current conflicts: " + (-currentScore));
			print("Current Temperature: " + Temperature);
	
		}

		if (debug) sm.getState().display();
	} 
	
	@Override
	public int getStepsToSolve() {
		return stepsToSolve;
	}

	public void clear() {
		stepsToSolve = 0;
	}
}
