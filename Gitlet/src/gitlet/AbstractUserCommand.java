package gitlet;

/**
 * Abstract Class meant for future iterations, when more user commands are added
 */
public abstract class AbstractUserCommand implements UserCommandInt{
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

}
