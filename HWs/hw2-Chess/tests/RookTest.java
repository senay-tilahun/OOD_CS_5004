import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RookTest {

  ChessPiece rook;

  @Before
  public void testSetUp() throws Exception {
    rook = new Bishop(2, 3, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, rook.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, rook.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, rook.getColor());
  }

  @Test
  public void testCanMove() {
  }

  @Test
  public void testCanKill() {
  }
}