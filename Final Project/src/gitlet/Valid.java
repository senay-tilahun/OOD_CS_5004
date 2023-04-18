package gitlet;

/**
 * This class represents a Validation class.
 * This will be used to validate different conditions for various class
 *  Main class validation
 *    check if user args are valid
 *  Command class validation
 *    init - validate if repo as been initialized
 *    add -
 *      check if file being added exists in working directory
 *      if file exists - is file valid for addition
 *        is file in current commit?
 *        are file contents different from one present in current commit.
 *    commit -
 *      validate if commit is possible
 *        is staging area empty?
 *        do we have a commit message?
 *
 */
class Valid {

  // TODO: Main class validation - user args are valid

  // TODO: init command - validate repo doesn't already exist in current repo

  // TODO: add command -
  //    if file in directory, is file valid for addition

  // TODO: commit command - validate if commit is possible
  //    staging area not empty and commit message exists

}
