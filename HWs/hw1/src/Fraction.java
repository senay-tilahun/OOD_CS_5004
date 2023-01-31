/**
 * Senay Tilahun
 * Spring CS 5004 - H1 - Fraction interface
 * This interface contains all protocols for concrete class FractionImp.
 * */
public interface Fraction extends Comparable<FractionImp> {
  /**
   * Computes and returns the scientific value (decimal) of the fraction.
   * @return the decimal value of fraction
   */
  double toDouble();

  /**
   * Computes and returns the reciprocal of the fraction.
   * @return the reciprocal of the fraction.
   * */
  String reciprocal();

  /**
   * Add this Fraction to the one passed to it and returns the result as a Fraction
   * @param other the other Fraction to add to this Fraction
   * @return the resized Shape
   * */
  String add(FractionImp other);
}
