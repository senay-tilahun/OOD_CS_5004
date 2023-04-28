package gitlet;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class GitletFileManage {
    // TODO - check this with Nati
    // initialize / create paths
    private static final GitletPaths gitPaths = new GitletPaths();

    public static File gitletObjects = gitPaths.getGitletObjects();
    public static File gitletInd = gitPaths.getGitletIndexFile();
    public static File gitletIndRM = gitPaths.getGitletIndexRMFile();
    public static File gitletLocalHead = gitPaths.getGitletLocalHead();
    public static File gitletHead = gitPaths.getGitletHead();



    /**
     * get current HEAD commit hash as a string
     *
     * @return hash of the current commit/HEAD
     */
    static String getCurrentGitletHead() {
        String curr = Utils.readContentsAsString(gitletHead);
        File branchHead = Utils.join(gitletLocalHead, curr);
        return Utils.readContentsAsString(branchHead);
    }


    /**
     * Helper function to get a Blob/Commit object with hash
     *
     * @param hash hashcode of the Objects
     * @return corresponding file, might not exist
     */
    static File convertGitletObjectToFile(String hash) {
        java.io.File file = Utils.join(gitletObjects, getHHead(hash), getHBody(hash));
        return file;
    }


    /**
     * Getter for hash code, used for naming things
     *
     * @return
     */
    static String getHHead(String hash) {
        return hash.substring(0, 2);
    }

    static String getHBody(String hash) {
        return hash.substring(2);
    }



}
