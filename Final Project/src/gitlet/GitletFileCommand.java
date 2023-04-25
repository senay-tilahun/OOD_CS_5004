package gitlet;

import java.io.File;
import java.util.*;

public class GitletFileCommand extends GitletFileManage{
  /**
   * set up dog gitlet folder and subfolders
   */
  static void initializeRepo() {
    gitletObjects.mkdirs();
    //this is for branching
    gitletLocalHead.mkdirs();
    GitletObjects index = new GitletObjects("index");
    Utils.writeObject(gitletIndRM, index);
    GitletObjects fileList = new GitletObjects("index");
    Utils.writeObject(gitletInd, fileList);
    Utils.writeContents(gitletHead, "master");
  }

  /**
   * write the commit object to .gitlet/objects/
   *
   * @param GitObject
   */
  static String writeGitletCommitObject(GitletObjects GitObject) {
    String hash = Utils.hash(GitObject);
    Utils.join(gitletObjects,
        getHHead(hash)).mkdir();
    java.io.File file = convertGitletObjectToFile(hash);
    if (GitObject.getType().equals("commit")) {
      //point head pointer to current head
      String currBranch = Utils.readContentsAsString(gitletHead);
      updateHeadPtr(currBranch);
//            updateBranchHead(currBranch, hash);
      Utils.writeObject(file, GitObject);
    }
    //handle blob
    else {
      if (addStagedToIndex(hash, GitObject.getCwdName())) {
        //if need to stage, store staged file to objects folder
        Utils.writeObject(file, GitObject);
      }
    }
    return hash;
  }


  /**
   * update the branch name of the head pointer in .gitlet/HEAD file
   *
   * @param branch name of the branch
   */
  static void updateHeadPtr(String branch) {
    Utils.writeContents(gitletHead, branch);
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
    if (fileList.indexFile.containsKey(filename)) {
      //if dictionary contains the same file, compare hashcode
      //same as last commit/staged already
      if (hash == fileList.indexFile.get(filename).getSha1Hash()) {
        //this version and previous version in staging area are the same
        //don't stage
        return false;
      }
    }
    //write update to file
    Gitindex update = new Gitindex(hash, filename);
    fileList.indexFile.put(filename, update);

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


  /**
   * helper function
   * perform removal action, either update INDEX file or create entry in
   * INDEX_RM file and perform deletion
   *
   * @param blobRmv file to be removed from gitlet system
   * @return false if no action performed
   */
  static boolean updateIndexRemoval(GitletObjects blobRmv) {
    String hashCode = Utils.hash(blobRmv);
    //read from staging area
    GitletObjects stageEntries = Utils.readObject(gitletInd, GitletObjects.class);
    //read from current commit
    File head = GitletFileManage.convertGitletObjectToFile(GitletFileManage.getCurrentGitletHead());
    GitletObjects currHeads = Utils.readObject(head, GitletObjects.class);
    if (!stageEntries.indexFile.isEmpty()
        && stageEntries.indexFile.containsKey(blobRmv.getCwdName())) {
      //Utils.restrictedDelete(file);
      stageEntries.indexFile.remove(blobRmv.getCwdName());
      Utils.writeObject(gitletInd, stageEntries);
      return true;
    } else if (!currHeads.indexFile.isEmpty()
        && currHeads.indexFile.containsKey(blobRmv.getCwdName())) {
      //if current commit contain the file and stage doesn't, write it to INDEX_RM
      //delete from repo
      Utils.restrictedDelete(new File(blobRmv.getCwdName()));
      //create INDEX object for writing
      GitletObjects removalStaged;
      //write RM file
      //read from stage remove
      removalStaged = Utils.readObject(gitletIndRM, GitletObjects.class);
      if (removalStaged == null) {
        removalStaged = new GitletObjects("index");
      }
      Gitindex entry = new Gitindex(hashCode, blobRmv.getCwdName());
      removalStaged.indexFile.put(blobRmv.getCwdName(), entry);
      Utils.writeObject(gitletIndRM, removalStaged);
      return true;
    }
    return false;
  }
}
