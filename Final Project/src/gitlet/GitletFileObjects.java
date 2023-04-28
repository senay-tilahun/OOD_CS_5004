package gitlet;

import java.io.File;


public class GitletFileObjects extends GitletFileManage {

  /**
   * replace/create cwd file with the repo version with the given blobHash
   * @param cwdNew cwd file to be written
   * @param blobHash hash of the blob in the repo
   */
  static void updateRepoFile(File cwdNew, String blobHash) {
    File repo = GitletFileManage.convertGitletObjectToFile(blobHash);
    String content = ((GitletBlob)Utils.readObject(repo, GitletObjects.class)).getContent();
    Utils.writeContents(cwdNew, content);
  }
}
