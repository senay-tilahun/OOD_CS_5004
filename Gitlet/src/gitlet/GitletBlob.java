package gitlet;

/**
 * Class that represents a blob object
 */
public class GitletBlob extends GitletObjects{
  private String type;
  private String content;
  private String PWD;

  /**
   * Constructor to make a blob object
   * @param content content of blob
   * @param PWD current working directory of blob
   */
  public GitletBlob(String content, String PWD) {

    this.type = "blob";
    this.content = content;
    this.PWD = PWD;
  }

  /**
   * Getter for object type
   * @return type
   */
  public String getGitletObjectType() {
    return type;
  }

  /**
   * Setter for object type
   * @param type type to set
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Getter of content
   * @return content
   */
  public String getContent() {
    return content;
  }

  /**
   * Setter of Content
   * @param content content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * Getter of PWD
   * @return PWD
   */
  @Override
  public String getWorkingDirectory() {
    return PWD;
  }

  /**
   * Setter of PWD
   * @param PWD PWD to set
   */
  public void setPWD(String PWD) {
    this.PWD = PWD;
  }
}
