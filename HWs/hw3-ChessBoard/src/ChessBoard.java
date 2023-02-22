/**
 * Senay Tilahun
 * Spring CS 5004 - H3 - ChessBoard interface
 * This interface contains all protocols for concrete Chess Board.
 * */
public interface ChessBoard {

  /**
   * Getter to return the piece at a given position,
   * or "EMPTY" if there is no piece at that position.
   * @param row the row of position cell
   * @param col the column  of position cell
   * @return piece or empty
   */
  ChessPiece getChessPieceAt(int row, int col);

//  /**
//   *
//   * @param row
//   * @param col
//   * @param piece
//   */
//  void setChessPieceAt(int row, int col, ChessPiece piece);

  /**
   *
   * @param startRow
   * @param moveRow
   * @param startCol
   * @param moveCol
   */
  void moveChessPiece(int startRow, int moveRow, int startCol, int moveCol);
}
