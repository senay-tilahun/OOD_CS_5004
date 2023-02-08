import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PawnTest {
  ChessPiece pawn;

  @Before
  public void setUp() throws Exception {
    pawn = new Pawn(2, 3, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, pawn.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, pawn.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, pawn.getColor());
  }

  @Test
  public void testCanMove() {
  }

  @Test
  public void testCanKill() {
  }


}