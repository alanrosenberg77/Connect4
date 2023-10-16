package cs440.c4;

import java.util.LinkedList;
import java.util.List;

/**
 * SearchTuple instances are simple data sacks that contain a score and associated action.
 * Used by the minimax functions to perform their tasks.
 * @author arose
 *
 */
public class SearchTuple {
	
	Integer score;	//utility
	Integer action;	//associated action
	
	/*
	 * Convenient parameterized constructor that also continues construction
	 * of the search tree
	 */
	public SearchTuple(Integer score, Integer action) {
		
		this.score = score;
		this.action = action;
	}
	
	/*
	 * Getters and Setters
	 */

	public Integer getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
	
	public Integer getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

}
