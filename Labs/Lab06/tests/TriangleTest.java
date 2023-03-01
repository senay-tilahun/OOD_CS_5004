import static org.junit.Assert.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class TriangleTest {


  Triangle t1;

  /**
   * Method to set up the triangle  class test
   * @throws Exception
   */
  @Before
  public void testSetUp() throws Exception {
    t1 = new Triangle(new Point2D(-2, 3),
        new Point2D(-3, -1), new Point2D(3, -2));
  }

  /**
   * Method to test Area method of triangle
   */
  @Test
  public void testArea() {
    assertEquals(12.5, t1.area(), 0.01);
  }

  /**
   * Method to test Perimeter method of triangle
   */
  @Test
  public void testPerimeter() {
    assertEquals(17.27, t1.perimeter(), 0.1);
  }

  /**
   * Method to test Resize method of triangle
   */
  @Test
  public void testResize() {
    Triangle t2 = (Triangle) t1.resize(2);
    assertEquals(-4, t2.getRefX(), 0.01);
  }

}