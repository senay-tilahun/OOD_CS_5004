package gitlet;

import java.util.*;

/**
 *
 */
public class GitletObjects implements GitSerialize {
    private String workingDirectory;
    public HashMap<String, GitletId> staging;


    /**
     *
     */
    public GitletObjects() {
        // create a new gitletCommit object using the initial commit constructor
        GitletCommit initCommit = new GitletCommit();
        staging = initCommit.staging;
    }


    /**
     *
     * @param isFileList
     */
    public GitletObjects(boolean isFileList) {
        if (isFileList) {
            staging = new HashMap<>();
        }
    }

    /**
     *
     * @param content
     * @param filename
     */
    public GitletObjects(String content, String filename) {
        // create a new GitletBlob
        new GitletBlob(content, filename);
    }

    /**
     *
     * @return
     */
    public String getWorkingDirectory() {
        return workingDirectory;
    }



}
