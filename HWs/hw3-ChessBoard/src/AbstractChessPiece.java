/**
 * This class represents an AbstractChessPiece.
 * It implements the ChessPiece interface.
 * It contains implementation details
 */

public abstract class AbstractChessPiece implements ChessPiece{
  private int row;
  private int column;
  private Color color;

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

  /**
   * Setter method for row
   * @param row the new row
   */
  @Override
  public void setRow(int row) {
    this.row = row;
  }

  /**
   * Setter method for col
   * @param col the new col
   */
  @Override
  public void setCol(int col) {
    this.column = col;
  }

  /**
   * Returns the current row of the ChessPiece on the board.
   * @return the current row of the ChessPiece
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Returns the current Column of the ChessPiece on the board.
   * @return the current Column of the ChessPiece
   */
  @Override
  public int getColumn() {
    return this.column;
  }

  /**
   * Returns the color of the ChessPiece
   * @return the color of the ChessPiece
   * */
  @Override
  public Color getColor() {
    return this.color;
  }

  /**
   * Computes if a ChessPiece can kill another ChessPiece on the board
   * starting from where it is currently
   * @param piece the provided ChessPiece to check whether this ChessPiece can kill
   * @return true if the ChessPiece can kill the given ChessPiece, false otherwise
   * */
  @Override
  public boolean canKill(ChessPiece piece) {
    // first check if the pieces are opposing colors
    if (this.getColor() != piece.getColor()){
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
      throw new IllegalArgumentException("Cell is outside board");
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

  /**
   * Helper method to check if there is any piece diagonally from a start cell to end cell
   * @param board the chess board to check
   * @param fromRow the start cell row
   * @param toRow the end cell row
   * @param fromCol the start cell col
   * @param toCol the end cell col
   * @return true if there is a piece, false otherwise
   */
  public boolean pieceExistsDiag(ChessBoard board, int fromRow, int toRow, int fromCol, int toCol){
    // first check which direction the diagonal is
    int rowDir;
    int colDir;
    if (toRow - fromRow > 0) {
      rowDir = 1;
    } else {
      rowDir = -1;
    }
    int r = fromRow + rowDir;

    if (toCol - fromCol > 0) {
      colDir = 1;
    } else {
      colDir = -1;
    }

    int c = fromCol + colDir;

    // check if there is a piece in those directions, until we fet to our destination
    while (r != toRow && c != toCol) {
      // check the board at r, c and see if there is a chesspiece
      if (board.getChessPieceAt(r, c) != null) {
        return true;
      }
      // increment current row and col to check
      r += rowDir;
      c += colDir;
    }
  // if we leave the loop and can't find a piece, return false
    return false;
  }

  /**
   * Helper method to check if there is any piece
   * horizontally or vertically from a start cell to end cell
   * @param board the chess board to check
   * @param constant the row (horizontal move) /col (vertical move) that remains constant
   * @param from the start cell variable coordinate
   * @param to the end cell variable coordinate
   * @return true if there is a piece, false otherwise
   */
  public boolean pieceExistsStraight(ChessBoard board, int constant, int from, int to) {
    // define the begin and end columns to check
    int begin = Math.min(from, to);
    int end = Math.max(from, to);
    // iterate from begin to end and check
    for (int i= begin; i < end; i++) {
      if (board.getChessPieceAt(row, i) != null) {
        return true;
      }
    }
    return false;
  }



}
