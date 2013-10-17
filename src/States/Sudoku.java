package States;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

public class Sudoku extends AbstractState{
	private int[][] board;
	private int squares;
	private int size;
	LinkedList<Integer> variables;
	
	public Sudoku(String filename) {
		variables = new LinkedList<Integer>();
		loadBoard(filename);
		//reserveVariables();
		makeNumbersAppear();		
		
	}
	
	public Sudoku(Sudoku oldSudoku){
		this.size = oldSudoku.size;
		this.board = new int[size][size];
		this.squares = oldSudoku.squares;
		variables = oldSudoku.getVars();
		for (int i = 0 ; i < size*size ; i++){
			setValue(i, oldSudoku.getValue(i));
		}
	}
	
	public void printList(LinkedList<Integer> list){
		for (int i : list){
			System.out.print(i+" ");
		}
	}
	
	public void printList(int[] list){
		for (int i : list){
			System.out.print(i+" ");
		}
	}
	
	private void loadBoard(String filename){
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + filename);
		}
		String line = null;
		
		try {
			
			int row = 0;
			
			while ((line = reader.readLine()) != null) {
				String substrings[] = line.split(" ");
				if (row == 0){
					this.size = Integer.parseInt(substrings[0]);
					this.board = new int[this.size][this.size];							
				}
				else if (row == 1){
					this.squares = Integer.parseInt(substrings[0]);
				}
				else{
					for (int i = 0; i < board.length; i++){
						board[row-2][i]=Integer.parseInt(substrings[i]);
					}
				}			
				row++;
			}
		} catch (IOException e) {
			System.err.println("Reading from file failed");
		}
		
		reserveVariables();
	}
	private int getNumber(int x, int y){
		return board[x][y];
	}
	
	private void reserveVariables(){
		for (int y = 0; y < size*size; y++){
			if(getValue(y)==0)
				variables.add(y);
		}
	}
	
	private int getRandomValue(){
		int random = new Random().nextInt(size);
				return random + 1;
	}
	
	private void makeNumbersAppear(){
		for (int x : variables){
			setValue(x, getRandomValue() );
			
		}
		System.out.println("Initial randomized board:");
		display();
		System.out.println("");
	}
	
	private LinkedList<Integer> returnSquare(int i, int j){
		//size = 9;
		//squares = 3;
		
		LinkedList<Integer> returnList = new LinkedList<Integer>();
		
		int ySquare = (int) Math.floor(1.0*(i)/squares);
		int xSquare = (int) Math.floor(1.0*(j)/squares);
		
		for (int y = 0; y < size; y++){
			for (int x = 0; x < size; x++){
				if ( (int) Math.floor(1.0*(y)/squares) == ySquare && (int) Math.floor(1.0*(x)/squares)==xSquare){
					if (!(i==y && j==x)){
							returnList.add(getNumber(y,x));
					}
				}
				
					
			}
		}
		return returnList;
	}
	
	private LinkedList<Integer> returnRow(int i, int j){
		LinkedList<Integer> returnList = new LinkedList<Integer>();
		for (int x = 0; x < size; x++){
			if(j!=x){
				returnList.add(getNumber(i, x));
			}
		}
		return returnList;
	}
	
	private LinkedList<Integer> returnColumn(int i, int j){
		LinkedList<Integer> returnList = new LinkedList<Integer>();
		for (int x = 0; x < size; x++){
			if(i!=x){
				returnList.add(getNumber(x, j));
			}
		}
		return returnList;
	}
	
	private int[] makeVar(int var){
		int[] list = new int[2];
		list[0] = (int) Math.floor(1.0*var/size);
		list[1] = var % size;
		return list;
	}
	
	
	private void setNumber(int x, int y, int value){
		this.board[x][y] = value;
	}

	@Override
	public LinkedList<Integer> getVars() {
		return (LinkedList<Integer>) variables.clone();
	}

	@Override
	public int getNumberOfConflicts(int var) {
		int[] coord = makeVar(var);
		LinkedList<Integer> columns = returnColumn(coord[0], coord[1]);
		LinkedList<Integer> rows = returnRow(coord[0], coord[1]);
		LinkedList<Integer> square = returnSquare(coord[0], coord[1]);
		int myNumber = getValue(var);
		int wrongs = 0;
		
		for (int i : columns){
			if(myNumber==i)
				wrongs++;
		}
		
		for (int j : rows){
			if(myNumber==j)
				wrongs++;
		}
		
		for (int k : square){
			if(myNumber==k)
				wrongs++;
		}
		return wrongs;
	}

	@Override
	public LinkedList<Integer> getPossibleValues() {
		LinkedList<Integer> vars = new LinkedList<Integer>();
		for (int i = 1; i < size; i++){
			vars.add(i);
		}
		return vars;
	}


	
	@Override
	public int getValue(int var) {
		int[] vars = makeVar(var);
		return getNumber(vars[0],vars[1]);
	}

	@Override
	public void setValue(int var, int value) {
		int[] vars = makeVar(var);
		setNumber(vars[0],vars[1],value);
	}

	@Override
	public void display() {
		for (int i = 0; i < size; i++){
			System.out.println();
			for (int j = 0; j < size; j++){
				System.out.print(board[i][j]+" ");		
			}
		}
	}

	@Override
	public AbstractState copy() {
		return new Sudoku(this);
	}

}
