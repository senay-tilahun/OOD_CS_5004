package gitlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static gitlet.GitletFileManage.*;

/**
 * Main class that handles execution of the different gitlet commands
 * init, add, commit, log
 */
public class Gitlet {

    /**
     * Method for command init
     * Creates the .gitlet directory and sub-folders
     * Creates the initial commit
     */
    void init() throws IOException, NoSuchAlgorithmException {
//        GitletFileCommand.initializeRepo();
        // Initialize .gitlet folder and sub-folders
        gitletObjects.mkdirs();
        gitletLocHead.mkdirs();
        // check that directories are correctly created
        File[] directories = {gitletObjects, gitletLocHead};
        for (File folder : directories){
            if (!folder.exists() && !folder.mkdir()){
                throw new IllegalStateException("Unable to create directory.");
            }
        }
        GitletObjects index = new GitletObjects(true);
        // catch exception
        try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(new File(".gitlet/indexRM")))) {
            out.writeObject(index);
        } catch (IOException e) {
            System.out.println("IOException while writing object to file .gitlet/index");
            e.printStackTrace();
        }
        Utility.writeObject(gitletIndRM, index);
        // catch exception
        GitletObjects fileList = new GitletObjects(true);
        try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(new File(".gitlet/index")))) {
            out.writeObject(fileList);
        } catch (IOException e) {
            System.out.println("IOException while writing object to file .gitlet/index");
            e.printStackTrace();
        }
        Utility.writeObject(gitletInd, fileList);
        Utility.writeContentsToFile(gitletHead, "master");
        GitletObjects commit0 = new GitletObjects();
        // TODO: update this method in GitletFileCommand & Utils
        GitletFileCommand.writeGitletCommitObject(commit0);
    }

    /**
     * Method for add command
     * @param fileToAdd file to add to staging area
     * @throws IOException if file I?O issues
     * @throws NoSuchAlgorithmException if algo not found
     */
    void add(String fileToAdd) throws IOException, NoSuchAlgorithmException {
        // Get and store the file
        File gitFile = Paths.get(fileToAdd).toFile();
        //Paths.get(first, others).toFile();
        // read file
        byte[] temp = Files.readAllBytes(gitFile.toPath());
        String newFileString = new String(temp, StandardCharsets.UTF_8);
        // create gitletBlob object and store
        GitletObjects gitletBlob = new GitletObjects(newFileString, fileToAdd);
        GitletFileCommand.writeGitletCommitObject(gitletBlob);
    }

    /**
     * Method for commit command
     * @param msg the log message of the commit
     * @throws IOException if file I?O issues
     * @throws NoSuchAlgorithmException if algo not found
     */
    void commit(String msg) throws IOException, NoSuchAlgorithmException {
        // read from stage entries and removals
        GitletObjects added = GitletFileCommand.readAddedStageEntries();
        GitletObjects removed = GitletFileCommand.readRemovedStageRemovals();
        // if nothing staged, no need to commit
        if (added.staging.isEmpty() && removed.staging.isEmpty()) {
            System.out.println("No changes added to the commit");
            System.exit(0);
        }
        // get current commit
        String headCommitSHA, temp;
        // check if filename is a file
        if (!gitletHead.isFile()){
            throw new IllegalStateException("Please make sure it is a normal filename");
        }
        byte[] byteArr = Files.readAllBytes(gitletHead.toPath());
        temp = new String(byteArr, StandardCharsets.UTF_8);;
        headCommitSHA = temp.trim();
        File headCommitFile = Paths.get(gitletLocHead.toString(), headCommitSHA).toFile();
        GitletObjects headCommit = Utility.readObjectFromFile(headCommitFile, GitletObjects.class);

        GitletFileCommand.updateCurrentHeads(headCommit, added, removed);
        createNewCommit(headCommit, msg);
        writeCommitObject(headCommit);
        // once committed, clear the staging areas
        clearStageEntries(added, removed);
    }

    /**
     * Helper method to create new commit
     * @param commitHead current head of the commit tree
     * @param message commit message
     * @throws IOException if file i/o issues
     */
    private void createNewCommit(GitletObjects commitHead, String message) throws IOException {
        GitletCommit commit = (GitletCommit) commitHead;
        commit.setType("commit");
        commit.setMessage(message);
        String time = ZonedDateTime.now().format(
            DateTimeFormatter.ofPattern("EEE MMM d kk:mm:ss uuuu xxxx"));
        commit.setDateTime(time);
        commit.setParent(new LinkedList<>());
        commit.getParentList().add(GitletFileManage.getCurrentGitletHead());
    }

    /**
     * Method to write commit object
     * @param newCommit commit to write
     * @throws IOException i/o issues
     */
    private void writeCommitObject(GitletObjects newCommit)
        throws IOException, NoSuchAlgorithmException {
        GitletFileCommand.writeGitletCommitObject(newCommit);
    }

    /**
     * Helper method for commit method, clears staging area after commit is made
     * @param added added items to stage
     * @param removed removed items from stage
     * @throws IOException i/o issues
     */
    private void clearStageEntries(GitletObjects added, GitletObjects removed) throws IOException {
        // clear the added and removed staging
        GitletObjects.clearStage(added);
        GitletObjects.clearStage(removed);
        GitletObjects.writeStage(gitletInd, added);
        GitletObjects.writeStage(gitletIndRM, removed);
    }

    /**
     * Method for log command
     * @throws IOException i/o issues
     */
    void log() throws IOException {
        //
        GitletCommit currentCommit = (GitletCommit) getCurrentCommitFromFile();
        StringBuilder builder = new StringBuilder();
        String commitName = GitletFileManage.getCurrentGitletHead();

        while (!currentCommit.getCommitParent().equals("")) {
            builder.append(singleParentCommitLog(currentCommit, commitName));
            commitName = currentCommit.getCommitParent();
            String id = currentCommit.getCommitParent();
            File file = Paths.get(gitletObjects.getPath(), getHHead(id), getHBody(id)).toFile();
            currentCommit = (GitletCommit) Utility.readObjectFromFile(file, GitletObjects.class);
        }
        builder.append(finalLog(currentCommit, commitName));
        System.out.println(builder);
    }

    /**
     * Helper for log, gets current commit from file
     * @return the current commit
     */
    public static GitletObjects getCurrentCommitFromFile() throws IOException {
        String commitId = getCurrentCommitId();
        File file = Paths.get(gitletObjects.getPath(),
            getHHead(commitId), getHBody(commitId)).toFile();
        return Utility.readObjectFromFile(file, GitletObjects.class);
    }

    /**
     * Helper for log, gets the current commit
     * @return the current commit id
     */
    public static String getCurrentCommitId() throws IOException {
        String headFilePath = getHeadFilePath();
        File filename = new File(headFilePath);
        if (!filename.isFile()){
            throw new IllegalStateException("Please make sure it is a normal filename");
        }
        byte[] byteArr = Files.readAllBytes(filename.toPath());
        String headContents = new String(byteArr, StandardCharsets.UTF_8);
        return headContents.trim();
    }

    /**
     * Helper, gets the head file path
     * @return head file path
     */
    public static String getHeadFilePath() {
        return gitletHead.getPath();
    }

    /**
     * Helper for log, single parent commit log
     * @return single parent commit log
     */
    private static String singleParentCommitLog(GitletObjects commit, String comName) {
        GitletCommit com = (GitletCommit) commit;
        return "=== \n"
            + "commit " + comName + "\n"
            + "Date: " + com.getDateTime() + "\n"
            + com.getMessage() + "\n\n";
    }

    /**
     *
     * Helper for log, gets final Log
     * @return final log
     */
    private static String finalLog(GitletObjects com, String comName) {
        return singleParentCommitLog(com, comName);
    }

}


