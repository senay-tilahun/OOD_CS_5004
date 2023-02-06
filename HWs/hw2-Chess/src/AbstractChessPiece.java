/**
 * This class represents an implementation of the Bishop ChessPiece.
 * It implements the ChessPiece interface.
 */

public class Bishop implements ChessPiece{

  @Override
  public int getRow() {
    return 0;
  }

  @Override
  public int getColumn() {
    return 0;
  }

  @Override
  public Color getColor() {
    return null;
  }

  @Override
  public boolean canMove(int row, int col) {
    return false;
  }

  @Override
  public boolean canKill(ChessPiece piece) {
    return false;
  }
}
