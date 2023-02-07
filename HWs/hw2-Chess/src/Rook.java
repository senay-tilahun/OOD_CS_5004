/**
 * This class represents a Rook chess piece.
 * It offers all the operations mandated by
 * the ChessPiece interface and AbstractChessPiece class.
 */
public class Rook extends AbstractChessPiece{

  /**
   * Constructs a Rook ChessPiece matching the
   * superclass AbstractChessPiece's constructor
   * @param row   the initial row position of the piece
   * @param col   the initial column position of the piece
   * @param color the color of the piece
   */
  public Rook(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    // check if the cell is within the chess board boarder
    if (cellOutsideBoard(row, col)){
      return false;
    }
    // utilize methods in Abstract class to check if Rook can move vertical or horizontal
    return canMoveHorizontal(row) || canMoveVertical(col);
  }
}
