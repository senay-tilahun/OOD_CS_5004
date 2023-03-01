/**
 * Senay Tilahun
 * Spring CS 5004 - H2 - ChessPiece interface
 * This interface contains all protocols for concrete Chess Pieces.
 * */
public interface ChessPiece {

  /**
   * Setter method for row
   * @param row the new row
   */
  public void setRow(int row);

  /**
   * Setter method for col
   * @param col the new col
   */
  public void setCol(int col);

  /**
   * Returns the current row of the ChessPiece on the board.
   * @return the current row of the ChessPiece
   */
  int getRow();

  /**
   * Returns the current Column of the ChessPiece on the board.
   * @return the current Column of the ChessPiece
   */
  int getColumn();

  /**
   * Returns the color of the ChessPiece
   * @return the color of the ChessPiece
   * */
  Color getColor();

  /**
   * Computes if a ChessPiece can move to a given cell (row, col)
   * @param row the row of the given cell to check possible move
   * @param col the column of the given cell to check possible move
   * @return true if the ChessPiece can move to the given cell, false otherwise
   * */
  boolean canMove(int row, int col);

  /**
   * Computes if a ChessPiece can kill another ChessPiece on the board
   * starting from where it is currently
   * @param piece the provided ChessPiece to check whether this ChessPiece can kill
   * @return true if the ChessPiece can kill the given ChessPiece, false otherwise
   * */
  boolean canKill(ChessPiece piece);

  /**
   * Updated canMove method - takes into account if move is blocked
   * @param moveRow the row of the given cell to check possible move
   * @param moveCol the column of the given cell to check possible move
   * @return true if the ChessPiece can move to the given cell, false otherwise
   */
  boolean canMoveV2(ChessBoard board, int moveRow, int moveCol);
}
