/**
 * This interface represents all the operations to be supported by a list of ints
 */
public interface IListOfInts {

  /**
   * Adds a new ElementNode with given data at the given index
   * @param data the given data to add at the index
   * @param index the index to add the new Node at
   * @return the new IlistOfInts that has been added at the given index
   */
  IListOfInts addAtIndex(int data, int index);

  /**
   * Adds a new IlistOfInts at the front of the list
   * @param data the data to add to the new IlistOfInts
   * @return the new IlistOfInts that has been added
   */
  IListOfInts addFront(int data);

  /**
   * Adds a new IlistOfInts at the back of the list
   * @param data the data to add to the new IlistOfInts
   * @return the new IlistOfInts that has been added
   */
  IListOfInts addBack(int data);

  /**
   * Returns the data at a given index
   * @param index the index to retrieve data from
   * @return the data at a given index
   */
  int getDataAtIndex(int index);

  /**
   * Return the number of IlistOfInts in this list
   * @return the size of this list
   */
  int count();

  /**
   * Helper method for count() using an accumulator
   * recursively computes the number of IlistOfInts in this list
   * @param acc accumulator to track count of nodes
   * @return the size of this list
   */
  int countHelp(int acc);

  /**
   * Returns the sum of the data in this list
   * @return the sum of this list
   */
  int sum();

  /**
   * Helper method for sum() using an accumulator/sum
   * recursively computes the number of IlistOfInts in this list
   * @param sum accumulator to track sum of data in nodes
   * @return the sum of this list
   */
  int sumHelper(int sum);

}
