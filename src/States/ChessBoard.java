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
		for (int k = 0 ; k < size ; k++){
			setRandomRow(k);
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
	
	private int getSize(){
		return board.length;
	}
	
	public boolean getPiece(int x, int y){	// just used for graphics.
		return board[x][y];
	}
	
	/*
	private void changePiece(int x, int y){
		this.board[x][y] = !board[x][y];
	}
	
	private void setTrue(int x, int y){
		this.board[x][y] = true;
	}
	
	private void setFalse(int x, int y){
		this.board[x][y] = true;
	}
	*/
	
	private void setRandomRow(int y){
		for (int x = 0 ; x < board.length ; x++){
			board[y][x] = false;
		}
		board[y][(int) Math.random()*board.length] = true;
	}
	
	private int findConflicts(int x, int y){
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
	

	@Override	//done
	public LinkedList<Integer> getVars() { 
		LinkedList<Integer> vars = new LinkedList<Integer>();
		for (int i = 0; i < board.length; i++)
			vars.add(i);
		return vars;
	}

	@Override	//done
	public int getNumberOfConflicts(int var) {
		int x = getValue(var);
		return findConflicts(var,x);
	}

	@Override	//done
	public LinkedList<Integer> getPossibleValues() {
		LinkedList<Integer> vars = new LinkedList<Integer>();
		for (int i = 0; i < board.length; i++)
			vars.add(i);
		return vars;
	}


	@Override	//done
	public int getValue(int var) {
		int queenLocation = -1;
		for (int i = 0; i < board.length; i++){
			if (board[var][i])
				queenLocation = i;
		}
		return queenLocation;
	}

	@Override	//done
	public void setValue(int var, int value) {
		for (int i = 0; i < board.length; i++){
			board[var][i] = false;
		}
		board[var][value] = true;
	}

}
