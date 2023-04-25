package gitlet.userCommand;

// TODO: java docs
/**
 *
 */
public abstract class AbstractUserCommand {
  // instance variables for user command and operands
  protected final String userCommand;
  protected final String[] ops;

  /**
   * Constructor
   */
  public AbstractUserCommand(String name, String[] ops){
    this.userCommand = name;
    this.ops = ops;
  }

  /**
   * Method to execute user command
   */
  public abstract void execute();

  public void validateUserArgNum(int expected){
    if (ops.length != expected){
      throw new RuntimeException("Incorrect number of operands.");
    }
  }
}
