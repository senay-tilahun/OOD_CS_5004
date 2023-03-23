
import java.util.*;

public class MyStackImp<T> implements MyStack<T> {
  private List<T> myStack;


  public MyStackImp(int size) {
    this.myStack = new ArrayList<>(8);
  }

  public MyStackImp(){
    this.myStack = new ArrayList<>();
  }


  @Override
  public void push(T item) {
    myStack.add(item);
  }

  @Override
  public T pop() {
    if (this.empty()){
      throw new EmptyStackException();
    }
    return myStack.remove(myStack.size() - 1);
  }

  @Override
  public T top() {
    if (this.empty()){
      throw new EmptyStackException();
    }
    return myStack.get(myStack.size() - 1);
  }

  @Override
  public boolean empty() {
    return myStack.isEmpty();
  }

  public List<T> getMyStack() {
    return myStack;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (T t : myStack) {
      builder.append(" ").append(t.toString());
    }
    return "Stack:" + builder;
  }

}
