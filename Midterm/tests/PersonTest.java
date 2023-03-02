import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit4 test Class for the Person class
 */
public class PersonTest {
  private Person john;
  private Person benLerner;
  // FLAW: Poor style, such as ignoring camelCase
  // FIX: refactor all instances of this name in this file to match good java style
  // FIXED
  private Person identicalTwin;
  // FLAW: Poor style, such as ignoring camelCase
  // FIX: refactor all instances of this name in this file to match good java style
  // FIXED

  /**
   *  Set up a few Persons to reuse in multiple tests
   */
  @Before
  public void setUp() {
    john = new Person("John", "Doe",1945);
    benLerner = new Person("Ben","Lerner",1982);
    identicalTwin = new Person("Ben","Lerner",1982);
  }

  /**
   * Verify that getFirstName returns value assigned by constructor
   */
  @Test
  public void testGetFirstName() {
    assertEquals("John", john.getFirstName());
    assertEquals("Ben", benLerner.getFirstName());
    assertNotEquals("Ben", john.getFirstName());
  }

  /**
   * Verify that getLastName returns value assigned by constructor
   */
  @Test
  public void getLastName() {
    assertEquals("Doe", john.getLastName());
    assertEquals("Lerner", benLerner.getLastName());
    assertNotEquals("Lerner", john.getLastName());
  }

  /**
   * Verify that getYearOfBirth returns value assigned by constructor
   */
  @Test
  public void testGetYearOfBirth() {
    assertEquals(1945, john.getYearOfBirth());
    assertEquals(1982, benLerner.getYearOfBirth());
    assertNotEquals(1945, identicalTwin.getYearOfBirth());
  }

  /**
   * Verify that toString returns expected value of "firstName lastName"
   */
  @Test
  public void testToString() {
    assertEquals("John Doe", john.toString());
    assertEquals("Ben Lerner", benLerner.toString());
    assertNotEquals("John Doh!", john.toString());
  }

  /**
   * Verify that overridden equals method works as expected, depending on key fields
   * Creates several additional local Persons to supplement those defined above
   */
  // FLAW: 3 flaws found in this Equals method, the new Person variable names do not
  //        have good style
  // FIX: make camelCase
  // FIXED
  @Test
  public void testEquals() {
    Person benAffleck = new Person("Ben","Affleck",1982);
    Person timLerner = new Person("Tim","Lerner",1982);
    Person anotherBenLerner = new Person("Ben","Lerner",1983);
    assertNotEquals(benLerner, benAffleck);
    assertNotEquals(benLerner, timLerner);
    assertNotEquals(benLerner, anotherBenLerner);
    assertEquals(benLerner, identicalTwin);
  }

  /**
   * Verify that overridden hashCode returns same int for equal Persons
   * Important to honor contract between equals and hashCode!
   */
  @Test
  public void testHashCode() {
    // NB: Two different Persons might hash to the same integer.
    // Hence, no negative tests, despite this be
    assertEquals(benLerner, identicalTwin);
    assertEquals(benLerner, benLerner);
  }
}
