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

  /**
   * Computes if a ChessPiece can move to a given cell (row, col)
   * @param row the row of the given cell to check possible move
   * @param col the column of the given cell to check possible move
   * @return true if the ChessPiece can move to the given cell, false otherwise
   * */
  @Override
  public boolean canMove(int row, int col) {
    // check if the cell is within the chess board boarder
    if (cellOutsideBoard(row, col)){
      return false;
    }
    // utilize methods in Abstract class to check if Rook can move vertical or horizontal
    return canMoveHorizontal(row) || canMoveVertical(col);
  }

  /**
   * Updated canMove method - takes into account if move is blocked
   * @param moveRow the row of the given cell to check possible move
   * @param moveCol the column of the given cell to check possible move
   * @return true if the ChessPiece can move to the given cell, false otherwise
   */
  @Override
  public boolean canMoveV2(ChessBoard board, int moveRow, int moveCol) {
    // if the piece can't move to the new cell return false
    if (!this.canMove(moveRow, moveCol)) { return false;}

    // check if move is horizontal or vertical
    if (Math.abs(this.getRow() - moveRow) == 1) {
      // horizontal movement - row is constant
      return !this.pieceExistsStraight(board, moveRow, this.getColumn(), moveCol);
    }
    // else vertical move
    return !this.pieceExistsStraight(board, moveCol, this.getRow(), moveRow);
  }
}
