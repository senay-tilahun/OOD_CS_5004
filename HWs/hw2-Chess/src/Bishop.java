/**
 * This class represents a Bishop chess piece.
 * It offers all the operations mandated by
 * the ChessPiece interface and AbstractChessPiece class.
 */
public class Bishop extends AbstractChessPiece {


  /**
   * Constructs a Bishop ChessPiece matching the
   * superclass AbstractChessPiece's constructor
   * @param row   the initial row position of the piece
   * @param col   the initial column position of the piece
   * @param color the color of the piece
   */
  public Bishop(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    // check if the cell is within the chess board boarder
    if (cellOutsideBoard(row, col)){
      return false;
    }
    // Utilize method in AbstractChessPiece to check if Bishop can move diagonally
    return canMoveDiagonal(row, col);
  }
}
