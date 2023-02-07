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


  @Override
  public boolean canMove(int row, int col) {
    // check if the cell is within the chess board boarder
    if (cellOutsideBoard(row, col)){
      return false;
    }
    // check if it can move 2 steps forward and right
    int rowDifference = Math.abs(this.getRow() - row);
    int colDifference = Math.abs(this.getColumn() - col);
    return (rowDifference == 2 && colDifference == 1) || (rowDifference == 1 && colDifference == 2);
  }
}
