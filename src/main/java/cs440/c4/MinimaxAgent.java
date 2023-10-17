package cs440.c4;

/**
 * An instance of this class is capable of performing a Minimax Search. When given
 * a state of a Connect 4 game, it will reinitialize and be ready to perform the search.
 * @author alanr
 *
 */
public class MinimaxAgent implements Agent {

	ConnectBoard board;

	/*
	 * Convenient parameterized constructor
	 */
	public MinimaxAgent(GameBoard board) {
		this.board = (ConnectBoard) board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {

		this.board = (ConnectBoard) board;
	}

	@Override
	public int nextAction() throws Exception {
		
		return minimaxSearch();
	}

	/**
	 * Implementation of the Minimax algorithm. Calls on min method because the
	 * agent will always attempt to minimize score.
	 * @return
	 */
	private int minimaxSearch() {
		
//		System.out.println("\nStarting Minimax Search...");
		
		System.gc();
		long before = System.nanoTime();

		//calling min to find minimum option and returning it
		SearchTuple solution = minVal(board.copy(), 0);
		
		long after = System.nanoTime();
		System.out.println(after-before);
		
		return solution.getAction();

	}

	/**
	 * Max from the minimax algorithm. Chooses the maximum from the child sates.
	 * @param current state
	 * @param depth
	 * @return maximum utility of child states
	 */
	private SearchTuple maxVal(ConnectBoard current, int depth) {
		
		//exiting early if in terminal state, returning only utility
		if(current.gameOver() || depth >= 7)
			return new SearchTuple(current.calcPoints(), null);
		
		//setting current best utikity to negative infinity
		SearchTuple best = new SearchTuple(Integer.MIN_VALUE, null);
		
		//for every possible action...
		for(int i = 0 ; i < current.getBoard()[0].length ; i++) {
			
			//as long as its permissible...
			if(!current.isColumnFull(i)) {
				
				//do it
				ConnectBoard newBoard = current.copy();
				newBoard.addDisk(i, 1);
				
				//ask min what she thinks about it
				SearchTuple result = minVal(newBoard, depth+1);
				
				//if min gave back something better than what max has...
				if(result.getScore() > best.getScore()) {
					
					//store it and its action
					best.setScore(result.getScore());
					best.setAction(i);
				}
			}
			
		}
		
		return best;

	}

	/**
	 * Min from the minimax algorithm. Chooses the minimum from tghe child states.
	 * @param current state
	 * @param depth
	 * @return minimum utility of child states
	 */
	private SearchTuple minVal(ConnectBoard current, int depth) {

		//exitinig early if in terminal state, returning only utility
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
				SearchTuple result = maxVal(newBoard, depth+1);
				
				//if max gave back something worse than what min has...
				if(result.getScore() < worst.getScore()) {
					
					//store it and its action
					worst.setScore(result.getScore());
					worst.setAction(i);
				}
			}
			
		}
		
		return worst;
	}

}
