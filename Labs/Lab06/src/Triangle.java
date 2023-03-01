/**
 * This class represents a triangle.  It offers all the operations mandated by the
 * Shape interface.
 */

public class Triangle extends AbstractShape{
  private Point2D p1;
  private Point2D p2;

  /**
   * Constructor
   * @param reference refernce point from Abstract Class
   * @param p1 first point
   * @param p2 second point
   */
  public Triangle(Point2D reference, Point2D p1, Point2D p2) {
    super(reference);
    if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
      throw new IllegalArgumentException("Points must be unique");
    }
    this.p1 = p1;
    this.p2 = p2;
  }

  public double getRefX() {
    return reference.getX();
  }


  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape
   */
  @Override
  public double area() {
    double x1 = reference.getX();
    double y1 = reference.getY();
    double x2 = p1.getX();
    double y2 = p1.getY();
    double x3 = p2.getX();
    double y3 = p2.getY();

    return Math.abs((x1*y2 + x2*y3 + x3*y1 - y1*x2 - y2*x3 - y3*x1)/2);
  }

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape
   */
  @Override
  public double perimeter() {
    double x1 = reference.getX();
    double y1 = reference.getY();
    double x2 = p1.getX();
    double y2 = p1.getY();
    double x3 = p2.getX();
    double y3 = p2.getY();

    double sideAB = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
    double sideAC = Math.sqrt((x1-x3)*(x1-x3) + (y1-y3)*(y1-y3));
    double sideBA = Math.sqrt((x2-x3)*(x2-x3) + (y2-y3)*(y2-y3));
    return sideAB + sideBA + sideAC;
  }

  /**
   * Create and return a shape of the same kind as this one, resized in area by the provided factor
   *
   * @param factor factor of resizing
   * @return the resized Shape
   */
  @Override
  public Shape resize(double factor) {
    double x1 = reference.getX() * factor;
    double y1 = reference.getY() * factor;
    double x2 = p1.getX() * factor;
    double y2 = p1.getY() * factor;
    double x3 = p2.getX() * factor;
    double y3 = p2.getY() * factor;

    Point2D newRef = new Point2D(x1, y1);
    Point2D newP1 = new Point2D(x2, y2);
    Point2D newP2 = new Point2D(x3, y3);
    return new Triangle(newRef, newP1, newP2);
  }

  /**
   * defines to string method
   * @return string representation of Triangle
   */
  public String toString() {
    return String.format("Triangle: reference (%.3f,%.3f) "
            + "point 1 (%.3f,%.3f) and point 1 (%.3f,%.3f)",
        this.reference.getX(),this.reference.getY(),p1.getX(), p1.getY(), p2.getX(), p2.getY());
  }
}
