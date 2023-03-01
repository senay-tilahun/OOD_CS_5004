import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PerimComparatorTest {

  Triangle t1;

  @Before
  public void testSetUp() throws Exception {
    t1 = new Triangle(new Point2D(-2, 3),
        new Point2D(-3, -1), new Point2D(3, -2));

  }

  @Test
  public void testCompare() {
    Triangle t2 = (Triangle) t1.resize(2);
    PerimComparator p = new PerimComparator();

    assertEquals(1, p.compare(t2, t1));
    assertEquals(-1, p.compare(t1, t2));
  }
}