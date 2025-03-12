package gitlet;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Main class to represent internal objects of gitlet
 * has two inheritors
 */
public class GitletObjects implements GitSerialize {
    private String workingDirectory;
    public HashMap<String, GitletId> staging;


    /**
     * Contractor to construct an initial commit object
     */
    public GitletObjects() {
        // create a new gitletCommit object using the initial commit constructor
        GitletCommit initCommit = new GitletCommit();
        staging = initCommit.staging;
    }


    /**
     * Constructor to initialize staging
     * @param isFileList boolen to indicate this is the file
     */
    public GitletObjects(boolean isFileList) {
        if (isFileList) {
            staging = new HashMap<>();
        }
    }

    /**
     * Constructor to create a blob object
     * @param content content of blob
     * @param filename file name
     */
    public GitletObjects(String content, String filename) {
        // create a new GitletBlob
        new GitletBlob(content, filename);
    }

    /**
     * Getter method to return current working directory
     * @return
     */
    public String getWorkingDirectory() {
        return workingDirectory;
    }

    /**
     * Method to clear the staging area
     * @param changes change to staging area to clear
     */
    static void clearStage(GitletObjects changes){
        changes.staging.clear();
    }

    /**
     * Method to update staging area
     * @param filename file staging
     * @param changes changes
     * @throws IOException i/o issues
     */
    static void writeStage(File filename, GitletObjects changes) throws IOException {
        Utility.writeObject(filename, changes);
    }
}
