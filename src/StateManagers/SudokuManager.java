package StateManagers;

import States.AbstractState;
import States.GraphState;
import States.Sudoku;

public class SudokuManager extends LocalStateManager{
	public static final String className = SudokuManager.class.getName();
	private String sudokufile;

	
	public SudokuManager(String soduFile) {
		super(new Sudoku(soduFile));
		this.sudokufile = soduFile;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return className;
	}

	@Override
	public LocalStateManager copy() {
		// TODO Auto-generated method stub
		return new SudokuManager(sudokufile);
	}

}
