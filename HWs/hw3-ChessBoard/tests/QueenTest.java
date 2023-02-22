import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueenTest {

  ChessPiece queen1;
  ChessPiece queen2;

  /**
   * This method setups the rest of the test by instantiating two Queen ChessPieces
   */
  @Before
  public void testSetUp() throws Exception {
    queen1 = new Queen(2, 3, Color.BLACK);
    queen2 = new Queen(0, 3, Color.WHITE);
  }

  /**
   * Test to confirm the getRow method returns the correct value on both ChessPieces
   */
  @Test
  public void testGetRow() {
    assertEquals(2, queen1.getRow());
    assertEquals(0, queen2.getRow());
  }

  /**
   * Test to confirm the getColumn() method returns the correct value on both ChessPieces
   */
  @Test
  public void testGetColumn() {
    assertEquals(3, queen1.getColumn());
    assertEquals(3, queen2.getColumn());
  }

  /**
   * Test to confirm the getColor() method returns the correct value on both ChessPieces
   */
  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, queen1.getColor());
    assertEquals(Color.WHITE, queen2.getColor());
  }

  /**
   * Test to confirm the getCanMove() method returns the correct value on both ChessPieces
   */
  @Test
  public void testCanMove() {
// move horizontal
    assertTrue(queen1.canMove(2, 3));
    assertTrue(queen2.canMove(0, 5));
    // move vertical
    assertTrue(queen1.canMove(3, 3));
    assertTrue(queen2.canMove(4, 3));
    //try diagonal
    assertTrue(queen1.canMove(4, 5));
    assertTrue(queen2.canMove(2, 1));

    // try can't move
    assertFalse(queen1.canMove(3,0));
    assertFalse(queen2.canMove(4,1));
  }

  /**
   * Test out-of-bounds exception on canMove
   */
  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(queen1.canMove(8, 0));
    assertFalse(queen2.canMove(4, -1));
  }

  /**
   * Test canKill method
   */
  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Queen(4, 1, Color.BLACK);
    ChessPiece temp11 = new Bishop(4, 1, Color.WHITE);
    ChessPiece temp2 = new Knight(3, 6, Color.WHITE);
    ChessPiece temp22 = new Rook(3, 6, Color.BLACK);
    assertTrue(queen1.canKill(temp11));
    assertTrue(queen2.canKill(temp22));
    assertFalse(queen1.canKill(temp1));
    assertFalse(queen2.canKill(temp2));
  }
}