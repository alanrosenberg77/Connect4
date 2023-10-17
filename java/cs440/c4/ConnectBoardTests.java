package cs440.c4;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConnectBoardTests {
	
	ConnectBoard blankBoard;
	ConnectBoard sampleBoard;
	int[][] testBoard = {
			{0,1,0,0,0,0,0,0,0,0},
			{0,-1,0,0,0,0,0,0,0,0},
			{0,-1,0,0,0,0,0,0,0,0},
			{0,-1,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,0,0,0,0},
			{0,-1,0,0,0,0,0,0,0,0},
			{0,1,0,0,0,0,-1,0,0,1},
			{0,1,0,0,1,-1,1,0,0,1},
			{0,-1,0,0,-1,1,1,0,-1,1},
			{0,1,1,-1,-1,1,-1,0,1,-1}};

	@BeforeEach
	void setUp() throws Exception {
		
		blankBoard = new ConnectBoard(10, 10);
		sampleBoard = new ConnectBoard(10, 10);
		
		sampleBoard.setBoard(testBoard);
		
	}
	
	/*
	 * Testing isColumnFull
	 */

	/*
	 * Testing that isColumnFull works properly with an empty column
	 */
	@Test
	void test_isColumnFull_EmptyColumn() {
		assertFalse(blankBoard.isColumnFull(0));
	}
	
	/*
	 * Testing that isColumnFull works properly with a non-empty, non-full column
	 */
	@Test
	void test_isColumnFull_NonFullColumn() {
		assertFalse(sampleBoard.isColumnFull(4));
	}
	
	/*
	 * Testing that isColumnFull works properly with a full column
	 */
	@Test
	void test_isColumnFull_FullColumn() {
		assertTrue(sampleBoard.isColumnFull(1));
	}
	
	/*
	 * Testing addDisk
	 */
	
	/*
	 * Testing that addDisk works properly with non-full columns
	 */
	@Test
	void test_addDisk_Normal() {
		
		int row1 = blankBoard.addDisk(0, blankBoard.USER);
		int row2 = sampleBoard.addDisk(4, sampleBoard.AGENT);
		
		assertEquals(9, row1);
		assertEquals(6, row2);
		
		int[][] blank = blankBoard.getBoard();
		int[][] sample = sampleBoard.getBoard();
		
		assertEquals(1, blank[9][0]);
		assertEquals(-1, sample[6][4]);
	}
	
	/*
	 * Testing that addDisk works properly with a full column
	 */
	@Test
	void test_addDisk_FullColumn() {
		
		int row = sampleBoard.addDisk(1, sampleBoard.USER);
		
		assertEquals(-1, row);
		
		int[][] sample = sampleBoard.getBoard();
		
		for(int i = 0 ; i < sample[0].length ; i++) {
			
			assertEquals(testBoard[0][i], sample[0][i]);
			
		}
	}
	
	/*
	 * Testing connected
	 */

	/*
	 * Testing that connected works properly with board with winning string of disks
	 */
	@Test
	void test_connected_Normal() {
		
		int winner = sampleBoard.connected();
		
		assertEquals(sampleBoard.AGENT, winner);
	}
	
	/*
	 * Testing that connected works properly with blank board (no winner)
	 */
	@Test
	void test_connected_NoWinner() {
		
		int winner = blankBoard.connected();
		
		assertEquals(blankBoard.NONE, winner);
	}
}
