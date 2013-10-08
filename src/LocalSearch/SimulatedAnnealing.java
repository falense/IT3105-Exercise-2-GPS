package LocalSearch;





import java.util.ArrayList;

import States.AbstractState;

public class SimulatedAnnealing extends ConstraintBasedLocalSearch{
	
	private double MaxTemprature;
	private double DeltaTemperature;
	private double Temperature;
	private int counter = 0;
	private double targetScore;
	private double currentScore;
	private int numberNeighbours;
	
	public SimulatedAnnealing(int numberNeighbours,double MaxTemprature, double DeltaTemprature, double targetScore){
		this.DeltaTemperature = DeltaTemprature;
		this.numberNeighbours = numberNeighbours;
		this.MaxTemprature = MaxTemprature;
		this.targetScore = targetScore;
	}
	
	
	public void solve(){
		Temperature = MaxTemprature;
		currentScore = sm.evaluate(sm.getState());
		//AbstractState currentState = manager.getState();
		
		while (counter < 10000 && currentScore < targetScore){
			
			AbstractState tempState = null;
			AbstractState bestState = null;
			
			ArrayList<AbstractState> newStates= new ArrayList<AbstractState>();
			double tempMaxScore = 0;
			double tempScore = 0;
			
			for (int i = 0 ; i < numberNeighbours ; i++){
				tempState = sm.generateNeighbourState();
				newStates.add(tempState);
				tempScore = sm.evaluate(tempState);	
				if (tempMaxScore==0 || tempMaxScore < tempScore){
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
			counter++;
			currentScore = sm.evaluate(sm.getState());
	
		}
			
		//return sm.getState();  *^***** THIS NEEDS FIXING ******
	} 
	
	@Override
	public int getStepsToSolve() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSolutionNumConflicts() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
}
