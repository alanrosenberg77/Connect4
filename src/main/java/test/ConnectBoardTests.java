package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs440.c4.ConnectBoard;

class ConnectBoardTests {
	
	ConnectBoard blankBoard;
	ConnectBoard sampleBoard;

	@BeforeEach
	void setUp() throws Exception {
		
		blankBoard = new ConnectBoard(10, 10);
		sampleBoard = new ConnectBoard(10, 10);
		int[][] testBoard = {
				{0,0,0,0,0,0,0,0,0,0},
				{1,-1,1,1,-1,1,-1,-1,-1,1},
				{1,0,0,0,0,0,0,0,0,0},
				{-1,0,0,0,0,0,0,0,0,0},
				{-1,-1,1,0,0,0,0,0,0,0},
				{1,1,-1,0,0,0,0,0,0,0},
				{-1,1,1,-1,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0},
				{1,-1,0,0,0,0,0,0,0,0},
				{-1,1,1,1,1,0,0,0,0,0}};
		
		sampleBoard.setBoard(testBoard);
		
	}

	@Test
	void test_isColumnFull_EmptyColumn() {
		assertFalse(blankBoard.isColumnFull(0));
	}
	
	@Test
	void test_isColumnFull_NonFullColumn() {
		assertFalse(sampleBoard.isColumnFull(4));
	}
	
	@Test
	void test_isColumnFull_FullColumn() {
		assertTrue(sampleBoard.isColumnFull(1));
	}

}
