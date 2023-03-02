import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit4 test Class for Magazine
 */
public class MagazineTest {
  private Person pat;
  private Person john;
  private Magazine mag1;
  private Magazine mag2;
  private Magazine mag3;

  /**
   * Set up examples of  Persons and Magazines to use in testing
   */
  @Before
  public void setUp() {
    pat = new Person("Pat", "Conroy", 1948);
    john = new Person("John", "Steinbeck", 1902);
    mag1 = new Magazine("Beaches Issue", pat, 2020,
        Month.MARCH, 5.0);
    mag2 = new Magazine("Sunsets Issue", pat, 2021,
        Month.SEPTEMBER, 2.50);
    mag3 = new Magazine("The Grapes of July", john, 1939,
        Month.JULY, 2.00);
  }

  // FLAW: No unittest to verify that the getMonth method in Magazine class is working
  // FIX: add unittest with java docs to verify that

  /**
   * Verify that getMonth returns Magazine's month as constructed
   */
  @Test
  public void getMonth() {
    assertEquals(Month.MARCH, mag1.getMonth());
    assertEquals(Month.SEPTEMBER, mag2.getMonth());
    assertNotEquals(Month.JANUARY, mag3.getMonth());
  }

  // FLAW: No java docs for the toString test method
  // FIX: add useful java docs
  // FIXED
  /**
   * Verify that toString returns expected value of "firstName lastName"
   */
  @Test
  public void testToString() {
    String expected;
    expected = """
        Kind: Magazine
        Title: Beaches Issue
        Author: Pat Conroy
        Year: 2020
        Month: MARCH
        Price: 5.00""";
    assertEquals(expected, mag1.toString());
    assertNotEquals(expected, mag2.toString());
  }

  // FLAW: No unittest to verify that the equals method in Magazine class is working
  // FIX: add unittest with java docs to verify that

  /**
   * Verify that overridden equals method works as expected, depending on key fields
   * Creates several additional local Magazines to supplement those defined above
   */
  @Test
  public void testEquals() {
    Magazine mag4 = new Magazine("Beaches Issue", pat, 2020,
        Month.MARCH, 5.000001);
    Magazine mag5 = new Magazine("Sunsets Issue 2", pat, 2021,
        Month.SEPTEMBER, 2.50);

    // test price difference within 0.001 works
    assertEquals(mag1, mag4);
    // test different titles does not work
    assertNotEquals(mag2, mag5);
  }


  // FLAW: No unittest to verify that the hashCode method in Magazine class is working
  // FIX: add unittest with java docs to verify that

  /**
   * Verify that overridden hashCode returns same int for equal Magazine
   * Important to honor contract between equals and hashCode!
   */
  @Test
  public void testHashCode() {
    assertEquals(mag1, mag1);
    assertEquals(mag2, mag2);
    assertEquals(mag3, mag3);
  }

}