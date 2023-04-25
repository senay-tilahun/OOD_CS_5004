package gitlet;

import java.io.File;
import java.util.*;

public class GitletFileManage {
    static final File gitletObjects = Utils.join(".gitlet","objects");
    static final File gitletInd = Utils.join(".gitlet", "INDEX");
    static final File gitletIndRM = Utils.join(".gitlet", "INDEX_RM");
    static final File gitletLocalHead = Utils.join(".gitlet", "refs", "heads");
    static final File gitletHead = Utils.join(".gitlet", "HEAD");
    static final File gitletBranch = Utils.join(".gitlet", "refs", "heads");
    static final File gitletPWD = Utils.join(".");



    /**
     * get current HEAD commit hash as a string
     *
     * @return hash of the current commit/HEAD
     */
    static String getCurrentGitletHead() {
        String curr = Utils.readContentsAsString(gitletHead);
        File branchHead = Utils.join(gitletLocalHead, curr);
        return Utils.readContentsAsString(branchHead);
    }


    /**
     * Helper function to get a Blob/Commit object with hash
     *
     * @param hash hashcode of the Objects
     * @return corresponding file, might not exist
     */
    static File convertGitletObjectToFile(String hash) {
        java.io.File file = Utils.join(gitletObjects, getHHead(hash), getHBody(hash));
        return file;
    }


    /**
     * Getter for hash code, used for naming things
     *
     * @return
     */
    static String getHHead(String hash) {
        return hash.substring(0, 2);
    }

    static String getHBody(String hash) {
        return hash.substring(2);
    }



    /**
     * compare cwd and commit and output a list of untracked files
     * @param commit
     * @return
     */
    static LinkedList<String> untrackedFiles(GitletObjects commit,
                                              GitletObjects staged,
                                              GitletObjects deleted) {
        LinkedList<String> list = new LinkedList<>();
        for (String file : Utils.plainFilenamesIn(gitletPWD)) {
            if (!commit.indexFile.containsKey(file)) {
                list.add(file);
            }
        }
        //must use itr instead of for range loop to remove
        for (Iterator<String> it= list.iterator(); it.hasNext(); ) {
            String file = it.next();
            if (staged.indexFile.containsKey(file)
                    || deleted.indexFile.containsKey(file)) {
                it.remove();
            }
        }
        return list;
    }

    /**
     * survey the current working directory to output a list of untracked but
     * modified or deleted but not staged files for log command
     * @param commit current commit to be compared
     * @param staged staged files
     * @param unstage staged for removal files
     * @return list of modified files
     */
    static LinkedList<String> modifiedFiles(GitletObjects commit,
                                            GitletObjects staged,
                                            GitletObjects unstage) {
        LinkedList<String> modified = new LinkedList<>();
        for (String i : staged.indexFile.keySet()) {
            System.out.println(i);
            String sha1 = staged.indexFile.get(i).getSha1Hash();
            String content = Utils.readContentsAsString(Utils.join(i));
            if (!Utils.hash(new GitletObjects(content, i)).equals(sha1)) {
                modified.add(i + " (modified)");
            }
        }
        for (String i : commit.indexFile.keySet()) {
            if (staged.indexFile.containsKey(i))
                continue;
            String sha1 = commit.indexFile.get(i).getSha1Hash();
            File cwdVer = Utils.join(i);
            if (!cwdVer.exists()) {
                modified.add(i + " (deleted)");
                continue;
            }
            String content = Utils.readContentsAsString(cwdVer);
            if (!Utils.hash(new GitletObjects(content, i)).equals(sha1))
                modified.add(i + " (modified)");
        }
        for (String i : unstage.indexFile.keySet()) {
            if (modified.contains(i + " (deleted)"))
                modified.remove(i + " (deleted)");
        }
    return modified;
    }



//    /**
//     * get the current hash of the given branch
//     *
//     * @param branchName
//     * @return
//     */
//    static String getBranchHead(String branchName) {
//        File head = Utils.join(gitletLocalHead, branchName);
//        return Utils.readContentsAsString(head);
//    }

//    /**
//     * return the name (not hash) of the current branch
//     *
//     * @return
//     */
//    static String currentBranch() {
//        return Utils.readContentsAsString(gitletHead);
//    }

//    /**
//     * write local branch head in .gitlet/refs/heads
//     *
//     * @param hash   hash of newest commit
//     * @param branch branch to update
//     */
//    static void updateBranchHead(String branch, String hash) {
//        File branchHead = Utils.join(".gitlet", "refs", "heads", branch);
//        Utils.writeContents(branchHead, hash);
//    }
//
//    /**
//     * Get the ancestor commits hash as a list (including merged in branches)
//     *
//     * @param currBranch branch to be retrieved
//     * @return list of commit hash
//     */
//    static List<String> pastCommits(String currBranch) {
//        //get head commit of this branch
//        String hash = Utils.readContentsAsString(Utils.join(gitletBranch, currBranch));
//        //TODO might be a problem to return List
//        List<String> ret = new ArrayList<>();
//        ret.add(hash);
//        LinkedList<String> queue = new LinkedList<>();
//        queue.add(hash);
//        while (!queue.isEmpty()) {
//            String commitHash = queue.pop();
//            GitletObjects currCommit = GitletFileManage.hashToGitletObjects(commitHash);
//            if (!currCommit.getSecondParent().equals("")) {
//                ret.add(currCommit.getSecondParent());
//                queue.add(currCommit.getSecondParent());
//            }
//            if (!currCommit.getParentHash().equals("")) {
//                ret.add(currCommit.getParentHash());
//                queue.add(currCommit.getParentHash());
//            }
//        }
//        return ret;
//    }

//    /**
//     * helper function for merge
//     * Given two branches, get the split point of the two. Implemented by making
//     * ancestors of two branches as a sorted map
//     * @param currBranch
//     * @param mergingParen
//     * @return hash of the splitpoint
//     */
//    static String splitPoint(String currBranch, List<String> mergingParen) {
//        TreeMap<Integer, String> anc = new TreeMap<>();
//        //traverse through current branch, record common ancestor if present in parenII
//        String hash = Utils.readContentsAsString(Utils.join(gitletBranch, currBranch));
//        LinkedList<String> queue = new LinkedList<>();
//        int i = 0;
//        queue.add(hash);
//        while (!queue.isEmpty()) {
//            String commitHash = queue.pop();
//            GitletObjects currCommit = GitletFileManage.hashToGitletObjects(commitHash);
//            if (!currCommit.getSecondParent().equals("")) {
//                queue.add(currCommit.getSecondParent());
//                if (mergingParen.contains(currCommit.getSecondParent())) {
//                    anc.put(i, currCommit.getSecondParent());
//                }
//            }
//            if (!currCommit.getParentHash().equals("")) {
//                queue.add(currCommit.getParentHash());
//                if (mergingParen.contains(currCommit.getParentHash())) {
//                    anc.put(i, currCommit.getParentHash());
//                }
//            }
//            ++i;
//        }
//    return anc.firstEntry().getValue();
//    }


//
//    /**
//     * clear staging area: INDEX and INDEX_RM
//     */
//    static void clearStage(GitletObjects stageEntries, GitletObjects stageRM) {
//        stageEntries.indexFile.clear();
//        stageRM.indexFile.clear();
//        Utils.writeObject(gitletInd, stageEntries);
//        Utils.writeObject(gitletIndRM, stageRM);
//    }


//    /**
//     * Given hash of an git object, return the object from repo
//     *
//     * @param hash
//     * @return Objects class of the git object
//     */
//    static GitletObjects hashToGitletObjects(String hash) {
//        File obj = convertGitletObjectToFile(hash);
//        return Utils.readObject(obj, GitletObjects.class);
//    }
}
