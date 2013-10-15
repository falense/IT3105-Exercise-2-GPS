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
			
			double q = (tempMaxScore-currentScore)/(currentScore);
			double p = Math.min(1, 
									Math.pow(2.71828182846, 
											(-q/Temperature)
											));
			double x = Math.random();
			
			if ( x>p ){
				sm.setState(bestState);
			} else {
				sm.setState(newStates.get((int)(Math.random()*numberNeighbours)));
			}
			
			Temperature -= DeltaTemperature;
			stepsToSolve++;
			currentScore = -sm.getState().getNumberOfConflicts();
	
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
