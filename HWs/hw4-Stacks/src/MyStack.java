/**
 * This interface contains all protocols for concrete MyStack class
 * @param <T>
 */
public interface MyStack<T> {

  /**
   * Adds element to the stack
   * @param item the item to add
   */
  void push(T item);

  /**
   * Removes the last element of the stack
   * @return the last element of the stack
   */
  T pop();

  /**
   * Peeks and returns the last element of the stack without removing it
   * @return the last element of the stack
   */
  T top();

  /**
   * Checks if the stack is empty
   * @return boolean, indicating whether the stack is empty of not
   */
  boolean empty();

}
