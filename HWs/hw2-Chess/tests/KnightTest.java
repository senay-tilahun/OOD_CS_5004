import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class KnightTest {

  ChessPiece knight;

  @Before
  public void setUp() throws Exception {
    knight = new Knight(2, 3, Color.BLACK);
  }

  @Test
  public void testGetRow() {
    assertEquals(2, knight.getRow());
  }

  @Test
  public void testGetColumn() {
    assertEquals(3, knight.getColumn());
  }

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, knight.getColor());
  }

  @Test
  public void testCanMove() {
  }

  @Test
  public void testCanKill() {
  }
}