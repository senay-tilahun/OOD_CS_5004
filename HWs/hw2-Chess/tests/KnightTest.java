import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KnightTest {

  ChessPiece knight1;
  ChessPiece knight2;

  @Before
  public void testSetUp() throws Exception {
    knight1 = new Knight(2, 3, Color.BLACK);
    knight2 = new Knight(0, 0, Color.WHITE);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, knight1.getRow());
    assertEquals(0, knight2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, knight1.getColumn());
    assertEquals(0, knight2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, knight1.getColor());
    assertEquals(Color.WHITE, knight2.getColor());
  }

  @Test
  public void testCanMove() {
// move horizontal
    assertTrue(knight1.canMove(3, 1));
    assertTrue(knight2.canMove(2, 1));

    // try diagonal
    assertFalse(knight1.canMove(2,2));
    assertFalse(knight2.canMove(4,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(knight1.canMove(8, 0));
    assertFalse(knight2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Queen(3, 1, Color.BLACK);
    ChessPiece temp2 = new Knight(2, 1, Color.WHITE);
    ChessPiece temp3 = new Pawn(3, 1, Color.WHITE);
    ChessPiece temp4 = new Bishop(2, 1, Color.BLACK);
    assertTrue(knight1.canKill(temp3));
    assertTrue(knight2.canKill(temp4));
    assertFalse(knight1.canKill(temp1));
    assertFalse(knight2.canKill(temp2));
  }
}