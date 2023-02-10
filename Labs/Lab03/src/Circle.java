/**
 * This class represents a circle.  It offers all the operations mandated by the
 * Shape interface.
 */

public class Circle implements Shape {
  private double centerx, centery;
  private double radius;

  /**
   * Construct a circle object using the given center and radius
   * @param x x coordinate of the center of this circle
   * @param y y coordinate of the center of this circle
   * @param radius the radius of this circle
   */
  public Circle(double x, double y, double radius) {
    this.centerx = x;
    this.centery = y;
    this.radius = radius;
  }

  /**
   * Construct a circle object with the given radius. It is centered at (0,0)
   * @param radius the radius of this circle
   */
  public Circle(double radius) {
    this.centerx = 0;
    this.centery = 0;
    this.radius = radius;
  }

  /*
    FIELDS
    ..this.centerx : double
    ..this.centery : double
    ..this.radius  : double
  */

  /**
   * Creates a string representation of this string
   * @return a string representation of this string
   */
  public String toString() {
    return String.format("Circle: center (%.3f,%.3f) radius %.3f",
        this.centerx,this.centery,this.radius);
  }

  /**
   * Returns the distance of this shape from the origin. The distance is
   * measured from whatever reference position a shape is (e.g. a center for
   * a circle)
   * @return the distance from the origin
   */
  @Override
  public double distanceFromOrigin() {
    return Math.sqrt(this.centerx * this.centerx +
                     this.centery * this.centery);
  } // Repetitive.

  /**
   * Computes and returns the area of this shape.
   * @return the area of the shape
   */
  @Override
  public double area() {
    return Math.PI * radius * radius;
  }

  /**
   * Computes and returns the perimeter of this shape.
   * @return the perimeter of the shape
   */
  @Override
  public double perimeter() {
    return 2 * Math.PI * radius;
  }

  /**
   * Create and return a shape of the same kind as this one, resized
   * in area by the provided factor
   * @param factor factor of resizing
   * @return the resized Shape
   */
  @Override
  public Shape resize(double factor) {
    return new Circle(this.centerx, this.centery, Math.sqrt(factor) *
        radius);
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
