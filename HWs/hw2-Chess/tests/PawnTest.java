import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {
  ChessPiece pawn1;
  ChessPiece pawn2;

  @Before
  public void testSetUp() throws Exception {
    pawn1 = new Pawn(1, 2, Color.WHITE);
    pawn2 = new Pawn(6, 6, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(1, pawn1.getRow());
    assertEquals(6, pawn2.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(2, pawn1.getColumn());
    assertEquals(6, pawn2.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.WHITE, pawn1.getColor());
    assertEquals(Color.BLACK, pawn2.getColor());
  }

  @Test
  public void testCanMove() {
    // move vertical
    assertTrue(pawn1.canMove(2, 2));
    assertTrue(pawn2.canMove(5, 6));
    // can not move
    assertFalse(pawn1.canMove(5,2));
    assertFalse(pawn2.canMove(5,4));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testCanMoveException() {
    assertFalse(pawn1.canMove(8, 0));
    assertFalse(pawn2.canMove(4, -1));
  }

  @Test
  public void testCanKill() {
    ChessPiece temp1 = new Pawn(2, 3, Color.BLACK);
    ChessPiece temp2 = new Rook(2, 1, Color.WHITE);
    ChessPiece temp3 = new Pawn(5, 7, Color.BLACK);
    ChessPiece temp4 = new Rook(5, 5, Color.WHITE);
    // can move and different
    assertTrue(pawn1.canKill(temp1));
    assertTrue(pawn2.canKill(temp4));
    // can move but same color
    assertFalse(pawn1.canKill(temp2));
    assertFalse(pawn2.canKill(temp3));
  }


}