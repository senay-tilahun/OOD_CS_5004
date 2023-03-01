/**
 * Created by ashesh on 1/26/2017.
 */
public abstract class AbstractShape implements Shape {
  protected Point2D reference;

  public AbstractShape(Point2D reference) {
    this.reference = reference;
  }

  @Override
  public double distanceFromOrigin() {
    return reference.distToOrigin();
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
  }

  /* Question 3: if we wanted to use the perimeter
  *           we would just update the compareTo method here to use the perimeter instead of
  *           the area - soe lines 19, 20, 22, 24 would need to be updated
  *
  *           if we did not have this Abstract class - we would need to implement and modify
  *           the compare to method in every concrete class, so three files
  *  */
}