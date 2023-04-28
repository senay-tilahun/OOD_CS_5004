package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GitletFileCommand extends GitletFileManage{

  private static void gitletObjectToFile(GitletObjects gitObject, String hash){
    File objectsDir = Paths.get(gitletObjects.getPath(), hash).toFile();
    objectsDir.mkdir();
    //
    String headH = hash.substring(0, 2);
    String headB = hash.substring(2);
    File file = Utils.join(gitletObjects, headH, headB);
    // TODO - update Utils method
    Utils.writeObject(file, gitObject);
  }


  /**
   * write the commit object to .gitlet/objects/
   *
   * @param gitObj
   */
  static String writeGitletCommitObject(GitletObjects gitObj) throws IOException {
    // TODO: how do I update hash function in Utils
    String gitHash = Utils.hash(gitObj);
    GitletCommit com = (GitletCommit) gitObj;
    // if the object is of commit type
    if (com.getGitletObjectType().equals("commit")) {
      byte[] temp = Files.readAllBytes(gitletHead.toPath());
      String currBranch = new String(temp, StandardCharsets.UTF_8);
      updateHeadPtr(currBranch);
      gitletObjectToFile(gitObj, gitHash);
      return gitHash;
    }
    if (com.getGitletObjectType().equals("blob") && addStagedToIndex(gitHash, gitObj.getWorkingDirectory())) {
      gitletObjectToFile(gitObj, gitHash);
    }
    return gitHash;
  }


  /**
   * update the branch name of the head pointer in .gitlet/HEAD file
   *
   * @param branch name of the branch
   */
  static void updateHeadPtr(String branch) {
    Utils.writeContents(gitletHead, branch);
  }

  static GitletObjects returnCurrentCommit() throws IOException {
    String headCommitSHA = Utils.readContentsAsString(gitletHead).trim();
    File headCommitFile = Paths.get(gitletLocalHead.toString(), headCommitSHA).toFile();
    return Utils.readObject(headCommitFile, GitletObjects.class);
  }

  static GitletObjects readAddedStageEntries() throws IOException {
    return Utils.readObject(gitletInd, GitletObjects.class);
  }

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

  static GitletObjects readRemovedStageRemovals() throws IOException {
    return Utils.readObject(gitletIndRM, GitletObjects.class);
  }


  /**
   * write git INDEX file to record staged file
   *
   * @param hash     sha1 of the staged file
   * @param filename filename of the stage file in the CWD
   * @return true if write to index
   */
  static boolean addStagedToIndex(String hash, String filename) {
    GitletObjects fileList;
    fileList = Utils.readObject(gitletInd, GitletObjects.class);
    if (fileList.staging.containsKey(filename)) {
      //if dictionary contains the same file, compare hashcode
      //same as last commit/staged already
      if (hash == fileList.staging.get(filename).id) {
        //this version and previous version in staging area are the same
        //don't stage
        return false;
      }
    }
    //write update to file
    GitletId update = new GitletId(hash, filename);
    fileList.staging.put(filename, update);

    //fileList.printDict();
    Utils.writeObject(gitletInd, fileList);
    return true;
  }

  /**
   * Get current commit as Objects
   *
   * @return
   */
  static GitletObjects currentCommitAsGitletObjects() {
    File commit = convertGitletObjectToFile(GitletFileManage.getCurrentGitletHead());
    return Utils.readObject(commit, GitletObjects.class);
  }


//  /**
//   * helper function
//   * perform removal action, either update INDEX file or create entry in
//   * INDEX_RM file and perform deletion
//   *
//   * @param blobRmv file to be removed from gitlet system
//   * @return false if no action performed
//   */
//  static boolean updateIndexRemoval(GitletObjects blobRmv) {
//    String hashCode = Utils.hash(blobRmv);
//    //read from staging area
//    GitletObjects stageEntries = Utils.readObject(gitletInd, GitletObjects.class);
//    //read from current commit
//    File head = GitletFileManage.convertGitletObjectToFile(GitletFileManage.getCurrentGitletHead());
//    GitletObjects currHeads = Utils.readObject(head, GitletObjects.class);
//    if (!stageEntries.indexFile.isEmpty()
//        && stageEntries.indexFile.containsKey(blobRmv.getCwdName())) {
//      //Utils.restrictedDelete(file);
//      stageEntries.indexFile.remove(blobRmv.getCwdName());
//      Utils.writeObject(gitletInd, stageEntries);
//      return true;
//    } else if (!currHeads.indexFile.isEmpty()
//        && currHeads.indexFile.containsKey(blobRmv.getCwdName())) {
//      //if current commit contain the file and stage doesn't, write it to INDEX_RM
//      //delete from repo
//      Utils.restrictedDelete(new File(blobRmv.getCwdName()));
//      //create INDEX object for writing
//      GitletObjects removalStaged;
//      //write RM file
//      //read from stage remove
//      removalStaged = Utils.readObject(gitletIndRM, GitletObjects.class);
//      if (removalStaged == null) {
//        removalStaged = new GitletObjects("index");
//      }
//      Gitindex entry = new Gitindex(hashCode, blobRmv.getCwdName());
//      removalStaged.indexFile.put(blobRmv.getCwdName(), entry);
//      Utils.writeObject(gitletIndRM, removalStaged);
//      return true;
//    }
//    return false;
//  }
}
