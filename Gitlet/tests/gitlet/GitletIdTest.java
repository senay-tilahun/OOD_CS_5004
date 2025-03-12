package gitlet;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to Junit test our GitletId class
 */
public class GitletIdTest {
  GitletId gitletId1;
  GitletId gitletId2;
  GitletId gitletId3;
  GitletId gitletId4;

  GitletId gitletId5;

  /**
   * Method to set up rest of test
   */
  @Before
  public void testSetUp() {
    gitletId1 = new GitletId("1", "oneID");
    gitletId2 = new GitletId("2", "twoID");
    gitletId3 = new GitletId("3", "threeID");
    gitletId4 = new GitletId("4", "fourID");
    gitletId5 = new GitletId("1", "oneID");
  }

  /**
   * Method to test the getId method
   */
  @Test
  public void testGetId() {
    assertEquals("1", gitletId1.getId());
    assertEquals("3", gitletId3.getId());
    assertNotEquals("2.0", gitletId2.getId());
    assertNotEquals("4.0", gitletId4.getId());
  }

  /**
   * Method to test the getName method
   */
  @Test
  public void testGetName() {
    assertEquals("oneID", gitletId1.getName());
    assertEquals("threeID", gitletId3.getName());
    assertNotEquals("2.0", gitletId2.getName());
    assertNotEquals("4.0", gitletId4.getName());
  }

  /**
   * Method to test the hashcode method
   */
  @Test
  public void testHashCode() {
    assertEquals(gitletId5.hashCode(), gitletId1.hashCode());
    assertNotEquals(gitletId1.hashCode(), gitletId2.hashCode());
  }

  /**
   * Method to test the equals method
   */
  @Test
  public void testEquals() {
    assertEquals(gitletId2, gitletId2);
    assertEquals(gitletId1, gitletId5);
    assertNotEquals(gitletId2, gitletId1);
  }
}