package gitlet;

/**
 * Class that represents a blob object
 */
public class GitletBlob extends GitletObjects{
  private String type;
  private String content;
  private String CwdName;

  public GitletBlob(String content, String PWD) {

    this.type = "blob";
    this.content = content;
    this.CwdName  = PWD;
  }


  public String getGitletObjectType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String getWorkingDirectory() {
    return CwdName;
  }

  public void setCwdName(String cwdName) {
    CwdName = cwdName;
  }


}
