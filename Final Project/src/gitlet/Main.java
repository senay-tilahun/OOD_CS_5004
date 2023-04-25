package gitlet;

/** Driver class for Gitlet, the tiny stupid version-control system.
 *  @author
 */
public class Main {

    // TODO: Update java docs
    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND> .... */
    // TODO: Update this method to use Valid class -
    //      create valid class and add all Utils validation methods there
    public static void main(String[] args) {
        if (args.length == 0) {
           Utils.exitWithError("Please enter a command.");
        }

        UserCommand command = new UserCommand();
        command.parseUserCommand(args);

        return;
    }

}
