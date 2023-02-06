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
}
