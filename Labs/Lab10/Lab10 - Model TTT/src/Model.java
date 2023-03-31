/**
 * This interface contains all protocols for concrete Model class for TTT game.
 */
public interface Model {

  /**
   * Places a player in the specified cell location.
   * @param row the row of the cell to place
   * @param column the column of the cell to place
   * @param player the player whose turn it is currently to move
   * @throws IllegalStateException if game is over, or if the cell is occupied
   * @throws IndexOutOfBoundsException if the cell specified is out of bounds
   */
  void move(int column, int row, Player player);

  /**
   * Determines which Player's turn it currently is
   * @return the Player whose turn it is
   * @throws IllegalStateException if the game is over
   */
  Player isTurn();

  /**
   * Returns a 2D representation of the state of the game.
   * The first index is the column, the second index is the row.
   * The array returned is newly allocated, so mutating it will not have an effect
   * on the original board
   * @return a copy of the board's state
   */
  Player[][] boardState();

  /**
   * Determines whether the game is over
   * @return true if game is over, false otherwise
   */
  boolean isGameOver();

  /**
   * Returns the winner of the game or NA if game is a tie.
   * @return the winner of the game or NA
   * @throws IllegalStateException if the game is not over
   */
  Player getWinner();
}
