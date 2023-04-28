package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * Class to aid in main gitlet file management
 * mainly for the gitlet class
 */
public class GitletFileCommand extends GitletFileManage{

  /**
   * Method to convert gitlet object to file
   * @param gitObject object to convert
   * @param ID id
   * @throws IOException i/o issues
   */
  private static void gitletObjectToFile(GitletObjects gitObject, String ID) throws IOException {
    File objectsDir = Paths.get(gitletObjects.getPath(), ID).toFile();
    objectsDir.mkdir();
    //
    String headH = ID.substring(0, 2);
    String headB = ID.substring(2);
    File file = Paths.get(gitletObjects.getPath(), headH, headB).toFile();
    // TODO - update Utils method
    Utility.writeObject(file, gitObject);
  }


  /**
   * Method to write gitlet commit object
   * @param gitObj object
   * @return git id
   * @throws IOException i/o issues
   */
  static String writeGitletCommitObject(GitletObjects gitObj)
      throws IOException, NoSuchAlgorithmException {
    // TODO: how do I update getId function in Utils
    String gitId = Utility.getId(gitObj);
    GitletCommit com = (GitletCommit) gitObj;
    // if the object is of commit type
    if (com.getGitletObjectType().equals("commit")) {
      byte[] temp = Files.readAllBytes(gitletHead.toPath());
      String currBranch = new String(temp, StandardCharsets.UTF_8);
      updateHeadPtr(currBranch);
      gitletObjectToFile(gitObj, gitId);
      return gitId;
    }
    if (com.getGitletObjectType().equals("blob") && addStagedToIndex(gitId, gitObj.getWorkingDirectory())) {
      gitletObjectToFile(gitObj, gitId);
    }
    return gitId;
  }


  /**
   * Method to update the head pointer
   * @param branch branch
   */
  static void updateHeadPtr(String branch) {
    Utility.writeContentsToFile(gitletHead, branch);
  }

  /**
   * Method to return current commit
   * @return the current commit
   * @throws IOException i/o issues
   */
  static GitletObjects returnCurrentCommit() throws IOException {
    String headCommitSHA, temp;
    // check if filename is a file
    if (!gitletHead.isFile()){
      throw new IllegalStateException("Please make sure it is a normal filename");
    }
    byte[] byteArr = Files.readAllBytes(gitletHead.toPath());
    temp = new String(byteArr, StandardCharsets.UTF_8);;
    headCommitSHA = temp.trim();
    File headCommitFile = Paths.get(gitletLocalHead.toString(), headCommitSHA).toFile();
    return Utility.readObjectFromFile(headCommitFile, GitletObjects.class);
  }

  /**
   * Method to read added stage entire
   * @return the added staged entire
   * @throws IOException i/o issues
   */
  static GitletObjects readAddedStageEntries() throws IOException {
    return Utility.readObjectFromFile(gitletInd, GitletObjects.class);
  }

  /**
   * Method to update current head
   * @param commitHead current commit head
   * @param add added
   * @param rm removed
   */
  static void updateCurrentHeads(GitletObjects commitHead, GitletObjects add,
      GitletObjects rm) {
    GitletCommit com = (GitletCommit) commitHead;
    GitletCommit added = (GitletCommit) add;
    GitletCommit removed = (GitletCommit) rm;
    // update added staged
    com.updatedAddStaged(added);
    com.updateRemovedStaged(removed);
    // update removed staged
  }

  /**
   * Method to read removed stage entry
   * @return removed
   * @throws IOException i/o issues
   */
  static GitletObjects readRemovedStageRemovals() throws IOException {
    return Utility.readObjectFromFile(gitletIndRM, GitletObjects.class);
  }


  /**
   * Methd to add staged
   * @param id id
   * @param filename file
   * @return true or false if, added to and removed form
   * @throws IOException i/o issues
   */
  static boolean addStagedToIndex(String id, String filename) throws IOException {
    GitletObjects fileList;
    fileList = Utility.readObjectFromFile(gitletInd, GitletObjects.class);
    if (fileList.staging.containsKey(filename)) {
      //if dictionary contains the same file, compare hashcode
      //same as last commit/staged already
      if (id == fileList.staging.get(filename).id) {
        //this version and previous version in staging area are the same
        //don't stage
        return false;
      }
    }
    //write update to file
    GitletId update = new GitletId(id, filename);
    fileList.staging.put(filename, update);

    //fileList.printDict();
    Utility.writeObject(gitletInd, fileList);
    return true;
  }
}
