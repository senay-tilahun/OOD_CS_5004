package gitlet;

import java.io.IOException;
import java.util.Arrays;

/**
 * TODO Add Java docs
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
           Utils.exitWithError("Please enter a command.");
        }
        parseUserCommand(args);

    }
    public static void parseUserCommand(String[] args) throws IOException {
        // assign the first element on args to user command
        String name = args[0];
        String[] ops = Arrays.copyOfRange(args, 0, args.length);
        GitLet gitlet = new GitLet();

        // check what the user command is
        if (name.equals("init")) {
            Valid.validateUserArgNum(args, 1, "init");
            Valid.repoAlreadyExists(); // validate repo doesn't exist
            gitlet.init();
        } else if (name.equals("commit")) {
            Valid.validateUserArgNum(ops, 2, "commit");
            // commit changes
            gitlet.commit(args[1]);
            // break
        } else if (name.equals("add")) {
            Valid.validateUserArgNum(ops, 2, "add");
            // validate the file name exists in the current directory
            Valid.fileExistsInDir(args[1]);
            // add to staging area
            gitlet.add(args[1]);
            // break
        } else if (name.equals("log")) {
            Valid.validateUserArgNum(ops, 1, "log");
            gitlet.log();
            //
        } else {
            Valid.invalidCommand("Command is invalid.");
        }
    }
}
