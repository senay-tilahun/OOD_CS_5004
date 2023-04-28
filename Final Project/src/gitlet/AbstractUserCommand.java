package gitlet;

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

}
