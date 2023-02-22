/**
 * This class represents a ChessBoardImp.
 * It implements the ChessBoard interface.
 * It contains implementation details
 */

import java.util.*;

public class ChessBoardImp implements ChessBoard{
  // instance variables
  private List<List<ChessPiece>> board;
  // create variable to represent the maximum number of rows and cols
  private final int boardDimension = 8;
  // create a representation of EMPTY - to help us return EMPTY when no piece on cell
  private final ChessPiece emptyCell = null;

  /**
   * Default constructor to create an empty board
   */
  public ChessBoardImp() {
    // create all the rows of the board
    this.board = new ArrayList<>(boardDimension);
    // for each row in the board - create all the columns and assign each cell to empty
    for (int k = 0; k < boardDimension; k++) {
      List<ChessPiece> newCol = new ArrayList<>(boardDimension);
      // for each cell in the column - add empty ChessPiece
      for (int c = 0; c< boardDimension; c++) {
        newCol.add(c, emptyCell);
      }
      // once done adding the cells, add the whole column
      board.add(k, newCol);

    }
  }

  /**
   * A constructor to create a Board with an initial position containing some number of pieces
   * @param initialBoard initial state of the board
   */
  public ChessBoardImp(List<List<ChessPiece>> initialBoard) {
    this.board = new ArrayList<>(initialBoard);
  }

  /**
   * Method to get a ChessPiece at a specific location
   * @param row
   * @param col
   * @return
   */
  @Override
  public ChessPiece getChessPieceAt(int row, int col) {
    return board.get(row).get(col);
  }

  /**
   *
   * @param startRow
   * @param moveRow
   * @param startCol
   * @param moveCol
   */
  @Override
  public void moveChessPiece(int startRow, int moveRow, int startCol, int moveCol) {
    // find if there is a chessPiece at start cell
    ChessPiece movePiece = getChessPieceAt(startRow, startCol);
    // check if there is a piece to move there
    if (movePiece == null) {
      throw new IllegalArgumentException("No piece here!");
    }
    // check if the piece can move to the new location
    if (movePiece.canMoveV2(moveRow, moveCol)) {
      // move piece
      board.get(moveRow).set(moveCol, movePiece);
      // update previous position to null
      board.get(startRow).set(startCol, emptyCell);
    }
  }
}
