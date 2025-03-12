package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class to aid in main gitlet file management
 */
public class GitletFileManage {
    // TODO: - check this with Nati
    // initialize / create paths
    private static final GitletPaths gitPaths = new GitletPaths();

    public static File gitletObjects = gitPaths.getGitletObjects();
    public static File gitletInd = gitPaths.getGitletIndexFile();
    public static File gitletIndRM = gitPaths.getGitletIndexRMFile();
    public static File gitletLocHead = gitPaths.getGitletLocalHead();
    public static File gitletHead = gitPaths.getGitletHead();


    /**
     * Method to get current head
     * @return current head
     * @throws IOException i/o issues
     */
    static String getCurrentGitletHead() throws IOException {
        String headCommit;
        // check if filename is a file
        if (!gitletHead.isFile()){
            throw new IllegalStateException("Please confirm filename is correct");
        }
        byte[] byteArr = Files.readAllBytes(gitletHead.toPath());
        String temp = new String(byteArr, StandardCharsets.UTF_8);;
        headCommit = temp.trim();

        File branchHead = Paths.get(gitletLocHead.getPath(), headCommit).toFile();

        //
        if (!branchHead.isFile()){
            throw new IllegalStateException("Please confirm filename is correct");
        }
        byte[] byteArr2 = Files.readAllBytes(branchHead.toPath());

        return new String(byteArr2, StandardCharsets.UTF_8);
    }

    /**
     * Getter of head, head
     * @param id id
     * @return head, head
     */
    static String getHHead(String id) {
        if (id == null || id.length() < 2) {
            throw new IllegalArgumentException("Must have at least two characters");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(id.charAt(0)).append(id.charAt(1));
        return sb.toString();
    }

    /**
     * Getter of head body
     * @param id id
     * @return head body
     */
    static String getHBody(String id) {
        if (id == null || id.length() < 2) {
            throw new IllegalArgumentException("Must have at least two characters");
        }
        StringBuilder sb = new StringBuilder();
        sb.append(id.substring(2));
        return sb.toString();
    }
}

