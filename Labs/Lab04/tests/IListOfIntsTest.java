import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IListOfIntsTest {

  IListOfInts listOfInts, partialListOfInts, emptyListOfInts;

  @Before
  public void testSetUp() throws Exception {
    emptyListOfInts = new EmptyNode();

    partialListOfInts = new ElementNode(1, new ElementNode(2, new ElementNode(3,
        new EmptyNode())));

    listOfInts = new ElementNode(4, new ElementNode(5, new ElementNode(6, partialListOfInts)));
  }

  @Test
  public void testAddAtIndex() {
    listOfInts.addAtIndex(10, 2);
    assertEquals(10,listOfInts.getDataAtIndex(2));
    partialListOfInts.addAtIndex(23,3);
    assertEquals(23, partialListOfInts.getDataAtIndex(3));
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void testAddAtIndexError() {
    listOfInts.addAtIndex(12, 12);
  }

  @Test
  public void testAddFront() {
    emptyListOfInts = emptyListOfInts.addFront(7);
    partialListOfInts = partialListOfInts.addFront(8);
    assertEquals(2, listOfInts.getDataAtIndex(4));
    listOfInts = listOfInts.addFront(9);
    System.out.println(emptyListOfInts.toString());
    assertEquals(7, emptyListOfInts.getDataAtIndex(0));
    assertEquals(8, partialListOfInts.getDataAtIndex(0));
    assertEquals(1, listOfInts.getDataAtIndex(4));
    assertEquals(9, listOfInts.getDataAtIndex(0));
  }

  @Test
  public void testAddBack() {
    emptyListOfInts = emptyListOfInts.addBack(7);
    assertEquals(7, emptyListOfInts.getDataAtIndex(0));
    partialListOfInts.addBack(8);
    assertEquals(8, partialListOfInts.getDataAtIndex(3));
  }

  @Test
  public void testGetDataAtIndex() {
    assertEquals(1, partialListOfInts.getDataAtIndex(0));
    assertEquals(4, listOfInts.getDataAtIndex(0));
    assertEquals(3, partialListOfInts.getDataAtIndex(2));
    assertEquals(6, listOfInts.getDataAtIndex(2));
  }

  @Test
  public void testCount() {
    assertEquals(0, emptyListOfInts.count());
    assertEquals(3, partialListOfInts.count());
    assertEquals(6, listOfInts.count());
  }

  @Test
  public void testSum() {
    assertEquals(0, emptyListOfInts.sum());
    assertEquals(6, partialListOfInts.sum());
    assertEquals(21, listOfInts.sum());
  }

  @Test
  public void testToString() {
    String expected1 = "1, 2, 3";
    String expected2 = "4, 5, 6, 1, 2, 3";
    String expected3 = "";
    assertEquals(expected1, partialListOfInts.toString());
    assertEquals(expected2, listOfInts.toString());
    assertEquals(expected3, emptyListOfInts.toString());
  }
}