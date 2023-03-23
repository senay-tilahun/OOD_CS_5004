import static org.junit.Assert.*;

import java.util.EmptyStackException;
import org.junit.Before;
import org.junit.Test;

public class MyStackImpTest {

  MyStackImp<Integer> intStack;

  MyStackImp<String> strStack;

  MyStackImp<Double> dblStack;

  @Before
  public void testSetUp() throws Exception {
    intStack = new MyStackImp<>();
    strStack = new MyStackImp<>();
    dblStack = new MyStackImp<>();

    intStack.push(1);
    intStack.push(2);
    intStack.push(3);
    intStack.push(4);
    strStack.push("one");
    dblStack.push(1.0);

  }

  @Test
  public void testPush() {
    // also tests getMyStack method
    assertEquals(4, intStack.getMyStack().size());
    assertEquals(1, strStack.getMyStack().size());
    assertEquals(1, dblStack.getMyStack().size());
  }

  @Test
  public void testPop() {
    // also tests getMyStack method
    int act = intStack.pop();
    int act1 = intStack.pop();

    assertEquals(4, act);
    assertEquals(3, act1);
    assertEquals(2, intStack.getMyStack().size());
  }

  @Test (expected = EmptyStackException.class)
  public void testPopException() {
    strStack.pop();
    strStack.pop();
  }

  @Test (expected = EmptyStackException.class)
  public void testTopException() {
    dblStack.pop();
    dblStack.top();
  }

  @Test
  public void testTop() {

    int actual1 = intStack.top();
    String actual2 = strStack.top();
    Double actual3 = dblStack.top();

    assertEquals(4, actual1);
    assertEquals("one", actual2);
    assertEquals(1.0, actual3, 0.01);
  }

  @Test
  public void testEmpty() {
    strStack.pop();
    dblStack.pop();
    intStack.pop();

    assertTrue(strStack.empty());
    assertTrue(dblStack.empty());
    assertFalse(intStack.empty());
  }


  @Test
  public void testToString() {
    String one = "Stack: 1 2 3 4";
    String two = "Stack: one";
    String three = "Stack: 1.0";

    assertEquals(one, intStack.toString());
    assertEquals(two, strStack.toString());
    assertEquals(three, dblStack.toString());
  }
}