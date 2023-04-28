package gitlet;


import java.util.*;

public class GitletId implements GitSerialize {
    private final String name;
    public String id;


    /**
     *
     * @param id
     * @param name
     */
    public GitletId(String id, String name) {
        this.name = name;
        //tracked, staged, removed (from stage), modified (not staged)
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getName() { return this.name; }


    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + id.hashCode();
        return result;
    }

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
