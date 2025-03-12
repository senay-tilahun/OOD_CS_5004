package gitlet;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import org.junit.Before;
import org.junit.Test;

/**
 * Class to JUnit test out Main class
 * Also tests the Gitlet class
 */
public class MainTest {
  Gitlet gitlet;

  /**
   * Set up the rest of the test
   * @throws Exception IO, NoSuchAlgo
   */
  @Before
  public void testSetUp() throws Exception {
    gitlet = new Gitlet();
  }

  /**
   * Method to test the init command of the parseUserCommand method
   * @throws IOException i/o issues
   */
  @Test
  public void testInitCommand() throws IOException, NoSuchAlgorithmException {
    // Test init command
    String[] init = {"init"};
    Main.parseUserCommand(init);
    assertTrue((Paths.get(".gitlet").toFile()).exists());
  }

  /**
   * Method to test the add command of the parseUserCommand method
   * @throws IOException i/o issues
   */
  @Test
  public void testAddCommand() throws IOException, NoSuchAlgorithmException {

    // Test add command
    String[] add = {"add", "file.txt"};
    Main.parseUserCommand(add);

  }

  /**
   * Method to test the commit command of the parseUserCommand method
   * @throws IOException i/o issues
   */
  @Test
  public void testCommitCommand() throws IOException, NoSuchAlgorithmException {
    // Test commit command
    String[] commit = {"commit", "Test"};
    Main.parseUserCommand(commit);
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    System.setOut(new PrintStream(out));
    assertEquals("No changes added to the commit", out.toString());
//    assertEquals(0, System.exit(0));
  }

  /**
   * Method to test the invalid command of the parseUserCommand method
   * @throws IOException i/o issues
   */
  @Test
  public void testInvalidCommand() throws IOException, NoSuchAlgorithmException {
    // Test invalid command
    String[] invalid = {"invalid"};
    try {
      Main.parseUserCommand(invalid);
    } catch (IllegalArgumentException e) {
      assertEquals("Command is invalid", e);
    }
  }
}