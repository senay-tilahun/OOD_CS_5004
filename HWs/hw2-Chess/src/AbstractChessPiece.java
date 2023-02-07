/**
 * This class represents an AbstractChessPiece.
 * It implements the ChessPiece interface.
 * It contains implementation details of
 */

public abstract class AbstractChessPiece implements ChessPiece{
  int row;
  int column;
  Color color;

  /**
   * Constructs an AbstractChessPiece
   * @param row the initial row position of the piece
   * @param col the initial column position of the piece
   * @param color the color of the piece
   */
  public AbstractChessPiece(int row, int col, Color color){
    this.row = row;
    this.column = col;
    this.color = color;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  /*
  @Override
  public boolean canMove(int row, int col) {
    return false;
  }
  * */

  @Override
  public boolean canKill(ChessPiece piece) {
    // first check if the pieces are opposing colors
    if (this.color != piece.getColor()){
      // if opposing colors, check if this can move to piece position
      return this.canMove(piece.getRow(), piece.getColumn());
    }
    // if this and piece are same color, cannot kill
    return false;
  }

  /**
   * Helper method to confirm that a given cell is within the chess board boarders
   * @param row the row of the given cell to check
   * @param col the col of the given cell to check
   * @return false if the given cell is not outside the board, throws exception otherwise
   */
  public boolean cellOutsideBoard(int row, int col) {
    // if cell is outside board - throw exception
    if (row < 0 || row >= 8 || col < 0 || col >= 8) {
      throw new IllegalArgumentException("cell is outside board");
    } // else return false
    return false;
  }

  /**
   * Helper method to check if a given ChessPiece can move diagonally on the board
   * towards a given cell
   * @param row the given cell row to check
   * @param col the given cell column to check
   * @return true if the ChessPiece can move diagonally to the cell, false otherwise
   */
  public boolean canMoveDiagonal(int row, int col) {
    // check if the absolute value of the difference in row and column is the same
    return Math.abs(this.getRow() - row) == Math.abs(this.getColumn() - col);
  }

  /**
   * Helper method to check if a given ChessPiece can move horizontally on the board
   * towards a given cell
   * @param row the given cell row to check
   * @return true if the ChessPiece can move horizontally to the cell, false otherwise
   */
  public boolean canMoveHorizontal(int row) {
    // check if this piece and the given cell have the same row
    return this.getRow() == row;
  }

  /**
   * Helper method to check if a given ChessPiece can move vertically on the board
   * towards a given cell
   * @param col the given cell column to check
   * @return true if the ChessPiece can move vertically to the cell, false otherwise
   */
  public boolean canMoveVertical(int col) {
    // check if this piece and the given cell have the same column
    return this.getColumn() == col;
  }
}
