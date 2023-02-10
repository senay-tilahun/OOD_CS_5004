/**
 * This class represents a pet rock The rock has a name.
 */
public class PetRock {
  // instance variables
  private String name;
  private boolean happy;
  private double weight;

  /**
   * Constructs a PetRock object and initializes it
   * @param name the name of the PetRock
   * */
  public PetRock(String name){
    if (name.isEmpty()){
      throw new IllegalArgumentException();
    }
    this.name = name;
    this.happy = false;
  }

  /**
   * Constructs a PetRock object and initializes it
   * @param name the name of the PetRock
   * @param weight the weight of the PetRock
   */
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

  /**
   * Checks if this PetRock is happy
   * @return true if this PetRock is happy, false otherwise
   */
  public boolean isHappy() {
    return happy;
  }

  /**
   * Method to represent user playing with this PetRock
   * Changes the happy instance var to true
   */
  public void playWithRock() {
    this.happy = true;
  }

  /**
   * Checks if this PetRock is happy or not
   * returns an IllegaStateException if not happy
   * @return a String "I'm happy" if PetRock is happy
   */
  public String getHappyMessage(){
    if (!isHappy()){
      throw new IllegalStateException();
    }
    return "I'm happy";
  }

  /**
   * Method to get the favorite number of this PetRock
   * @return the fav number of this pet rock
   */
  public int getFavNumber(){
    return 42;
  }

  /**
   * Constructs a string representation of this PetRock
   * @return the string representation of this PerRock
   */
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
