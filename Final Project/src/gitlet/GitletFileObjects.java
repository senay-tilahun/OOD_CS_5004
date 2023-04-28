package gitlet;

import java.io.File;


public class GitletFileObjects extends GitletFileManage {

  /**
   * Method to update repo file
   * @param PWD present working directory
   * @param blobId id of blob
   */
  static void updateRepoFile(File PWD, String blobId) {
    File repo = GitletFileManage.convertGitletObjectToFile(blobId);
    String content = ((GitletBlob) Utility.readObjectFromFile(repo, GitletObjects.class)).getContent();
    Utility.writeContentsToFile(PWD, content);
  }
}
