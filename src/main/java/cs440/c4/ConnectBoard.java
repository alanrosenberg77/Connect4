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
		board = new int[col][row];
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
		
	}

	@Override
	public int connected() {
		
		//looping through all spaces
		for(int i = 0 ; i < board.length ; i++) {
			for(int j = 0 ; j < board[0].length ; j++) {
				
				//if nw diagonal is connected, return winning player
				int nw = checkNW(i, j);
				if(nw != 0)
					return nw;
				
				//if column is connected, return winning player
				int n = checkN(i, j);
				if(n != 0)
					return n;
				
				//if ne diagonal is connected, return winning player
				int ne = checkNE(i, j);
				if(ne != 0)
					return ne;
				
				//if row is connected, return winning player
				int e = checkE(i, j);
				if(e != 0)
					return e;
			}
		}
		
		//if not connected at all, returning no winner
		return NONE;
	}
	
	/**
	 * Helper method for connected. Checks north (up) from a certain disk
	 * for <i>N</i> connected disks. If found, returns the winning player.
	 * @return id of winning player, 0 if no winner
	 */
	private int checkN(int row, int col) {
		
		//grabbing which player to check for
		int player = board[col][row];
		
		//tracking how many connected disks there are
		int streak = 0;
		
		//looping up the column
		for(int i = 0 ; i < N ; i++) {
			
			//checking space for matching disk
			if(board[col][row+i] == player) {
				
				//incrementing streak
				streak++;
				
				//if streak meets requirement...
				if(streak == N) {
					
					//returning winning player
					return player;
					
				}
			}
		}
		
		//if long enough streak not found, returning no winner
		return NONE;
	}
	
	/**
	 * Helper method for connected. Checks east (right) from a certain disk
	 * for <i>N</i> connected disks. If found, returns the winning player.
	 * @return id of winning player, 0 if no winner
	 */
	private int checkE(int row, int col) {
		
		//grabbing which player to check for
		int player = board[col][row];
		
		//tracking how many connected disks there are
		int streak = 0;
		
		//looping across the row
		for(int i = 0 ; i < N ; i++) {
			
			//checking space for matching disk
			if(board[col+i][row] == player) {
				
				//incrementing streak
				streak++;
				
				//if streak meets requirement...
				if(streak == N) {
					
					//returning winning player
					return player;
				}
			}
		}
		
		//if long enough streak not found, returning no winner
		return NONE;
	}
	
	/**
	 * Helper method for connected. Checks northeast (diagonal right) from a
	 * certain disk for <i>N</i> connected disks. If found, returns the winning
	 * player.
	 * @return id of winning player, 0 if no winner
	 */
	private int checkNE(int row, int col) {
		
		//grabbing which player to check for
		int player = board[col][row];
		
		//tracking how many connected disks there are
		int streak = 0;
		
		//looping across the row
		for(int i = 0 ; i < N ; i++) {
			
			//checking space for matching disk
			if(board[col+i][row+i] == player) {
				
				//incrementing streak
				streak++;
				
				//if streak meets requirement...
				if(streak == N) {
					
					//returning winning player
					return player;
				}
			}
		}
		
		//if long enough streak not found, returning no winner
		return NONE;
	}
	
	/**
	 * Helper method for connected. Checks northwest (diagonal left) from a
	 * certain disk for <i>N</i> connected disks. If found, returns the winning
	 * player.
	 * @return id of winning player, 0 if no winner
	 */
	private int checkNW(int row, int col) {
		
		//grabbing which player to check for
		int player = board[col][row];
		
		//tracking how many connected disks there are
		int streak = 0;
		
		//looping across the row
		for(int i = 0 ; i < N ; i++) {
			
			//checking space for matching disk
			if(board[col-i][row+i] == player) {
				
				//incrementing streak
				streak++;
				
				//if streak meets requirement...
				if(streak == N) {
					
					//returning winning player
					return player;
				}
			}
		}
		
		//if long enough streak not found, returning no winner
		return NONE;
	}

	@Override
	public boolean gameOver() {
		
		//calling connected to return which player has won
		int player = connected();
		
		//as long as connected returns 1 or -1 (not 0)...
		if(player != 0)
			
			//game over
			return true;
		
		//otherwise...
		else
			
			//not game over
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
