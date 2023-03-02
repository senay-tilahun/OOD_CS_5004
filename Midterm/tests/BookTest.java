import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit 4 test Class for Book
 * <p>
 * Methods shared by Publications are tested in AbstractPublicationTest
 * Methods with unique Book implementations are tested here
 */
public class BookTest {
  private Person pat;
  private Person john;
  private Book book1;
  private Book book2;
  private Book book3;

  /**
   * Set up some examples of Person and Book for testing
   */
  @Before
  public void setUp() {
    // Examples of Person
    pat = new Person("Pat", "Conroy", 1948);
    john = new Person("John", "Steinbeck", 1902);
    // Examples of Book
    book1 = new Book ("Beaches", pat, 2020, 5.0);
    book2 = new Book("Sunsets", pat, 2021, 25.00);
    book3 = new Book("The Grapes of Wrath", john, 1939,  50.00 );
  }

  // FLAW: testBookString method does not have any Java docs
  // FIX: add useful java docs explaining what this test is doing
  // FIXED
  /**
   * Verify that toString returns expected value - for example """
   *         Kind: Book
   *         Title: Beaches
   *         Author: Pat Conroy
   *         Year: 2020
   *         Price: 5.00"""
   */
  @Test
  public void testBookString() {
    String expected;
    expected = """
        Kind: Book
        Title: Beaches
        Author: Pat Conroy
        Year: 2020
        Price: 5.00""";
    // FLAW: the price is not a double and is not equal to the price of book 1
    // FIX: change price to 5.00
    // FIXED
    assertEquals(expected,book1.toString());
  }

  // FLAW: we don't have test to verify that overridden equals method works as expected
  // FIX: add test and verify overridden equals method
  //

  /**
   * Verify that overridden equals method works as expected, depending on key fields
   * Creates several additional local Books to supplement those defined above
   */
  @Test
  public void testEquals() {
    Book book4 = new Book ("Beaches", pat, 2020, 15.0);
    Book book5 = new Book("Sunsets", pat, 2222, 25.00);

    // test price doesn't matter in equality
    assertEquals(book1, book4);
    // test year matters in equality
    assertNotEquals(book5, book2);
  }

  // FLAW: we don't have test to verify that overridden equals method works as expected
  // FIX: add test and verify overridden equals method
  //

  /**
   * Verify that overridden hashCode returns same int for equal Book
   * Important to honor contract between equals and hashCode!
   */
  @Test
  public void testHashCode() {
    assertEquals(book1, book1);
    assertEquals(book2, book2);
    assertEquals(book3, book3);
  }
}
