import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 Class to test our Library of Publications Class
 */
public class LibraryTest {
  Library myLibrary;
  Publication book0, mag1, book2, book3;
  Person ben, john, seymour;

  /**
   * Create new Library with various Publications/authors, for testing
   */
  @Before
  public void setUp(){
    myLibrary = new Library();
    john = new Person("John", "Steinbeck",1902);
    ben = new Person("Ben","Lerner",1982);
    seymour  = new Person("Seymour", "Papert", 1928);

    book0 = new Book ("Beaches", ben, 2020, 5.0);
    mag1 = new Magazine("Sunsets", ben, 2021, Month.NOVEMBER, 25.00);
    book2 = new Book("The Grapes of Wrath", john, 1939,  50.00 );
    book3 = new Book("Mindstorms", seymour, 1980, 10.00);

    myLibrary  = new Library();
    myLibrary.addToLibrary(book0);
    myLibrary.addToLibrary(mag1);
    myLibrary.addToLibrary(book2);
    myLibrary.addToLibrary(book3);
  }

  /**
   * Test addToLibrary, getFromLibrary, libraryContains -- 3 related methods
   * <p>
   * Verifies adding publications to the library
   * More than one copy of a given publication may be added
   * Testing that these 3 methods work together consistently
   * Next few tests are closely related and use above sample data
   */
  @Test
  public void testAddToLibrary() {
    assertEquals(book3, myLibrary.getFromLibrary(3));
    // book0 was the first one added
    assertEquals(book0, myLibrary.getFromLibrary(0));
    myLibrary.addToLibrary(book0); // now also at index 4
    assertEquals(book0, myLibrary.getFromLibrary(4));
    assertTrue(myLibrary.libraryContains(mag1));
  }

  /**
   * Test getFromLibrary with Index out of bounds exception
   * <p>
   * Verifying that these 3 methods work together consistently
   * Tests are closely related and share above sample data
   */
  @Test(expected = IndexOutOfBoundsException.class)
  public void testGetFromLibraryException() {
    // Data above involved fewer than 6 publications in the library
    myLibrary.getFromLibrary(10);
  }

  /**
   * Test getFromLibrary with valid index
   * <p>
   * Also verifying that the 3 methods work together consistently
   */
  @Test
  public void testGetFromLibrary() {
    // Additional tests in related methods
    assertEquals(book2, myLibrary.getFromLibrary(2));
  }

  /**
   * Test libraryContains method
   * <p>
   * Also ensuring behavior is consistent with above 2 methods
   */
  @Test
  public void testLibraryContains() {
    assertTrue(myLibrary.libraryContains(book2));
    Person nick = new Person("Nicholas", "Negroponte", 1943);
    Publication mag2 = new Magazine("Wired", nick, 2023, Month.JANUARY, 2.00);
    assertFalse(myLibrary.libraryContains(mag2));
  }

  /**
   * Test toString method for libraries using sample data defined above
   *
   * <p>
   * First print that it is a Library. Then,
   * print all publications, separated by 5 dashes on separate line
   */
  @Test
  public void testToString() {
    String expected = "Library:"
        + "\n-----\n" + book0.toString() +
          "\n-----\n" + mag1.toString() +
          "\n-----\n" + book2.toString() +
          "\n-----\n" + book3.toString();
    assertEquals(expected, myLibrary.toString());
  }

  /**
   * Test sortLibrary method for libraries using sample data defined above
   * <p>
   * Note that the sorted Library order is different from original ordering
   */

  // FLAW: this unittest is not testing the sortLibrary method, but the toString method
  // FIX: update the test to test and verify the sortLibrary method of Library class

  @Test
  public void testSortLibrary() {
    myLibrary.sortLibrary(new SortByAuthor());
    // check the sorting order is correct
    // sorting order should [book0, mag1, book3, book2]
    assertEquals(book0, myLibrary.getFromLibrary(0));
    assertEquals(mag1, myLibrary.getFromLibrary(1));
    assertNotEquals(book2, myLibrary.getFromLibrary(2));
    assertNotEquals(book3, myLibrary.getFromLibrary(3));
   }
}
