package gitlet;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import jdk.jshell.execution.Util;


/**
 * Utility class used to validate various inputs throughout gitlet implementation
 */
public class Valid {

  /**
   * Validates the number of user arguments is as expected for the given command
   * @param ops
   * @param expected
   * @param name
   * @throws RuntimeException if number of args do not match expected
   */
  public static void validateUserArgNum(String[] ops, int expected, String name){
    if (name.equals("commit")){
      if (ops.length != expected){
        // TODO - refactor Utils part here
        Utils.exitWithError("Please enter a commit message.");
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
    // check if we have already created a repo
    boolean repoExists = Utils.join(".gitlet").exists();

    if (repoExists) {
      // exist with error message
      Utils.exitWithError("A version control repo already exists in directory.");
    }
  }

  /**
   * Method to validate that a file exists in the working directory
   * @param filename
   */
  public static void fileExistsInDir(String filename){
    boolean fileExists = Utils.join(filename).exists();
    if(!fileExists){
      Utils.exitWithError("File does not exist in current directory");
    }
  }

  public static void invalidCommand(String message){
    System.out.println(message);
    System.exit(0);
  }

}
