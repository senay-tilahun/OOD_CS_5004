import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {
  ChessPiece pawn1;
  ChessPiece pawn2;

  @Before
  public void testSetUp() throws Exception {
    pawn1 = new Pawn(2, 3, Color.BLACK);
    pawn2 = new Pawn(0, 0, Color.WHITE);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, pawn1.getRow());
    assertEquals(0, pawn2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, pawn1.getColumn());
    assertEquals(0, pawn2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, pawn1.getColor());
    assertEquals(Color.WHITE, pawn2.getColor());
  }

  @Test
  public void testCanMove() {
// move horizontal
    assertTrue(pawn1.canMove(7, 3));
    assertTrue(pawn2.canMove(0, 5));
    // move vertical
    assertTrue(pawn1.canMove(3, 7));
    assertTrue(pawn2.canMove(4, 0));
    // try diagonal
    assertFalse(pawn1.canMove(2,2));
    assertFalse(pawn2.canMove(4,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(pawn1.canMove(8, 0));
    assertFalse(pawn2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Rook(0, 3, Color.BLACK);
    ChessPiece temp2 = new Rook(7, 3, Color.WHITE);
    assertTrue(pawn1.canKill(temp2));
    assertTrue(pawn2.canKill(temp1));
    assertFalse(pawn1.canKill(temp1));
    assertFalse(pawn2.canKill(temp2));
  }


}