package gitlet.userCommand;


import gitlet.Utils;
import java.util.Arrays;

/**
 * Extends UserCommand Abstract Class
 * parses the command and executes correct functionality
 */
public class UserCommand {
  /**
   * Constructor - call super's constructor
   */
  public UserCommand(){
    super();
  }

  /**
   * Define our parsing method for the user command
   */
//  @Override
  public void parseUserCommand(String[] args) {
    // assign the first element on args to user command
    String name = args[0];
    String[] ops = Arrays.copyOfRange(args, 1, args.length);

    // check what the user command is
    if (name.equals("init")) {
      return new InitCommand(ops);
    } else if (name.equals("add")) {
      return new AddCommand(ops);
    } else if (name.equals("commit")) {
      return new CommitCommand(ops);
    } else if (name.equals("rm")) {
      return new RmCommand(ops);
    }
//    else if (name.equals("log")) {
//      return new LogCommand(ops);
//    } else if (name.equals("status")) {
//      return new StatusCommand(ops);
//    } else if (name.equals("branch")) {
//      return new BranchCommand(ops);
//    } else if (name.equals("reset")) {
//      return new ResetCommand(ops);
//    } else if (name.equals("checkout")) {
//      return new CheckoutCommand(ops);
//    } else if (name.equals("merge")) {
//      return new MergeCommand(ops);
//    } else if (name.equals("rebase")) {
//      return new RebaseCommand(ops);
//    }
    else {
      Utils.exitWithError("Command entered does not exist.");
      return;
    }
  }
}

