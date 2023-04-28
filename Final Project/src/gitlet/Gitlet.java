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
     * Creates the .gitlet directory
     * Creates the initial commit - commit 0
     * set up folders and set the zero commit
     */
    void init() throws IOException, NoSuchAlgorithmException {
//        GitletFileCommand.initializeRepo();
        // Initialize .gitlet folder and sub-folders
        gitletObjects.mkdirs();
        gitletLocalHead.mkdirs();
        // check that directories are correctly created
        File[] directories = {gitletObjects, gitletLocalHead};
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
        //staging area is the Tree class under Objects and corresponding index file
        //first read the file content and make a gitletBlob
        File newFile = Paths.get(fileToAdd).toFile();
        //Paths.get(first, others).toFile();
        byte[] temp = Files.readAllBytes(newFile.toPath());
        String newFileString = new String(temp, StandardCharsets.UTF_8);
        // create new gitletBlob
        GitletObjects gitletBlob = new GitletObjects(newFileString, fileToAdd);
        // write the gitletBlob to disk
        GitletFileCommand.writeGitletCommitObject(gitletBlob);
    }

    /**
     * Method for commit command
     * @param msg the log message of the commit
     * @throws IOException if file I?O issues
     * @throws NoSuchAlgorithmException if algo not found
     */
    void commit(String msg) throws IOException, NoSuchAlgorithmException {
        GitletObjects stageAddChanges = GitletFileCommand.readAddedStageEntries();
        GitletObjects stageRmChanges = GitletFileCommand.readRemovedStageRemovals();
        if (stageAddChanges.staging.isEmpty() && stageRmChanges.staging.isEmpty()) {
            System.out.println("No changes added to the commit");
            System.exit(0);
        }
        GitletObjects currHeads = GitletFileCommand.returnCurrentCommit();
        GitletFileCommand.updateCurrentHeads(currHeads, stageAddChanges, stageRmChanges);
        createNewCommit(currHeads, msg);
        writeCommitObject(currHeads);
        clearStageEntries(stageAddChanges, stageRmChanges);
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
     *
     * @param newCommit
     * @throws IOException
     * @throws NoSuchAlgorithmException
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
        StringBuilder commitContent = new StringBuilder();
        String commitName = GitletFileManage.getCurrentGitletHead();
        // TODO - do I need the mergeCommitLog method ???
        while (!currentCommit.getCommitParent().equals("")) {
            commitContent.append(singleParentCommitLog(currentCommit, commitName));
            commitName = currentCommit.getCommitParent();
            currentCommit = (GitletCommit) Utility.readObjectFromFile(GitletFileManage.convertGitletObjectToFile(currentCommit.getCommitParent()), GitletObjects.class);
        }
        commitContent.append(finalLog(currentCommit, commitName));
        System.out.println(commitContent);
    }

    /**
     * Helper for log, gets current commit from file
     * @return the current commit
     */
    public static GitletObjects getCurrentCommitFromFile() throws IOException {
        // TODO - update GitletFileManage.getCurrentGitletHead() method
        String commitHash = getCurrentCommitId();
        return Utility.readObjectFromFile(GitletFileManage.convertGitletObjectToFile(commitHash), GitletObjects.class);
    }

    /**
     * Helper for log, gets the current commit
     * @return the current commit id
     */
    public static String getCurrentCommitId() throws IOException {
        String headFilePath = getHeadFilePath();
        File filename = new File(headFilePath);
        // check if filename is a file
        if (!filename.isFile()){
            throw new IllegalStateException("Please make sure it is a normal filename");
        }
        byte[] byteArr = Files.readAllBytes(filename.toPath());
        String headContents = new String(byteArr, StandardCharsets.UTF_8);
        return headContents.trim();
    }

    /**
     * Helper for log, gets the head file path
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
        return "=== \n" + "commit " + comName + "\n"
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


