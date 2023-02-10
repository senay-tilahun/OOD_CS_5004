import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopTest {

  ChessPiece bishop1;
  ChessPiece bishop2;

  @Before
  public void testSetUp() throws Exception {
    bishop1 = new Bishop(2, 3, Color.BLACK);
    bishop2 = new Bishop(0, 3, Color.WHITE);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, bishop1.getRow());
    assertEquals(0, bishop2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, bishop1.getColumn());
    assertEquals(3, bishop2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, bishop1.getColor());
    assertEquals(Color.WHITE, bishop2.getColor());
  }

  @Test
  public void testCanMove() {
  //try diagonal
    assertTrue(bishop1.canMove(4, 5));
    assertTrue(bishop2.canMove(2, 1));

    // try can't move
    assertFalse(bishop1.canMove(3,0));
    assertFalse(bishop2.canMove(4,1));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(bishop1.canMove(8, 0));
    assertFalse(bishop2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Queen(4, 1, Color.BLACK);
    ChessPiece temp11 = new Bishop(4, 1, Color.WHITE);
    ChessPiece temp2 = new Knight(3, 6, Color.WHITE);
    ChessPiece temp22 = new Rook(3, 6, Color.BLACK);
    assertTrue(bishop1.canKill(temp11));
    assertTrue(bishop2.canKill(temp22));
    assertFalse(bishop1.canKill(temp1));
    assertFalse(bishop2.canKill(temp2));
  }

}