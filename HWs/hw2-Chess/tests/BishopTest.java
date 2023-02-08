import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BishopTest {

  ChessPiece bishop;

  @Before
  public void setUp() throws Exception {
    bishop = new Bishop(2, 3, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, bishop.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, bishop.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, bishop.getColor());
  }

  @Test
  public void testCanMove() {
  }

  @Test
  public void testCanKill() {
  }

}