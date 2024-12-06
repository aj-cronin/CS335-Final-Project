package com.finalproject;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.scene.paint.Color;

import java.util.ArrayList;

class GameTests {
	//Tile Class Tests
	Tile t2 = new Tile(2);
	Tile t4 = new Tile(4);
	Theme light = new Theme("light,d8c644,ff7575,FAF8F0,756452,FFFFFF");

	@Test
	void testConstructorWithTheme() {
		Tile themeTile = new Tile(2, new Theme("othertheme,d8c644,ff7575,FAF8F0,756452,FFFFFF"));
		themeTile.setTheme(light);
	}
	
	@Test
	void testGetValue() {
		assertEquals(t2.getValue(), 2);
	}

	@Test
	void testGetColor() {
		assertEquals(t2.getColor(), light.getColor(0));
		assertEquals(t4.getColor(), light.getColor(1));
	}

	@Test
	void testCombine() {
		Tile combinedT4 = t2.combine(t2);
		assertEquals(combinedT4.getValue(), 4);
	}

	@Test
	void testCombine_nonEqualTiles() {
		Tile combinedT = t2.combine(t4);
		assertNull(combinedT);
	}

	


	// Leaderboard Tests
	Leaderboard lb = new Leaderboard("leaderboard.csv");
	Leaderboard badLB = new Leaderboard("badScore.csv");
	Leaderboard emptyLB = new Leaderboard("empty.csv");
	Leaderboard nonExistLB = new Leaderboard("doesnotexist.csv");
	Leaderboard fullLB = new Leaderboard("fullLeaderboard.csv");
	Leaderboard writeLB = new Leaderboard("writeback.csv");
	Leaderboard notAFileLB = new Leaderboard("/something");

	@Test
	void testLeaderboardCreatePartiallyFull() {
		assertEquals(lb.toString(), "1: melanie - 2048");
	}

	@Test
	void testBadScoreInput() {
		assertEquals(badLB.toString(), "");
	}

	@Test
	void testEmptyInput() {
		assertEquals(emptyLB.toString(), "");
	}

	@Test
	void testInputDoesNotExist() {
		assertEquals(nonExistLB.toString(), "");
	}

	@Test
	void testAddPlayerAtEnd() {
		lb.addPlayer("matt", 2);
		assertEquals(lb.toString(), "1: melanie - 2048\n2: matt - 2");
	}

	@Test
	void testAddPlayerMiddle() {
		lb.addPlayer("matt", 128);
		lb.addPlayer("sandra", 256);
		assertEquals(lb.toString(), "1: melanie - 2048\n2: sandra - 256\n3: matt - 128");
	}

	@Test
	void testAddPlayerLowerThanTopTen() {
		fullLB.addPlayer("kendall", 32);
		assertEquals(fullLB.toString(),
				"1: melanie - 335\n" + "2: matt - 334\n" + "3: aj - 323\n" + "4: brooke - 245\n" + "5: nate - 244\n"
						+ "6: aidan - 242\n" + "7: sandra - 198\n" + "8: roman - 176\n" + "9: tom - 150\n"
						+ "10: shiv - 45");
	}

	@Test
	void testAddPlayerMiddleOfTop10() {
		fullLB.addPlayer("kendall", 182);
		assertEquals(fullLB.toString(),
				"1: melanie - 335\n" + "2: matt - 334\n" + "3: aj - 323\n" + "4: brooke - 245\n" + "5: nate - 244\n"
						+ "6: aidan - 242\n" + "7: sandra - 198\n" + "8: kendall - 182\n" + "9: roman - 176\n"
						+ "10: tom - 150");
	}
	
	@Test
	void testWriteback() {
		assertEquals(writeLB.toString(), "");
		writeLB.addPlayer("kendall", 182);
		writeLB.writeToFile();
		Leaderboard newLB = new Leaderboard("writeback.csv");
		assertEquals(newLB.toString(), "1: kendall - 182");
	}
	
	@Test
	void testWritingToNoFile() {
		notAFileLB.writeToFile();
	}
	
	
	//Theme Tests
	Theme dark = new Theme("dark,e5b92c,6e0d2d,282522,cdaa8b,FFFFFF");
	//	Theme underwater = new Theme("underwater,b4519e,112248,185178,72c273,FFFFFF,Bubbles.png");
	
	@Test
	void testGetName() {
		assertEquals(light.getName(), "light");
	}
	
	@Test
	void testGetUppercaseName() {
		assertEquals(light.getUppercaseName(), "Light");
	}
	
	@Test
	void testGetBackground() {
		assertEquals(dark.getBackground(), Color.web("282522"));
	}
	
	@Test
	void testGetSecondary() {
		assertEquals(dark.getSecondary(), Color.web("cdaa8b"));
	}
	
	@Test
	void testGetText() {
		assertEquals(dark.getText(), Color.web("FFFFFF"));
	}
	
	
	//Theme Collection Tests
	ThemeCollection myCollection = new ThemeCollection("themes.csv");
	ThemeCollection nonExistCollection = new ThemeCollection("noThemes.csv");

	@Test
	void testNonExistCollections() {
		assertEquals(myCollection.getSelectedTheme().getName(), light.getName());
	}
	
	@Test
	void testGetSelectedTheme() {
		assertEquals(myCollection.getSelectedTheme().getName(), light.getName());
	}
	
	@Test
	void testSetSelectedTheme() {
		myCollection.setSelectedTheme("dark");
		assertEquals(myCollection.getSelectedTheme().getName(), dark.getName());
	}
	
	@Test
	void testSetThemeNotInCollection() {
		myCollection.setSelectedTheme("space");
		assertEquals(myCollection.getSelectedTheme().getName(), light.getName());
	}
	
	@Test
	void testGetCollection() {
		ArrayList<Theme> collectionCopy = myCollection.getThemes();
		assertEquals(collectionCopy.get(0).getName(), light.getName());
		assertEquals(collectionCopy.get(1).getName(), dark.getName());
		assertEquals(collectionCopy.get(2).getName(), "grayscale");
	}
	
	//Test GameController
	
	GameController controller = new GameController();
	
	public void startBoard() {
		controller.setRandomSeed(2048);
		controller.start();
	}
	
	@Test
	void testGetBoardFromController() {
		startBoard();
		String myBoard = controller.getBoard();
		assertEquals(myBoard, "┌---------------┐\n"
				+ "|   |   |   | 2 |\n"
				+ "|---+---+---+---|\n"
				+ "|   | 2 |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "└---------------┘");
	}
	
	@Test
	void testMoveUp() {
		startBoard();
		controller.move(Enums.DIRECTION.UP);
		String myBoard = controller.getBoard();
		assertEquals(myBoard, "┌---------------┐\n"
				+ "|   | 2 | 2 | 2 |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "└---------------┘");
	}
	
	@Test
	void testMoveDown() {
		startBoard();
		controller.move(Enums.DIRECTION.DOWN);
		String myBoard = controller.getBoard();
		assertEquals(myBoard, "┌---------------┐\n"
				+ "|   |   | 2 |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   | 2 |   | 2 |\n"
				+ "└---------------┘");
	}
	
	@Test
	void testMoveLeft() {
		startBoard();
		controller.move(Enums.DIRECTION.LEFT);
		String myBoard = controller.getBoard();
		assertEquals(myBoard, "┌---------------┐\n"
				+ "| 2 |   | 2 |   |\n"
				+ "|---+---+---+---|\n"
				+ "| 2 |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "└---------------┘");
	}
	
	@Test
	void testMoveRight() {
		startBoard();
		controller.move(Enums.DIRECTION.RIGHT);
		String myBoard = controller.getBoard();
		assertEquals(myBoard, "┌---------------┐\n"
				+ "|   |   | 2 | 2 |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   | 2 |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "|---+---+---+---|\n"
				+ "|   |   |   |   |\n"
				+ "└---------------┘");
	}
	
	@Test
	void testGetBoardList() {
		startBoard();
		ArrayList<ArrayList<Tile>> boardList = controller.getBoardList();
		assertEquals(boardList.get(0).get(3).getValue(), 2);
		assertEquals(boardList.get(1).get(1).getValue(), 2);
		
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(!(i == 0 && j == 3 || i == 1 && j == 1)) {
					assertEquals(boardList.get(i).get(j), null);
				}
			}
		}
	}
	
	@Test
	void testGetBoardTheme() {
		startBoard();
		assertEquals(controller.getTheme().getName(), "light");
	}
	

	@Test
	void testSetBoardTheme() {
		controller.setBoardTheme(dark);

		assertEquals(controller.getTheme().getName(), "dark");
	}
	
	
	@Test
	void testGetScore() {
		startBoard();
		assertEquals(controller.getScore(), 0);
	}
	
	@Test
	void testGetHighest() {
		startBoard();
		controller.move(Enums.DIRECTION.RIGHT);
		controller.move(Enums.DIRECTION.UP);

		assertEquals(controller.getHighest(), 4);
	}
	
	@Test
	void testIsNotOver() {
		startBoard();
		assertEquals(controller.isOver(), false);
	}
	
	@Test
	void testIsOverTileCantMove() {
		startBoard();
		for(int i = 0; i < 27; i++) {
			controller.move(Enums.DIRECTION.UP);
			controller.move(Enums.DIRECTION.DOWN);
			controller.move(Enums.DIRECTION.LEFT);
			controller.move(Enums.DIRECTION.RIGHT);
		}
		
		assertEquals(controller.isOver(), true);
	}
	
	@Test
	void testAvailableMovesButStillFullDown() {
		startBoard();
		for(int i = 0; i < 25; i++) {
			controller.move(Enums.DIRECTION.UP);
			controller.move(Enums.DIRECTION.DOWN);
			controller.move(Enums.DIRECTION.LEFT);
			controller.move(Enums.DIRECTION.RIGHT);
		}
		
		controller.move(Enums.DIRECTION.LEFT);
		
		assertEquals(controller.isOver(), false);
	}
	
	@Test
	void testAvailableMovesButStillFullRightAndUp() {
		startBoard();
		for(int i = 0; i < 25; i++) {
			controller.move(Enums.DIRECTION.RIGHT);
			controller.move(Enums.DIRECTION.DOWN);
			controller.move(Enums.DIRECTION.UP);
			controller.move(Enums.DIRECTION.LEFT);
			
		}
		
		controller.move(Enums.DIRECTION.RIGHT);
		
		assertEquals(controller.isOver(), false);
	}
	
	// Board Class Tests
	Board board = new Board();

	@Test
	void testGetBoard() {
		ArrayList<ArrayList<Tile>> emptyBoard = new ArrayList<ArrayList<Tile>>();
		for (int i = 0; i < 4; i++) {
			ArrayList<Tile> row = new ArrayList<Tile>();
			for (int j = 0; j < 4; j++) {
				row.add(null);
			}
			emptyBoard.add(row);
		}
		assertEquals(board.getBoard(), emptyBoard);
	}
}
