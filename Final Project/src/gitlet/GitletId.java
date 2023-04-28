package gitlet;


import java.util.*;

/**
 * Class to represent a Gitlet ID with file name combination
 */
public class GitletId implements GitSerialize {
    private final String name;
    public String id;


    /**
     * Constructor of a GitletId
     * @param id id
     * @param name filename
     */
    public GitletId(String id, String name) {
        this.name = name;
        //tracked, staged, removed (from stage), modified (not staged)
        this.id = id;
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

}
