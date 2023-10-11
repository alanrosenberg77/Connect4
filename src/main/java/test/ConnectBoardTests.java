package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cs440.c4.ConnectBoard;

public class ConnectBoardTests {
	
	ConnectBoard blankBoard;
	ConnectBoard sampleBoard;
	int[][] testBoard;

	@Before
	public void setUp() throws Exception {
		blankBoard = new ConnectBoard(10, 10);
		sampleBoard = new ConnectBoard(10, 10);
		testBoard = new int[10][10];
	}

	@Test
	public void test_isColumnFull_Normal() {
		
	}

}
