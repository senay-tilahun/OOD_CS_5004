package gitlet;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to JUnit test out Valid class
 */
public class ValidTest {
  String[] init;
  String[] init2;
  String[] add;
  String[] add2;

  /**
   * Method to set up the rest of the test
   * @throws Exception i/o
   */
  @Before
  public void testSetUp() throws Exception {
    init = new String[]{"init"};
    init2 = new String[]{"init", "file.txt"};
    add = new String[]{"add", "file.txt", "third"};
    add2 = new String[]{"add", "file.txt"};
  }

  /**
   * Method to test valid number of user arguments
   */
  @Test
  public void testValidateUserArgNum() {
    // Test cases with correct number of args
    Valid.validateUserArgNum(init, 1, "init");
    Valid.validateUserArgNum(add2, 2, "add");

  }

  /**
   * Method to test invalid number of user arguments
   * @throws RuntimeException for inlaid args
   */
  @Test (expected = RuntimeException.class)
  public void testInValidateUserArgNum() throws RuntimeException {
   // Test cases with incorrect number of args
    Valid.validateUserArgNum(init2, 1, "init");
    fail("Expected RuntimeException");

  }

  /**
   * Method to test if repo already exists
   */
  @Test
  public void testRepoAlreadyExists() {
    // Test case where repo don't exist
    assertTrue(Paths.get(".gitlet").toFile().exists());
    Valid.repoAlreadyExists();
  }

  /**
   * Method to test if file already exists
   * @throws IOException if exists
   */
  @Test
  public void testFileExistsInDir() throws IOException {
    assertTrue(Paths.get(".gitlet").toFile().exists());
    assertFalse(Paths.get("test.txt").toFile().exists());
  }

  /**
   * Method to test invalid commands
   */
  @Test
  public void testInvalidCommand() {
    Valid.invalidCommand("Command invalid");
    // if we get to this line, failure
    fail("Expected system exit");
  }
}