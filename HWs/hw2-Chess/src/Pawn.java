/**
 * This class represents a Pawn chess piece.
 * It offers all the operations mandated by
 * the ChessPiece interface and AbstractChessPiece class.
 */
public class Pawn extends AbstractChessPiece{

  /**
   * Constructs a Pawn ChessPiece matching the
   * superclass AbstractChessPiece's constructor
   * @param row   the initial row position of the piece
   * @param col   the initial column position of the piece
   * @param color the color of the piece
   */
  public Pawn(int row, int col, Color color) {
    super(row, col, color);
  }

  /**
   * Overrides the default implementation of canKill in AbstractChessPiece
   * @param piece the provided ChessPiece to check whether this Pawn can kill
   * @return true if the ChessPiece can kill the given ChessPiece, false otherwise
   */
  @Override
  public boolean canKill(ChessPiece piece){
    // if it can move diagonally to this piece
    // first check if the pieces are opposing colors
    if (this.color != piece.getColor()){
      // if opposing colors, check if this can move to piece position
      return this.canMoveDiagonal(piece.getRow(), piece.getColumn());
    }
    // if this and piece are same color, cannot kill
    return false;
  }

  /**
   * Helper method for canKill method of this Pawn
   * Checks if this Pawn can move diagonally to the given cell row and col
   * @param row the given cell row to check
   * @param col the given cell column to check
   * @return true if the Pawn can move diagonally to this, false otherwise
   */
  private boolean canMoveDiagonal(int row, int col){
    // check if this Pawn can move diagonally
    return false;
  }


  @Override
  public boolean canMove(int row, int col) {
    // if at initial position can move two cells forward
    // else - can only move one piece forward
    return false;
  }
}
