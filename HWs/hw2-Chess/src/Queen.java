/**
 * This class represents a Queen chess piece.
 * It offers all the operations mandated by
 * the ChessPiece interface and AbstractChessPiece class.
 */
public class Queen extends AbstractChessPiece{

  /**
   * Constructs a Queen ChessPiece matching the
   * superclass AbstractChessPiece's constructor
   * @param row   the initial row position of the piece
   * @param col   the initial column position of the piece
   * @param color the color of the piece
   */
  public Queen(int row, int col, Color color) {
    super(row, col, color);
  }

  @Override
  public boolean canMove(int row, int col) {
    // check if the cell is within the chess board boarder
    if (cellOutsideBoard(row, col)){
      return false;
    }
    // check if piece can move horizontal or vertical or diagonal
    return canMoveHorizontal(row) || canMoveVertical(col) || canMoveDiagonal(row, col);
  }
}
