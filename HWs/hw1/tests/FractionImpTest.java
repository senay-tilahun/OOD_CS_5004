import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FractionImpTest {
  // declare a few Fractions to test with
  private FractionImp frac1;
  private FractionImp frac2;
  private FractionImp frac3;
  private FractionImp frac4;

  /**
   * instantiates four fractions to use for the rest of unittests
   * @throws Exception to allow for general exception being thrown
   */
  @Before
      public void myTestSetUp() throws Exception{
    frac1 = new FractionImp(1, 2);
    frac2 = new FractionImp(3, 12);
    frac3 = new FractionImp(-1, 2);
    frac4 = new FractionImp(3, -12);
  }

  /**
   * Check if exception thrown when trying to divide by zero
   * IllegalArgumentException when trying to divide by zero
   */
  @Test (expected = IllegalArgumentException.class)
  public void testZeroDenominator() {
    FractionImp frac5 = new FractionImp(2, 0);
  }

  /**
   * Test to check the getter method for denominator instance variable
   * Tests getDenominator() method on frac1 and frac2
   */
  @Test
  public void testGetDenominator() {
    assertEquals(2, frac1.getDenominator());
    assertEquals(12, frac2.getDenominator());
  }

  /**
   * Test to check the getter method for numerator instance variable
   * Tests getNumerator() method on frac1 and frac2
   */
  @Test
  public void testGetNumerator() {
    assertEquals(1, frac1.getNumerator());
    assertEquals(3, frac2.getNumerator());
  }

  /**
   * Test to check the getter method for Decimal representation instance variable
   * Tests getDecimalRep() method on all fractions instantiated
   */
  @Test
  public void testGetDecimalRepresentation() {
    assertEquals(0.5,frac1.getDecimalRep(), 0.01);
    assertEquals(0.25, frac2.getDecimalRep(), 0.01);
    assertEquals(-0.5, frac3.getDecimalRep(), 0.01);
    assertEquals(-0.25, frac4.getDecimalRep(), 0.01);
  }

  /**
   * Test to check the setter method for denominator instance variable
   * Tests setDenominator() method on frac1 and frac2
   */
  @Test
  public void testSetDenominator() {
    frac1.setDenominator(5);
    frac2.setDenominator(3);
    assertEquals(5, frac1.getDenominator());
    assertEquals(3, frac2.getDenominator());
  }

  /**
   * Test to check the setter method for numerator instance variable
   * Tests setNumerator() method on frac1 and frac2
   */
  @Test
  public void testSetNumerator() {
    frac1.setNumerator(24);
    frac2.setNumerator(54);
    assertEquals(24, frac1.getNumerator());
    assertEquals(54, frac2.getNumerator());
  }

  /**
   * Test to check the toString method for FractionImp
   */
  @Test
  public void testToString() {
    assertEquals("Fraction is: 1 / 2", frac1.toString());
    assertEquals("Fraction is: 1 / 4", frac2.toString());
    assertEquals("Fraction is: -1 / 2", frac3.toString());
    assertEquals("Fraction is: -1 / 4", frac4.toString());
  }

  /**
   * Test to check toDouble - Decimal representation
   * Tests toDouble() method on all fractions instantiated
   */
  @Test
  public void testToDouble() {
    assertEquals(0.5, frac1.toDouble(), 0.01);
    assertEquals(0.25, frac2.toDouble(), 0.01);
    assertEquals(-0.5, frac3.toDouble(), 0.01);
    assertEquals(-0.25, frac4.toDouble(), 0.01);
  }

  /**
   * Test to check reciprocal representation of FractionImp
   * Tests reciprocal() method on all fractions instantiated
   */
  @Test
  public void testReciprocal() {
    assertEquals("Fraction is: 2 / 1", frac1.reciprocal());
    assertEquals("Fraction is: 4 / 1", frac2.reciprocal());
    assertEquals("Fraction is: -2 / 1", frac3.reciprocal());
    assertEquals("Fraction is: -4 / 1", frac4.reciprocal());
  }

  /**
   * Test to check exception thrown when trying to get reciprocal of fraction with
   * 0 as numerator
   */
  @Test (expected = IllegalArgumentException.class)
  public void testZeroNumeratorReciprocal() {
    FractionImp frac5 = new FractionImp(0, 5);
    frac5.reciprocal();
  }

  /**
   * Test to verify toAdd method is working
   * Create new fraction frac 5 -
   * add frac 5 to frac2 and frac4 and confirm output
   */
  @Test
  public void testAdd() {
    FractionImp frac5 = new FractionImp(2, 4);
    // test adding to positive fractions
    assertEquals("Fraction is: 3 / 4", frac2.add(frac5));
    // test adding positive and negative fractions
    assertEquals("Fraction is: 1 / 4", frac4.add(frac5));
  }

  /**
   * Test to check compareTo method with two fractions
   */
  @Test
  public void testCompareTo() {
    FractionImp frac5 = new FractionImp(2, 8);
    assertEquals(-1, frac3.compareTo(frac1));
    assertEquals(0, frac5.compareTo(frac2));
    assertEquals(1, frac4.compareTo(frac3));
  }
}
