package gitlet;

import java.io.File;
import java.nio.file.Paths;

public class GitletPaths {

    static final File gitletObjects = Paths.get(".gitlet", "objects").toFile();
    static final File gitletInd = Paths.get(".gitlet", "INDEX").toFile();
    static final File gitletIndRM = Paths.get(".gitlet", "INDEX_RM").toFile();
    static final File gitletLocalHead = Paths.get(".gitlet", "refs", "heads").toFile();
    static final File gitletHead = Paths.get(".gitlet", "HEAD").toFile();

  /**
   * Constructor
   *
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
