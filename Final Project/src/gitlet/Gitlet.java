package gitlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import static gitlet.GitletFileManage.*;

//TODO: class GitLet
public class GitLet {

    /**
     * Method for init command init
     * Creates the .gitlet directory
     * Creates the initial commit - commit 0
     * set up folders and set the zero commit
     */
    void init() throws IOException {
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
        Utils.writeObject(gitletIndRM, index);
        // catch exception
        GitletObjects fileList = new GitletObjects(true);
        try (ObjectOutputStream out = new ObjectOutputStream(
            new FileOutputStream(new File(".gitlet/index")))) {
            out.writeObject(fileList);
        } catch (IOException e) {
            System.out.println("IOException while writing object to file .gitlet/index");
            e.printStackTrace();
        }
        Utils.writeObject(gitletInd, fileList);
        Utils.writeContents(gitletHead, "master");
        GitletObjects commit0 = new GitletObjects();
        // TODO: update this method in GitletFileCommand & Utils
        GitletFileCommand.writeGitletCommitObject(commit0);
    }

    /**
     * add [filename]
     * add the file to the staging area
     *
     * @param fileToAdd filename of the new file to be added
     */
    void add(String fileToAdd) throws IOException {
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
     * commit all files in the staging area
     */
    void commit(String msg) throws IOException {
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
     * Helper Method for commit method
     * @param commitHead
     * @param message
     */
    private void createNewCommit(GitletObjects commitHead, String message) {
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
     * Helper method for commit method
     * @param newCommit
     * @throws IOException
     */
    private void writeCommitObject(GitletObjects newCommit) throws IOException {
        GitletFileCommand.writeGitletCommitObject(newCommit);
    }

    /**
     * Helper method for commit method
     * @param stageEntries
     * @param stageRm
     * @throws IOException
     */
    // TODO - need to change this method - same as original
    private void clearStageEntries(GitletObjects stageEntries, GitletObjects stageRm) throws IOException {
        stageEntries.staging.clear();
        stageRm.staging.clear();
        Utils.writeObject(gitletInd, stageEntries);
        Utils.writeObject(gitletIndRM, stageRm);
    }

    /**
     * Method to execute the log command
     */
    void log() {
        //
        GitletCommit currentCommit = (GitletCommit) getCurrentCommitFromFile();
        StringBuilder commitContent = new StringBuilder();
        String commitName = GitletFileManage.getCurrentGitletHead();
        // TODO - do I need the mergeCommitLog method ???
        while (!currentCommit.getCommitParent().equals("")) {
            commitContent.append(singleParentCommitLog(currentCommit, commitName));
            commitName = currentCommit.getCommitParent();
            currentCommit = (GitletCommit) Utils.readObject(GitletFileManage.convertGitletObjectToFile(currentCommit.getCommitParent()), GitletObjects.class);
        }
        commitContent.append(finalLog(currentCommit, commitName));
        System.out.println(commitContent);
    }

    /**
     * Helper for log
     * @return
     */
    public static GitletObjects getCurrentCommitFromFile() {
        // TODO - update GitletFileManage.getCurrentGitletHead() method
        String commitHash = getCurrentCommitHash();
        return Utils.readObject(GitletFileManage.convertGitletObjectToFile(commitHash), GitletObjects.class);
    }

    /**
     * Helper for log
     * @return
     */
    public static String getCurrentCommitHash() {
        String headFilePath = getHeadFilePath();
        String headContents = Utils.readContentsAsString(new File(headFilePath));
        return headContents.trim();
    }

    /**
     * Helper for log
     * @return
     */
    public static String getHeadFilePath() {
        return gitletHead.getPath();
    }

    /**
     * Helper for log
     * @return
     */
    private static String singleParentCommitLog(GitletObjects commit, String currName) {
        GitletCommit com = (GitletCommit) commit;
        return "=== \n" + "commit " + currName + "\n"
            + "Date: " + com.getDateTime() + "\n"
            + com.getMessage() + "\n\n";
    }

    /**
     * Helper for log
     * @return
     */
    private static String finalLog(GitletObjects curr, String currName) {
        return singleParentCommitLog(curr, currName);
    }

}


