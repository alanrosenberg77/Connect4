package cs440.c4;

/**
 * Instances of the ConnectBoard class are implementations of the GameBoard
 * interface. They can addDisks to a column of the game board, determine whether
 * a winning string of disks exists, answer whether the game is over, and determine
 * if a column is full. ConnectBoard instances hold onto a 2D array of integers to
 * serve as a physical representation of the game board, storing the data needed to
 * represent the state of the game.
 * 
 * @author alanr
 *
 */
public class ConnectBoard implements GameBoard {
	
	//Actual game board where disks will be stored
	int[][] board;
	
	/*
	 * Convenient constructor for parameterized ConnectBoard
	 */
	public ConnectBoard(int row, int col) {
		board = new int[row][col];
	}
	
	/*
	 * Convenient constructor for default ConnectBoard
	 */
	public ConnectBoard() {
		board = new int[7][6];
	}

	@Override
	public int addDisk(int c, int player) {
		
		int[] col = board[c];	//retrieving specified column
		
		//looping through column...
		for(int i = 0 ; i < col.length ; i++) {
			
			//until we find an available space
			if(col[i] == AVAIL) {
				
				//putting a disk there based on whose turn it is
				col[i] = player;
				
				//returning index of column where disk was dropped
				return i;
			}
		}
		
		//returning -1 if column is full
		return -1;
		
		//TODO ensure this is right
		
	}

	@Override
	public int connected() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean gameOver() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isColumnFull(int c) {
		
		int[] col = board[c];	//retrieving the column
		
		boolean isFull = true;
		
		//looping through column...
		for(int i : col) {
			
			//if we find an open space...
			if(i == 0) {
				
				//then its not full
				isFull = false;
			}
		}
		
		return isFull;
	}
	
	/*
	 * Getter and Setter
	 */
	@Override
	public int[][] getBoard() {
		return board;
	}
	
	public void setBoard(int[][] newBoard) {
		board = newBoard;
	}

}
