package gitlet;

import java.io.File;
import java.nio.file.Paths;

/**
 * Class to initialize all needed file paths for gitlet repo
 */
public class GitletPaths {

    static final File gitletObjects = Paths.get(".gitlet", "objects").toFile();
    static final File gitletInd = Paths.get(".gitlet", "INDEX").toFile();
    static final File gitletIndRM = Paths.get(".gitlet", "INDEX_RM").toFile();
    static final File gitletLocalHead = Paths.get(".gitlet", "refs", "heads").toFile();
    static final File gitletHead = Paths.get(".gitlet", "HEAD").toFile();

  /**
   * Getters for all file paths to utilize in different classes
   */


  public File getGitletObjects() {
    return gitletObjects;
  }

  public File getGitletIndexFile() {
    return gitletInd;
  }

  public File getGitletIndexRMFile() {
    return gitletIndRM;
  }

  public File getGitletLocalHead() {
    return gitletLocalHead;
  }

  public File getGitletHead() {
    return gitletHead;
  }
}
