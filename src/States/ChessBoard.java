package States;

import java.util.LinkedList;

public class ChessBoard extends AbstractState  {
	boolean[][] board;
	
	public ChessBoard(int size){
		this.board = new boolean[size][size];
		for (int i = 0 ; i < size ; i++){
			for (int j = 0 ; j < size ; j++){
				board[i][j] = false;
			}
		}
	}
	
	public ChessBoard(ChessBoard oldBoard){
		this.board = new boolean[oldBoard.getSize()][oldBoard.getSize()];
		for (int i = 0 ; i < oldBoard.getSize() ; i++){
			for (int j = 0 ; j < oldBoard.getSize() ; j++){
				if (oldBoard.getPiece(i, j))
					board[i][j] = true;
				else
					board[i][j] = false;
			}
		}
	}
	
	public int getSize(){
		return board.length;
	}
	
	public boolean getPiece(int x, int y){
		return board[x][y];
	}
	
	
	public void changePiece(int x, int y){
		this.board[x][y] = !board[x][y];
	}
	
	public void setTrue(int x, int y){
		this.board[x][y] = true;
	}
	
	public void setFalse(int x, int y){
		this.board[x][y] = true;
	}
	
	public void setRandomRow(int y){
		for (int x = 0 ; x < board.length ; x++){
			board[y][x] = false;
		}
		board[y][(int) Math.random()*board.length] = true;
	}
	
	public int findConflicts(int x, int y){
		int cons = 0;
		for (int i = 0 ; i < board.length ; i++){
			for (int j = 0 ; j < board.length ; j++){
				if (board[i][j] && (i==x || j==y || (x-y)==(i-j) || (x+y) == (i+j))){
					cons++;
				}
			}
		}
		return cons-1;
	}
	
	public AbstractState copy(){
		return new ChessBoard(this);
	}
	

	
	
	

	@Override
	public LinkedList<Integer> getVars() { 
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfConflicts(int var) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<Integer> getPossibleValues() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getValue(int var) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setValue(int var, int value) { //Remove old queen
		// TODO Auto-generated method stub
	}

}
