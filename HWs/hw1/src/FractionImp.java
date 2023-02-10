/**
 * This class represents an implementation of the Fraction interface.
 */
public class FractionImp implements Fraction{
  private int numerator;
  private int denominator;
  private double decimalRep;

  /**
   * Constructs a Fraction given the numerator and denominator.
   * If fraction is negative, numerator becomes negative,
   * denominator always positive
   * It also sets the decimal value of the fraction
   * @param numerator numerator of the Fraction
   * @param denominator denominator of the Fraction
   * */
  public FractionImp(int numerator, int denominator){
    if (denominator < 0){
      this.numerator = -1 * numerator;
      this.denominator = -1 * denominator;
    } else {
      this.numerator = numerator;
      this.denominator = denominator;
    }
    // check if denominator is 0
    validateDenominator();
    this.decimalRep = (double) this.numerator/this.denominator;
  }

  /**
   * Helper function to validate denominator value
   * Denominator value should not be 0
   * */
  private void validateDenominator(){
    // check if denominator is 0 or -ve
    if (this.denominator == 0){
      throw new IllegalArgumentException("Denominator can't be 0");
    }
  }

  /**
   * Getter method of the denominator instance variable
   * @return the denominator of the fraction
   * */
  public int getDenominator() {
    return this.denominator;
  }

  /**
   * Getter method of the numerator instance variable
   * @return the numerator of the fraction
   * */
  public int getNumerator() {
    return this.numerator;
  }

  /**
   * Getter method of the decimal representation of this Fraction
   * @return the decimal representation of this Fraction
   * */
  public double getDecimalRep() {
    return this.decimalRep;
  }

  /**
   * Setter method of the denominator instance variable
   * Also updates the decimal representation of the fraction
   * @param denominator the new integer to set as numerator
   * */
  public void setDenominator(int denominator) {
    validateDenominator();
    this.denominator = denominator;
    this.decimalRep = (double) this.numerator/this.denominator;
  }

  /**
   * Setter method of the numerator instance variable
   * Also updates the decimal representation of the fraction
   * @param numerator the new integer to set as numerator
   * */
  public void setNumerator(int numerator) {
    this.numerator = numerator;
    this.decimalRep = (double) this.numerator/this.denominator;
  }

  /**
   * Helper function for toString.
   * Computes the greatest common denominator for two numbers
   * Euclid's recursion algorithm
   * */
  private static int gcd(int a, int b){
    if(b == 0)
    {
      return a;
    }
    return Math.abs(gcd(b, a % b));
  }

  /**
   * Creates a string representation of this Fraction
   * @return returns the string rep of this Fraction
   * */
  public String toString(){
    int gcd = gcd(this.getNumerator(), this.getDenominator());
    return String.format("Fraction is: %d / %d", this.getNumerator()/gcd,
                                                  this.getDenominator()/gcd);
  }

  /**
   * Computes and returns the scientific value (decimal) of the fraction.
   * @return the decimal value of fraction
   */
  @Override
  public double toDouble() {
    return this.getDecimalRep();
  }

  /**
   * Computes and returns the reciprocal of the fraction.
   * @return the reciprocal of the fraction.
   * */
  @Override
  public String reciprocal() {
    // check if numerator is 0
    if (this.getNumerator() == 0) {
      throw new IllegalArgumentException("Can't reciprocate, "
          + "numerator = 0");
    }
    FractionImp reciprocal = new FractionImp(this.getDenominator(),
                                              this.getNumerator());

    return reciprocal.toString();
  }

  /**
   * Add this Fraction to the one passed to it and returns the result as a Fraction
   * @param other the other Fraction to add to this Fraction
   * @return the resized Shape
   * */
  @Override
  public String add(FractionImp other) {
    int newNum = (this.getNumerator() * other.getDenominator())
                + (this.getDenominator() * other.getNumerator());
    int newDenom = this.getDenominator() * other.getDenominator();
    Fraction newFrac = new FractionImp(newNum, newDenom);
    return newFrac.toString();
  }

  /**
   * Compares this Fraction with the one passed to it.
   * if (this<other) return a negative number
   * if (this==other) return 0
   * if (this>other) return a positive number
   * @param other the other shape to be compared to
   * @return the result of the comparison
   */
  @Override
  public int compareTo(FractionImp other) {
//    double thisFrac = (double) this.getNumerator() / this.getDenominator();
//    double otherFrac = (double) other.getNumerator() / other.getDenominator();
    if (this.getDecimalRep() < other.getDecimalRep()){
      return -1;
    } else if (this.getDecimalRep() == other.getDecimalRep()) {
      return 0;
    }
    return 1;
  }

}
