import java.util.function.Predicate;
/**
 * This represents a non-empty node of the list. It contains a piece of data
 *  * along with the rest of the list
 */
public class ElementNode implements IListOfInts{
  private int item;
  private IListOfInts rest;

  public ElementNode(int item, IListOfInts rest){
    this.item = item;
    this.rest = rest;
  }

  @Override
  public IListOfInts addAtIndex(int data, int index) {
    // check if index is 0
    if (index == 0) {
      return new ElementNode(data, this);
      // if index is 1
    } else if (index == 1) {
      IListOfInts newNode = new ElementNode(data, this.getRest());
      this.setRest(newNode);
      return newNode;
    }
    // else
    return this.getRest().addAtIndex(data, index - 1);
  }

  @Override
  public IListOfInts addFront(int data) {
    // add at the 0th index
    return this.addAtIndex(data, 0);
  }

  @Override
  public IListOfInts addBack(int data) {
    // add at index = count
    return this.addAtIndex(data, this.count());
  }

  @Override
  public int getDataAtIndex(int index) {
    // check if index 0
    if (index == 0) {
      return this.getItem();
    }
    return this.getRest().getDataAtIndex(index - 1);
  }

  @Override
  public int count() {
    return countHelp(0);
  }

  @Override
  public int countHelp(int acc) {
    return this.getRest().countHelp(1 + acc);
  }

  @Override
  public int sum() {
    return sumHelper(0);
  }

  @Override
  public int sumHelper(int sum) {
    return this.getRest().sumHelper(sum + this.getItem());
  }

  // -------------

  public int getItem() {
    return item;
  }

  public void setItem(int item) {
    this.item = item;
  }

  public IListOfInts getRest() {
    return rest;
  }

  public void setRest(IListOfInts rest) {
    this.rest = rest;
  }

  @Override
  public String toString() {
    if (rest instanceof EmptyNode) {
      return String.format("%d", this.getItem());
    }
    return this.getItem() + ", " + this.rest.toString();
  }

}
