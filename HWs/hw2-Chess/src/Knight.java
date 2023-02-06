/**
 * This class represents a Knight chess piece.
 * It offers all the operations mandated by
 * the ChessPiece interface and AbstractChessPiece class.
 */
public class Knight extends AbstractChessPiece{

  /**
   * Constructs a Knight ChessPiece matching the
   * superclass AbstractChessPiece's constructor
   * @param row   the initial row position of the piece
   * @param col   the initial column position of the piece
   * @param color the color of the piece
   */
  public Knight(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * @param row the row of the given cell to check possible move
   * @param col the column of the given cell to check possible move
   * @return
   */
  @Override
  public boolean canMove(int row, int col) {
    return false;
  }
}
