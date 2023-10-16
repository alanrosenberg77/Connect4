package cs440.c4;

public class ImprovedMinimaxAgent implements Agent {
	
	GameBoard board;
	
	TreeNode root;
	
	/*
	 * Convenient parameterized constructor
	 */
	public ImprovedMinimaxAgent(GameBoard board) {
		this.board = board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {
		
		this.board = board;
		
		root = new TreeNode(board, -1, null);

	}

	@Override
	public int nextAction() throws Exception {
		return alphaBetaSearch();
	}
	
	private int alphaBetaSearch() {
		
		int lowScore = Integer.MAX_VALUE;
		int lowChoice = -1;
		
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		
		for(TreeNode n : root.getChildren()) {
			
			int newScore = maxVal(n, alpha, beta);
			
			if(lowScore > newScore) {
				lowScore = newScore;
				lowChoice = n.getAction();
				beta = Integer.min(beta, lowScore);
			}
			
			if(lowScore <= alpha)
				return lowChoice;
		}
		
		return lowChoice;
	}
	
	private int maxVal(TreeNode current, int alpha, int beta) {
		
		if(current.getPoints() != 0)
			return current.getPoints();
		
		else {
			
			int highScore = Integer.MIN_VALUE;
			
			for(TreeNode n : current.getChildren()) {
				
				int score = minVal(n, alpha, beta);
				
				if(score > highScore) {
					
					highScore = score;
					alpha = Integer.max(alpha, highScore);
				}
				
				if(highScore >= beta)
					return highScore;
			}
			
			return highScore;
		}
	}
	
	private int minVal(TreeNode current, int alpha, int beta) {
		
		if(current.getPoints() != 0)
			return current.getPoints();
		
		else {
			
			int lowScore = Integer.MAX_VALUE;
			
			for(TreeNode n : current.getChildren()) {
				
				int score = maxVal(n, alpha, beta);
				
				if(score < lowScore) {
					lowScore = score;
					beta = Integer.min(beta, lowScore);
				}
				
				if(lowScore <= alpha)
					return lowScore;
			}
			
			return lowScore;
		}
	}

}
