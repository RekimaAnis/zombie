package test.projet;

import org.junit.jupiter.api.*;
import projet.Board;
import projet.celulle.Piece;

import static org.junit.Assert.assertEquals;

class BoardTest {
	//Test le constructeur
	@Test
	void testBoard() {
		Board board = new Board(15,15);
		assertEquals(board.getLigne(),15);
		assertEquals(board.getColonne(),15);
	}

	@Test
	void testInitboard() {
		Board board = new Board(15,15);
		for (int i = 0; i < board.getLigne(); i++) {
			for (int j = 0; j < board.getColonne(); j++) {
				Piece piece= new Piece(i,j);
				//assertEquals(board.getBoard()[i][j].getWidth(),piece.getWidth());
				//assertEquals(board.getBoard()[i][j].getHeight(),piece.getHeight());
			}
		}
	}



}
