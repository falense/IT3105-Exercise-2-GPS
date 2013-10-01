package StateManagers;

import States.AbstractState;

public abstract class LocalStateManager {
	private AbstractState currentState;
	
	
	
	public AbstractState getState(){
		return currentState;
	}
	
	public double evaluateCurrentState(){
		return evaluate(currentState);
	}
	
	public double evaluate(AbstractState state){
		//todo
		return 0;
	}
	
	public AbstractState generateNeighbourState(){
		//todo
		return null;
	}

	public void setState(AbstractState bestState) {
		this.currentState = bestState;
		
	}

}
