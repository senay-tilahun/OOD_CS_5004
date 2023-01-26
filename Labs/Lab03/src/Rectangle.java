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
  public String toString() {
    return String.format("Rectangle: LL corner (%.3f,%.3f) width %.3f height " +
            "%.3f",
        this.x,this.y,this.width,this.height);
  }

  @Override
  public double distanceFromOrigin() {
    return Math.sqrt(this.x * this.x + this.y * this.y);
  } // Repetitive.

  @Override
  public double area() {
    return this.width * this.height;
  }

  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);
  }

  @Override
  public Shape resize(double factor) {
    double sqrtFactor = Math.sqrt(factor);
    return new Rectangle(
        this.x,
        this.y, sqrtFactor *
        this.width,
        sqrtFactor * this.height);
  }

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
