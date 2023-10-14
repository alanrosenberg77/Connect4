package cs440.c4;

import java.util.LinkedList;
import java.util.Queue;

public class MinimaxAgent implements Agent {
	
	GameBoard board;
	
	TreeNode root;

	/*
	 * Convenient parameterized constructor
	 */
	public MinimaxAgent(GameBoard board) {
		this.board = board;
	}

	@Override
	public void initializeWithBoard(GameBoard board) throws Exception {

		this.board = board;
		
		root = new TreeNode(board, -1, null);
	}

	@Override
	public int nextAction() throws Exception {
		return minimaxSearch();
	}
	
	private int minimaxSearch() {
		
		Queue<TreeNode> q = new LinkedList<>();
		
		q.add(root);
	}

}
