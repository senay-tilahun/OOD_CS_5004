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
    File dir = Paths.get(gitletObjects.getPath(), ID).toFile();
    dir.mkdir();
    //
    String headH = ID.substring(0, 2);
    String headB = ID.substring(2);
    File file = Paths.get(gitletObjects.getPath(), headH, headB).toFile();
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
    String gitId = Utility.getId(gitObj);
    GitletCommit com = (GitletCommit) gitObj;
    // if the object is of commit type
    if (com.getGitletObjectType().equals("commit")) {
      byte[] temp = Files.readAllBytes(gitletHead.toPath());
      String commitB = new String(temp, StandardCharsets.UTF_8);
      Utility.writeContentsToFile(gitletHead, commitB);
      gitletObjectToFile(gitObj, gitId);
      return gitId;
    }
    if (com.getGitletObjectType().equals("blob") &&
        addStaged(gitId, gitObj.getWorkingDirectory())) {
      gitletObjectToFile(gitObj, gitId);
    }
    return gitId;
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
   * Method to add staged
   * @param id id
   * @param filename file
   * @return true or false if, added to and removed form
   * @throws IOException i/o issues
   */
  static boolean addStaged(String id, String filename) throws IOException {
    GitletObjects staging = readGitletObjects();
    if (staging.staging.containsKey(filename) && staging.staging.get(filename).id.equals(id)) {
      return false; // don't stage if the same version already exists in staging area
    }
    GitletId newId = new GitletId(id, filename);
    staging.staging.put(filename, newId);
    writeGitletObjects(staging);
    return true;
  }

  /**
   * Helper for to add staged
   */
  static GitletObjects readGitletObjects() {
    return Utility.readObjectFromFile(gitletInd, GitletObjects.class);
  }

  /**
   * Helper for to add staged
   */
  static void writeGitletObjects(GitletObjects objects) throws IOException {
    Utility.writeObject(gitletInd, objects);
  }
}
