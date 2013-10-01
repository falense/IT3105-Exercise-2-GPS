package States;

public class ChessBoard extends AbstractState  {
	boolean[][] board;
	
	public ChessBoard(int size){
		board = new boolean[size][size];
		for (int i = 0 ; i < size ; i++){
			for (int j = 0 ; j < size ; j++){
				board[i][j] = false;
			}
		}
	}
	
	
	

}
