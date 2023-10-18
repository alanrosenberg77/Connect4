package cs440.c4;

/**
 * An instance of this class is capable of performing a Minimax Search with
 * alpha-beta pruning. When given a state of a Connect 4 game, AlphaBetaAgent
 * objects will reinitialize and be ready to perform the search.
 * @author alanr
 *
 */
public class AlphaBetaAgent implements Agent {
	
	ConnectBoard board;
	int count;
	
	/*
	 * Convenient parameterized constructor
	 */
	public AlphaBetaAgent(GameBoard board) {
		this.board = (ConnectBoard) board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {
		
		this.board = (ConnectBoard) board;
	}

	@Override
	public int nextAction() throws Exception {
		
		return alphaBetaSearch();
	}
	
	/**
	 * Implementation of the Minimax algorithm with alpha-beta pruning. Calls on
	 * min method to start because agent will always attempt to minimize score.
	 * @return
	 */
	private int alphaBetaSearch() {
		
		System.gc();
		long before = System.nanoTime();
		
		//calling min to find minimum option and returning it
		SearchTuple solution = minVal(board.copy(), Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
		
		long after = System.nanoTime();
		System.out.println(after-before);
		
		return solution.getAction();
	}
	
	/**
	 * Max from the minimax algorithm with alpha-beta pruning. Chooses the maximum value
	 * from the child states. Limiting depth of search tree to 7
	 * 
	 * @param current state
	 * @param alpha
	 * @param beta
	 * @param depth of search tree
	 * @return maximum utility of child states
	 */
	private SearchTuple maxVal(ConnectBoard current, int alpha, int beta, int depth) {
		
		count++;
//		System.err.println(count);
		
		//exiting early if in terminal state or too deep, returning only utility
		if(current.gameOver() || depth >= 7)
			return new SearchTuple(current.calcPoints(), null);
		
		//setting current best utility to negative infinity
		SearchTuple best = new SearchTuple(Integer.MIN_VALUE, null);
		
		//for every possible action...
		for(int i = 0 ; i < current.getBoard()[0].length ; i++) {
			
			//as long as its permissible...
			if(!current.isColumnFull(i)) {
				
				//do it
				ConnectBoard newBoard = current.copy();
				newBoard.addDisk(i, 1);
				
				//and ask min what she thinks of it
				SearchTuple result = minVal(newBoard, alpha, beta, depth+1);
				
				//if min gave back something better than what max has...
				if(result.getScore() > best.getScore()) {
					
					//store it and its action
					best.setScore(result.getScore());
					best.setAction(i);
					
					//update alpha if necessary
					alpha = Integer.max(alpha, best.getScore());
				}
				
				//if best score exceeds beta, then return best score
				if(best.getScore() >= beta) {
					return best;
				}
			}
			
		}
		
		return best;
	}
	
	/**
	 * Min from the minimax algorithm with alpha-beta pruning. Chooses the minimum value
	 * from the child states. Limiting depth of search tree to 7
	 * 
	 * @param current state
	 * @param alpha
	 * @param beta
	 * @return minimum utility of child states
	 */
	private SearchTuple minVal(ConnectBoard current, int alpha, int beta, int depth) {

		count++;
//		System.err.println(count);
		
		//exiting early if in terminal state or too deep, returning only utility
		if(current.gameOver() || depth >= 7)
			return new SearchTuple(current.calcPoints(), null);
		
		//setting current worst utility to infinity
		SearchTuple worst = new SearchTuple(Integer.MAX_VALUE, null);
		
		//for every possible action...
		for(int i = 0 ; i < current.getBoard()[0].length ; i++) {
			
			//as long as its permissible...
			if(!current.isColumnFull(i)) {
				
				//do it
				ConnectBoard newBoard = current.copy();
				newBoard.addDisk(i, -1);
				
				//and ask max what he thinks of it
				SearchTuple result = maxVal(newBoard, alpha, beta, depth+1);
				
				//if max gave back something worse than what min has...
				if(result.getScore() < worst.getScore()) {
					worst.setScore(result.getScore());
					worst.setAction(i);
					
					//update beta if necessary
					beta = Integer.min(beta, worst.getScore());
				}
				
				//if best score exceeds alpha, then return worst score
				if(worst.getScore() <= alpha) {
					return worst;
				}
			}
			
		}
		
		return worst;
	}

}
