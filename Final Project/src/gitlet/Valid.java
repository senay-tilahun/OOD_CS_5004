package gitlet;


import java.nio.file.Paths;

/**
 * Utility class used to validate various inputs throughout gitlet implementation
 */
public class Valid {

  /**
   * Validates the number of user arguments is as expected for the given command
   * @param ops user args
   * @param expected expected number of args
   * @param name name of input
   * @throws RuntimeException if number of args do not match expected
   */
  public static void validateUserArgNum(String[] ops, int expected, String name){
    if (name.equals("commit")){
      if (ops.length != expected){
        // TODO - refactor Utils part here
        System.out.println("Please enter a commit message.");
        System.exit(0);

      }
    }

    if (ops.length != expected){
      throw new RuntimeException("Incorrect number of operands for command chosen.");
    }
  }

  /**
   * Method to check if repo already exists
   */
  public static void repoAlreadyExists(){
    if ((Paths.get(".gitlet").toFile()).exists()) {
      // exist with error message
      System.out.println("A version control repo already exists in directory.");
      System.exit(0);
    }
  }

  /**
   * Method to validate that a file exists in the working directory
   * @param filename filename
   */
  public static void fileExistsInDir(String filename){
    if(!(Paths.get(filename).toFile()).exists()){
      System.out.println("File does not exist in current directory");
      System.exit(0);
    }
  }

  /**
   * Method to exectue an invalid command
   * @param message
   */
  public static void invalidCommand(String message){
    System.out.println(message);
    System.exit(0);
  }

}
