import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class QueenTest {

  ChessPiece queen;

  @Before
  public void testSetUp() throws Exception {
    queen = new Bishop(2, 3, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, queen.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, queen.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, queen.getColor());
  }

  @Test
  public void testCanMove() {

  }

  @Test
  public void testCanKill() {
  }
}