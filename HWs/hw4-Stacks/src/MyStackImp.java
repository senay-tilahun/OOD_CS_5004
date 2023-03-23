
import java.util.*;

/**
 * This class represents a generic implementation of MyStack.
 * @param <T> the type of data containing in the stack during use
 */
public class MyStackImp<T> implements MyStack<T> {
  private List<T> myStack;

  /**
   * Constructor 1
   * Constructs a MyStackImp with an initial size
   * @param size the initial size of MyStack
   */
  public MyStackImp(int size) {
    this.myStack = new ArrayList<>(8);
  }

  /**
   * Constructor 2
   * Constructs an empty stack
   */
  public MyStackImp(){
    this.myStack = new ArrayList<>();
  }

  /**
   * Adds element to the stack
   * @param item the item to add
   */
  @Override
  public void push(T item) {
    myStack.add(item);
  }

  /**
   * Removes the last element of the stack
   * @return the last element of the stack
   */
  @Override
  public T pop() {
    if (this.empty()){
      throw new EmptyStackException();
    }
    return myStack.remove(myStack.size() - 1);
  }

  /**
   * Peeks and returns the last element of the stack without removing it
   * @return the last element of the stack
   */
  @Override
  public T top() {
    if (this.empty()){
      throw new EmptyStackException();
    }
    return myStack.get(myStack.size() - 1);
  }

  /**
   * Checks if the stack is empty
   * @return boolean, indicating whether the stack is empty of not
   */
  @Override
  public boolean empty() {
    return myStack.isEmpty();
  }

  /**
   * Getter method for the instance variable
   * @return the List myStack
   */
  public List<T> getMyStack() {
    return myStack;
  }

  /**
   * Method to create the string representation of MyStack
   * @return the string representation of MySta
   */
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (T t : myStack) {
      builder.append(" ").append(t.toString());
    }
    return "Stack:" + builder;
  }

}
