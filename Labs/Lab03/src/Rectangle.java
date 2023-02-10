/**
 * This class represents a rectangle.  It defines all the operations mandated by
 * the Shape interface
 */
public class Rectangle implements Shape{
  private double width, height;
  private double x, y;

  /**
   * Constructs a rectangle object with the given location of its lower-left
   * corner and dimensions
   * @param x x coordinate of the lower-left corner of this rectangle
   * @param y y coordinate of the lower-left corner of this rectangle
   * @param width width of this rectangle
   * @param height height of this rectangle
   */
  public Rectangle(double x, double y, double width, double height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /*
    FIELDS
    ..this.x : double
    ..this.y : double
    ..this.width  : double
    ..this.height : double
  */

  /**
   * Creates a string representation of this string
   * @return a string representation of this string
   */
  public String toString() {
    return String.format("Rectangle: LL corner (%.3f,%.3f) width %.3f height " +
            "%.3f",
        this.x,this.y,this.width,this.height);
  }

  /**
   * Returns the distance of this shape from the origin. The distance is
   * measured from whatever reference position a shape is (e.g. a center for
   * a circle)
   * @return the distance from the origin
   */
  @Override
  public double distanceFromOrigin() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  } // Repetitive.

  /**
   * Computes and returns the area of this shape.
   * @return the area of the shape
   */
  @Override
  public double area() {
    return this.width * this.height;
  }

  /**
   * Computes and returns the perimeter of this shape.
   * @return the perimeter of the shape
   */
  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);
  }

  /**
   * Create and return a shape of the same kind as this one, resized
   * in area by the provided factor
   * @param factor factor of resizing
   * @return the resized Shape
   */
  @Override
  public Shape resize(double factor) {
    double sqrtFactor = Math.sqrt(factor);
    return new Rectangle(
        this.x,
        this.y, sqrtFactor *
        this.width,
        sqrtFactor * this.height);
  }

  /**
   * Compares this shape with the one passed to it based on their areas.
   * if (this<s) return a negative number
   * if (this==s) return 0
   * if (this>s) return a positive number
   * @param s the other shape to be compared to
   * @return the result of the comparison
   */
  @Override
  public int compareTo(Shape s) {
    double areaThis = this.area();
    double areaOther = s.area();

    if (areaThis < areaOther) {
      return -1;
    } else if (areaOther < areaThis) {
      return 1;
    } else {
      return 0;
    }
  } // Repetitive
}
