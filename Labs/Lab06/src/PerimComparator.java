import java.util.Comparator;

/**
 * This class represents a Custom Comparator based on perimeter of Shape.
 */

public class PerimComparator implements Comparator<Shape> {

  /**
   * Compares its two Shape for order based on perimeter.
   * Returns a negative integer, zero, or a positive integer
   * as the first argument is less than, equal to, or greater than the second.<p>
   */
  @Override
  public int compare(Shape o1, Shape o2) {
    Double perim1 = o1.perimeter();
    Double perim2 = o2.perimeter();
    // use the build in Double compareTo function
    return perim1.compareTo(perim2);
  }
}
