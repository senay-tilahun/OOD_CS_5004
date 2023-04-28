package gitlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Main class - contains methods to check and validate user commands
 * Use as follows on command line
 * Java gitlet.Main <args>
 *   args will contain the user commands
 */
public class Main {
    /**
     * Main method - gets user args and checks if valid
     * @param args user args from command line
     * @throws IOException if not able to read
     */
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        if (args.length == 0) {
            System.out.println("Please enter at least one gitlet command.");
            System.exit(0);
        }
        parseUserCommand(args);

    }

    /**
     * Method to parse user arguments from command line and execute correct command
     * @param args user arguments from command line
     *  @throws IOException if can't read
     */
    public static void parseUserCommand(String[] args)throws IOException, NoSuchAlgorithmException {
        // assign the first element on args to user command
        String name = args[0];
        String[] ops = Arrays.copyOfRange(args, 0, args.length);
        Gitlet gitlet = new Gitlet();

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
            // break

        } else {
            Valid.invalidCommand("Command is invalid.");
        }
    }
}
