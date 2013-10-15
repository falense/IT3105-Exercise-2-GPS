package StateManagers;

import States.AbstractState;
import States.ChessBoard;

public class KQueensManager extends LocalStateManager {
	private String className = KQueensManager.class.getName();
	
	public AbstractState generateNeighbourState(){ //Should be moved to localstatemanager
		ChessBoard board = (ChessBoard) super.getState();
		ChessBoard newBoard = (ChessBoard) board.copy();
		int y = (int) Math.random()*board.getSize();
		newBoard.setRandomRow(y);
		
		return newBoard;
	}
	
	public double evaluate(AbstractState state){ //Should become getNumberOfConflicts
		double score = 0;
		ChessBoard board = (ChessBoard) super.getState();
		
		for (int i = 0 ; i < board.getSize() ; i++){
			for (int j = 0 ; j < board.getSize() ; j++){
				if (board.getPiece(i, j)){
					score += board.findConflicts(i,j);
				}
			}
		}
		return score/2;
		
	}
	
	public AbstractState generateInitialState(int size){
		AbstractState board = new ChessBoard(size);
		
		for (int i = 0 ; i < size ; i++){
			((ChessBoard) board).setTrue(i,(int) Math.random()*size);
		}
		
		return board;
	}

	public KQueensManager(AbstractState state) {
		super(state);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return className;
	}

	@Override
	public LocalStateManager copy() {
		// TODO Auto-generated method stub
		return null;
	}

}
