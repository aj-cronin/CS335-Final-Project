/*
 * Commented out because VSCode hates JavaFX and JUnit together :)
 */


// package com.finalproject;

// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;

// import org.junit.jupiter.api.Test;

// class GameTests {
	
// 	//Tile Class Tests
// 	Tile t2 = new Tile(2);
// 	Tile t4 = new Tile(4);
	
// 	@Test
// 	void testGetValue() {
// 		assertEquals(t2.getValue(), 2);
// 	}
	
// 	@Test
// 	void testGetColor() {
// 		assertEquals(t2.getColor(), Enums.COLOR.EX1);
// 		assertEquals(t4.getColor(), Enums.COLOR.EX2);
// 	}
	
// 	@Test
// 	void testCombine() {
// 		Tile combinedT4 = t2.combine(t2);
// 		assertEquals(combinedT4.getValue(), 4);
// 	}
	
// 	@Test
// 	void testCombine_nonEqualTiles() {
// 		Tile combinedT = t2.combine(t4);
// 		assertNull(combinedT);
// 	}
	
// 	//Board Class Tests
// 	Board board = new Board();
	
// 	@Test
// 	void testGetBoard() {
// 		ArrayList<ArrayList<Tile>> emptyBoard = new ArrayList<ArrayList<Tile>>();
// 		for(int ii = 0; ii < 4; ii++) {
// 			ArrayList<Tile> row = new ArrayList<Tile>();
// 			for(int jj = 0; jj < 4; jj++) {
// 				row.add(null);
// 			}
// 			emptyBoard.add(row);
// 		}
// 		assertEquals(board.getBoard(), emptyBoard);
// 	}

// 	@Test
// 	void testMoveUp() {
// 		board.move(Enums.DIRECTION.UP);
// 	}
	
// 	@Test
// 	void testMoveDown() {
// 		board.move(Enums.DIRECTION.DOWN);
// 	}
	
// 	@Test
// 	void testMoveLeft() {
// 		board.move(Enums.DIRECTION.LEFT);
// 	}
	
// 	@Test
// 	void testMoveRight() {
// 		board.move(Enums.DIRECTION.RIGHT);
// 	}

// }
