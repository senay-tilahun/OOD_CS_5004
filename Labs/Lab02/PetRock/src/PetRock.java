/**
 * This class represents a pet rock The rock has a name.
 */
public class PetRock {
  // instance variables
  private String name;
  private boolean happy;
  private double weight;

  /**
   *Constructs a PetRock object and initializes it
   * @param name the name of the PetRock
   * */
  public PetRock(String name){
    if (name.isEmpty()){
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.happy = false;
  }

  public PetRock(String name, double weight){
    if (weight == 0) {
      throw new ArithmeticException();
    }
    this.name = name;
    this.weight = weight;
    this.happy = false;
  }

  /**
   * Get the name of this PetRock
   * @return name of this PetRock
   * */
  public String getName() {
    return name;
  }


  public boolean isHappy() {
    return happy;
  }

  public void playWithRock() {
    this.happy = true;
  }

  public String getHappyMessage(){
    if (!isHappy()){
      throw new IllegalStateException();
    }
    return "I'm happy";
  }

  public int getFavNumber(){
    return 42;
  }

  @Override
  public String toString(){
    return name + " the PetRock!";
  }

  /*
  public void waitTillHappy(){
    while (!happy){
      // do nothing
    }
  }
  */
}
