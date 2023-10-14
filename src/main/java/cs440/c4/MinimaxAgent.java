package cs440.c4;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

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

		int lowScore = Integer.MAX_VALUE;
		int lowChoice = -1;
		
		for(TreeNode n : root.getChildren()) {
			
			int newScore = maxVal(n);
			
			if(lowScore > newScore) {
				
				lowScore = newScore;
				lowChoice = n.getAction();
				
			}
		}
		
		return lowChoice;

	}

	private int maxVal(TreeNode current) {

		// if state is terminal, returning its point value
		if (current.getPoints() != 0)
			return current.getPoints();

		// otherwise...
		else {

			int highScore = Integer.MIN_VALUE; // holds highest score

			// looping through children
			for (TreeNode n : current.getChildren()) {

				// storing the score that the min function spits out
				int score = minVal(n);

				// if score higher than highest score, update highest score
				if (score > highScore)
					highScore = score;

			}

			return highScore;

		}

	}

	private int minVal(TreeNode current) {

		// if state is terminal, returning its point value
		if (current.getPoints() != 0)
			return current.getPoints();

		// otherwise...
		else {

			int lowScore = Integer.MAX_VALUE; // holds lowest score

			// looping through children
			for (TreeNode n : current.getChildren()) {

				// storing the score that the min function spits out
				int score = maxVal(n);

				// if score lower than lowest score, update lowest score
				if (score < lowScore)
					lowScore = score;

			}

			return lowScore;

		}
	}

}
