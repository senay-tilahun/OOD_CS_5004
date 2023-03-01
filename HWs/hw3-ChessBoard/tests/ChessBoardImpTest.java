import static org.junit.Assert.*;
import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class ChessBoardImpTest {

  // declare two Boards
  ChessBoardImp board1;
  ChessBoardImp board2;

  /**
   * Set up rest of unittest
   * Instantiate two Boards
   * @throws Exception general
   */
  @Before
  public void setUp() throws Exception {
    board1 = new ChessBoardImp();
    board2 = new ChessBoardImp();
  }

  /**
   * Method to test the getChessPieceAt() method of ChessBoard class
   * Adds two chesspieces to board 1 and 2
   * Confirms the row/col/color are as expected
   */
  @Test
  public void getChessPieceAt() {
    ChessPiece queen = new Queen(0, 4, Color.WHITE);
    ChessPiece rook = new Rook(7, 0, Color.BLACK);
    // add to board
    board1.getBoard().get(0).add(4, queen);
    board2.getBoard().get(7).add(0, rook);

    assertEquals(0, board1.getChessPieceAt(0,4).getRow());
    assertEquals(4, board1.getChessPieceAt(0,4).getColumn());
    assertEquals(Color.WHITE, board1.getChessPieceAt(0,4).getColor());

    //
    assertEquals(7, board2.getChessPieceAt(7,0).getRow());
    assertEquals(0, board2.getChessPieceAt(7,0).getColumn());
    assertEquals(Color.BLACK, board2.getChessPieceAt(7,0).getColor());

  }

  /**
   * Method to test the moveChessPiece() method of ChessBoard class
   * Adds two chess pieces to board 1 and 2
   * Moves and confirms move
   */
  @Test
  public void moveChessPiece() {
    ChessPiece queen = new Queen(0, 4, Color.WHITE);
    ChessPiece rook = new Rook(7, 0, Color.BLACK);
    // add to board
    board1.getBoard().get(0).add(4, queen);
    board2.getBoard().get(7).add(0, rook);

    board1.moveChessPiece(0, 5, 4, 4);
    assertEquals(5, board1.getChessPieceAt(5,4).getRow());
    assertEquals(4, board1.getChessPieceAt(5,4).getColumn());

  }
}