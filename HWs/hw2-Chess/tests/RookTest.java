import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RookTest {

  ChessPiece rook1;
  ChessPiece rook2;

  @Before
  public void testSetUp() throws Exception {
    rook1 = new Rook(7, 7, Color.BLACK);
    rook2 = new Rook(0, 0, Color.WHITE);
  }

  @Test
  public void testGetRow() {
    assertEquals(7, rook1.getRow());
    assertEquals(0, rook2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(7, rook1.getColumn());
    assertEquals(0, rook2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, rook1.getColor());
    assertEquals(Color.WHITE, rook2.getColor());
  }

  @Test
  public void testCanMove() {
    // move horizontal
    assertTrue(rook1.canMove(7, 3));
    assertTrue(rook2.canMove(0, 5));
    // move vertical
    assertTrue(rook1.canMove(3, 7));
    assertTrue(rook2.canMove(4, 0));
    // try diagonal
    assertFalse(rook1.canMove(2,2));
    assertFalse(rook2.canMove(4,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(rook1.canMove(8, 0));
    assertFalse(rook2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Rook(0, 3, Color.BLACK);
    ChessPiece temp2 = new Rook(7, 3, Color.WHITE);
    assertTrue(rook1.canKill(temp2));
    assertTrue(rook2.canKill(temp1));
    assertFalse(rook1.canKill(temp1));
    assertFalse(rook2.canKill(temp2));
  }
}