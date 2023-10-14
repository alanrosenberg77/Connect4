package cs440.c4;

import java.util.LinkedList;
import java.util.List;

/**
 * TreeNode instances contain a GameState object, an <i>alpha, beta, and score</i> attributes.
 * The GameState is used to determine the point value of the node, which is stored in
 * <i>score</i>. <i>alpha and beta</i> are used to perform alpha-beta pruning. TreeNodes link-up
 * with each other to create a complete search tree for a minimax agent to do its thing.
 * @author arose
 *
 */
public class TreeNode implements Comparable<TreeNode> {
	
	GameBoard state;	//state of the game
	int alpha;			//used for alpha-beta pruning
	int beta;			//used for alpha-beta pruning
	int points;			//gives point value to node so minimax can do its thing
	int player;			//whose turn it is
	Integer action;		//action used to reach this node
	
	List<TreeNode> children;	//child nodes of this node
	TreeNode parent;			//parent node of this node
	
	/*
	 * Convenient parameterized constructor that also continues construction
	 * of the search tree
	 */
	public TreeNode(GameBoard state, int player, Integer action) {
		
		this.state = state;
		this.player = player;
		
		alpha = Integer.MIN_VALUE;
		beta = Integer.MIN_VALUE;
		
		children = new LinkedList<>();
		
		if(state.gameOver()) {
			calcPoints();
		}
		else {
			generateChildren();
		}
	}
	
	/**
	 * Helper method that determines the amount of points to assign to this
	 * node based on the state of the game. 10 if player wins, -10 if agent wins,
	 * 0 if no winner. Updates <i>points</i> attribute
	 * 
	 * @return calculated point value
	 */
	public int calcPoints() {
		
		//determining if there is a winner and who
		int winner = state.connected();
		
		//if winner, points updated
		if(winner != 0)
			points = winner * 10;
		
		//if no winner, points kept at 0
		else
			points = 0;
		
		return points;
	}
	
	/**
	 * Helper method that generates a list of children based on the current node,
	 * ensuring that all children are different from the current (their parent),
	 * and that everything is properly linked.
	 */
	public void generateChildren() {
		
		//grabbing number of columns for convenience
		int length = state.getBoard().length;
		
		//for every column in the board
		for(int i = 0 ; i < length ; i++) {
			
			//preventing identical children
			if(!state.isColumnFull(i)) {
				
				//making new board based parent
				ConnectBoard newBoard = new ConnectBoard();
				state.addDisk(i, player);
				newBoard.setBoard(state.getBoard());
				
				//making new node with new state and linking to current
				TreeNode newNode = new TreeNode(newBoard, player * (0-1), i);
				newNode.setParent(this);
				children.add(newNode);
			}
		}
	}
	
	@Override
	public int compareTo(TreeNode o) {
		
		if(this.points > o.points) return 1;
		if(this.points < o.points) return -1;
		return 0;
	}
	
	/*
	 * Getters and Setters
	 */

	public GameBoard getState() {
		return state;
	}

	public void setState(GameBoard state) {
		this.state = state;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getBeta() {
		return beta;
	}

	public void setBeta(int beta) {
		this.beta = beta;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public int getPlayer() {
		return player;
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
