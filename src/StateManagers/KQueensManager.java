package StateManagers;

import States.AbstractState;
import States.ChessBoard;

public class KQueensManager extends LocalStateManager {
	
	
	public AbstractState generateNeighbourState(){
		ChessBoard board = (ChessBoard) super.getState();
		ChessBoard newBoard = (ChessBoard) board.copyBoard();
		int y = (int) Math.random()*board.getSize();
		newBoard.setRandomRow(y);
		
		return newBoard;
	}
	
	public double evaluate(AbstractState state){
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

}
