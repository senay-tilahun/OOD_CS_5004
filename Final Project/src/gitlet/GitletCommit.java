package gitlet;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Class that represents a commit object
 */
public class GitletCommit extends GitletObjects{
  private String dateTime;
  private String type;
  private String message;
  private LinkedList<String> parent;
  public HashMap<String, GitletId> staging;

  // constructor for initial commit

  /**
   * Constructor for initial commit
   * Sets the date, log message, parent, staging
   */
  public GitletCommit(){
    this.type = "commit";
    this.message = "initial commit";
    parent = new LinkedList<>();
    this.dateTime = Instant.EPOCH.atZone(ZoneId.systemDefault()).format(
        DateTimeFormatter.ofPattern("EEE MMM d kk:mm:ss uuuu xxxx"));
    staging = new HashMap<>();
  }

  /**
   * Getter and Setter methods
   */

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }


  public String getGitletObjectType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LinkedList<String> getParentList() {
    if (parent.isEmpty()){
      parent.add("");
    }
    return parent;
  }

  public String getCommitParent() {
    if (parent.isEmpty()){
      parent.add("");
    }
    return this.getParentList().getFirst();
  }

  public void setParent(LinkedList<String> parent) {
    this.parent = parent;
  }

  /**
   * Method to update added to stage
   * @param added added changes
   */
  public void updatedAddStaged(GitletCommit added){
    if (!added.staging.isEmpty()) {
      staging.putAll(added.staging);
    }
  }

  /**
   * Method to update removed to stage
   * @param removed removed changes
   */
  public void updateRemovedStaged(GitletCommit removed){
    staging.keySet().removeAll(removed.staging.keySet());
  }

}
