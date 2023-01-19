import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class PetRockTest {
  // to make testing easier
  private PetRock rocky;

  // time out rule
  @Rule
  public Timeout globalTimeout = Timeout.seconds(10); // 10 sec

   /* can also do "After" to do after our tests run
      can also do "BeforeClass" to only run it once
      for expensive operations - creating DB
   */
  @Before
  public void myTestSetUp() throws Exception {
    rocky = new PetRock("Rocky");
  }

  @Test
  public void getName() {
    // test getter
    assertEquals("Rocky", rocky.getName());
  }

  @Test
  public void testUnhappyToStart() {
    assertFalse(rocky.isHappy());
  }

  @Test
  public void testHappyAfterPlay() {
    rocky.playWithRock();
    assertTrue(rocky.isHappy());
  }

//  @Ignore ("Not implemented yet")
  @Test (expected = IllegalStateException.class)
  public void testNotHappy() throws Exception {
    rocky.getHappyMessage();
  }

  @Test
  public void name() throws Exception {
    rocky.playWithRock();
    String msg = rocky.getHappyMessage();
    assertEquals("I'm happy", msg);
  }

  @Test
  public void testFavNum() {
    assertEquals(42, rocky.getFavNumber());
  }

  @Test (expected = IllegalArgumentException.class)
  public void emptyNameFail() {
    PetRock woof = new PetRock("");
  }

  @Test (expected = ArithmeticException.class)
  public void emptyWeightFail() {
    PetRock woof = new PetRock("rock", 0);
  }

  @Test
  public void testTOString() {
    assertEquals("Rocky the PetRock!" ,rocky.toString());
  }
  //  @Test (timeout = 100) // in ms
//  public void waitForHappyTimeOut() {
//    rocky.waitTillHappy();
//  }
}