/**
 * This represents an empty node in the list
 */
public class EmptyNode implements IListOfInts{

  @Override
  public IListOfInts addAtIndex(int data, int index) {
    // if index is 0
    if (index == 0) {
      return new ElementNode(data, this);
    }
    // else - index is > 0 which means out of bounds
    throw new IndexOutOfBoundsException("Index out of bounds");
  }

  @Override
  public IListOfInts addFront(int data) {
    // the same as adding at 0 index
    return this.addAtIndex(data, 0);
  }

  @Override
  public IListOfInts addBack(int data) {
    // add at index = count
    return this.addAtIndex(data, this.count());
  }

  @Override
  public int getDataAtIndex(int index) {
    throw new IndexOutOfBoundsException("Index out of bounds");
  }

  @Override
  public int count() {
    return 0;
  }

  @Override
  public int countHelp(int acc) {
    return acc;
  }

  @Override
  public int sum() {
    return 0;
  }

  @Override
  public int sumHelper(int sum) {
    return sum;
  }

  @Override
  public String toString() {
    return "";
  }
}
