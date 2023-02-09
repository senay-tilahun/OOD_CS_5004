import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopTest {

  ChessPiece bishop1;
  ChessPiece bishop2;

  @Before
  public void testSetUp() throws Exception {
    bishop1 = new Bishop(2, 3, Color.BLACK);
    bishop2 = new Bishop(0, 0, Color.WHITE);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, bishop1.getRow());
    assertEquals(0, bishop2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, bishop1.getColumn());
    assertEquals(0, bishop2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, bishop1.getColor());
    assertEquals(Color.WHITE, bishop2.getColor());
  }

  @Test
  public void testCanMove() {
// move horizontal
    assertTrue(bishop1.canMove(7, 3));
    assertTrue(bishop2.canMove(0, 5));
    // move vertical
    assertTrue(bishop1.canMove(3, 7));
    assertTrue(bishop2.canMove(4, 0));
    // try diagonal
    assertFalse(bishop1.canMove(2,2));
    assertFalse(bishop2.canMove(4,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(bishop1.canMove(8, 0));
    assertFalse(bishop2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Rook(0, 3, Color.BLACK);
    ChessPiece temp2 = new Rook(7, 3, Color.WHITE);
    assertTrue(bishop1.canKill(temp2));
    assertTrue(bishop2.canKill(temp1));
    assertFalse(bishop1.canKill(temp1));
    assertFalse(bishop2.canKill(temp2));
  }

}