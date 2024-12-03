/*
 * Commented out because VSCode hates JavaFX and JUnit together :)
 */


//  package com.finalproject;

//  import static org.junit.jupiter.api.Assertions.*;
 
//  import java.util.ArrayList;
 
//  import org.junit.jupiter.api.Test;
 
//  class GameTests {
     
//      //Tile Class Tests
//      Tile t2 = new Tile(2);
//      Tile t4 = new Tile(4);
     
//      @Test
//      void testGetValue() {
//          assertEquals(t2.getValue(), 2);
//      }
     
//      @Test
//      void testGetColor() {
//          assertEquals(t2.getColor(), Enums.COLOR.EX1);
//          assertEquals(t4.getColor(), Enums.COLOR.EX2);
//      }
     
//      @Test
//      void testCombine() {
//          Tile combinedT4 = t2.combine(t2);
//          assertEquals(combinedT4.getValue(), 4);
//      }
     
//      @Test
//      void testCombine_nonEqualTiles() {
//          Tile combinedT = t2.combine(t4);
//          assertNull(combinedT);
//      }
     
//      //Board Class Tests
//      Board board = new Board();
     
//      @Test
//      void testGetBoard() {
//          ArrayList<ArrayList<Tile>> emptyBoard = new ArrayList<ArrayList<Tile>>();
//          for(int i = 0; i < 4; i++) {
//              ArrayList<Tile> row = new ArrayList<Tile>();
//              for(int j = 0; j < 4; j++) {
//                  row.add(null);
//              }
//              emptyBoard.add(row);
//          }
//          assertEquals(board.getBoard(), emptyBoard);
//      }
 
//      @Test
//      void testMoveUp() {
//          board.move(Enums.DIRECTION.UP);
//      }
     
//      @Test
//      void testMoveDown() {
//          board.move(Enums.DIRECTION.DOWN);
//      }
     
//      @Test
//      void testMoveLeft() {
//          board.move(Enums.DIRECTION.LEFT);
//      }
     
//      @Test
//      void testMoveRight() {
//          board.move(Enums.DIRECTION.RIGHT);
//      }
     
     
//      // Leaderboard Tests
//      Leaderboard lb = new Leaderboard("leaderboard.csv");
//      Leaderboard badLB = new Leaderboard("badScore.csv");
//      Leaderboard emptyLB = new Leaderboard("empty.csv");
//      Leaderboard nonExistLB = new Leaderboard("doesnotexist.csv");
//      Leaderboard fullLB = new Leaderboard("fullLeaderboard.csv");
     
//      @Test
//      void testLeaderboardCreatePartiallyFull() {
//          assertEquals(lb.toString(), "1: melanie - 2048");
//      }
     
//      @Test
//      void testBadScoreInput() {
//          assertTrue(badLB.getLeaderboard().isEmpty());
//      }
 
//      @Test
//      void testEmptyInput() {
//          assertTrue(emptyLB.getLeaderboard().isEmpty());
//      }
     
//      @Test
//      void testInputDoesNotExist() {
//          assertTrue(nonExistLB.getLeaderboard().isEmpty());
//      }
     
//      @Test
//      void testAddPlayerAtEnd() {
//          lb.addPlayer("matt", 2);
//          assertEquals(lb.toString(), "1: melanie - 2048\n2: matt - 2");
//      }
     
//      @Test
//      void testAddPlayerMiddle() {
//          lb.addPlayer("matt", 128);
//          lb.addPlayer("sandra", 256);
//          assertEquals(lb.toString(), "1: melanie - 2048\n2: sandra - 256\n3: matt - 128");
//      }
     
//      @Test
//      void testAddPlayerLowerThanTopTen() {
//          fullLB.addPlayer("kendall", 32);
//          assertEquals(fullLB.toString(), "1: melanie - 335\n"
//                  + "2: matt - 334\n"
//                  + "3: aj - 323\n"
//                  + "4: brooke - 245\n"
//                  + "5: nate - 244\n"
//                  + "6: aidan - 242\n"
//                  + "7: sandra - 198\n"
//                  + "8: roman - 176\n"
//                  + "9: tom - 150\n"
//                  + "10: shiv - 45");
//      }
     
//      @Test
//      void testAddPlayerMiddleOfTop10() {
//          fullLB.addPlayer("kendall", 182);
//          assertEquals(fullLB.toString(), "1: melanie - 335\n"
//                  + "2: matt - 334\n"
//                  + "3: aj - 323\n"
//                  + "4: brooke - 245\n"
//                  + "5: nate - 244\n"
//                  + "6: aidan - 242\n"
//                  + "7: sandra - 198\n"
//                  + "8: kendall - 182\n"
//                  + "9: roman - 176\n"
//                  + "10: tom - 150");
//      }
//  }
 