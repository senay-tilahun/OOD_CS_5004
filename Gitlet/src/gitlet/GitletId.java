package gitlet;


import java.util.*;

/**
 * Class to represent a Gitlet ID with file name combination
 */
public class GitletId implements GitSerialize {
    private LinkedList<String> parent;
    public String id;
    private HashMap<GitletId, GitletBlob> blobRefs;
    private final String name;


    /**
     * Constructor of a GitletId
     * @param id id
     * @param name filename
     */
    public GitletId(String id, String name) {
        this.id = id;
        this.name = name;
        this.parent = new LinkedList<>();
        this.blobRefs = new HashMap<>();
    }

    /**
     * Getter of id
     * @return id
     */
    public String getId() {
        return this.id;
    }

    /**
     * Getter of name
     * @return filename
     */
    public String getName() { return this.name; }

    /**
     * Override equals method
     * @param obj other object
     * @return true or false, depending on comparison
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GitletId)) {
            return false;
        }
        GitletId other = (GitletId) obj;
        return Objects.equals(name, other.name)
            && Objects.equals(id, other.id);
    }

    /**
     * Override hashCode to compare different GitletIDs
     * @return int hashcode
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

    /**
     * Getter of Parent
     * @return Parent of Object related to this GitletID
     */
    public LinkedList<String> getParent() {
        return parent;
    }

    /**
     * Setter of Parent
     */
    public void setParent(LinkedList<String> parent) {
        this.parent = parent;
    }

    /**
     * Getter of blobReferences
     * @return blob references
     */
    public HashMap<GitletId, GitletBlob> getBlobRefs() {
        return blobRefs;
    }

    /**
     * Setter of blobReferences
     */
    public void setBlobRefs(HashMap<GitletId, GitletBlob> blobRefs) {
        this.blobRefs = blobRefs;
    }
}
