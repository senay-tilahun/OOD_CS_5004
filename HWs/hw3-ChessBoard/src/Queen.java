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
    // check if piece can move horizontal or vertical or diagonal
    return canMoveHorizontal(row) || canMoveVertical(col) || canMoveDiagonal(row, col);
  }

  /**
   * Updated canMove method - takes into account if move is blocked
   *
   * @param moveRow the row of the given cell to check possible move
   * @param moveCol the column of the given cell to check possible move
   * @return true if the ChessPiece can move to the given cell, false otherwise
   */
  @Override
  public boolean canMoveV2(ChessBoard board, int moveRow, int moveCol) {
    if (!this.canMove(moveRow, moveCol)) { return false;}
    //
    boolean one = pieceExistsStraight(board, moveRow, this.getColumn(), moveCol);
    boolean two = pieceExistsStraight(board, moveCol, this.getRow(), moveRow);
    boolean three = pieceExistsDiag(board, this.getRow(), moveRow, this.getColumn(), moveCol);
    return one || two || three;
  }
}
