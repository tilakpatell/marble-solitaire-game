package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelUtil;

/**
 * Represents an abstract model for Solitaire games. This class implements the
 * MarbleSolitaireModel interface and provides common functionality for creating
 * and
 * manipulating the game board.
 */
public abstract class AbstractSolitaireModel implements MarbleSolitaireModel {

  protected int size;
  protected int sRow;
  protected int sCol;
  protected int score;
  protected SlotState[][] boardState;
  protected MarbleSolitaireModelUtil utils = new MarbleSolitaireModelUtil();
  protected MarbleSolitaireValidMoveStrategy strategy;

  /**
   * Constructs an AbstractSolitaireModel with the given size, empty slot position, and score.
   *
   * @param size the size of the game board
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @param score the initial score of the game
   * @throws IllegalArgumentException if the size is invalid or the empty slot position is invalid
   */
  protected AbstractSolitaireModel(int size, int sRow, int sCol, int score, MarbleSolitaireValidMoveStrategy strategy) {
    this.size = size;
    this.sRow = sRow;
    this.sCol = sCol;
    this.score = score;
    this.strategy = strategy;
    this.utils.checkInBoard(this, sRow, sCol);
    this.boardState = new SlotState[size][size];
    this.initializeBoard();
    
  }

  /**
   * Initializes the game board with the correct layout of marbles and empty
   * spaces.
   * This method should be implemented by each specific solitaire variant.
   */
  protected abstract void initializeBoard() throws IllegalArgumentException;


  @Override
  public int getBoardSize() {
    return this.size;
  }

  @Override
  public SlotState getSlotAt(int row, int col) throws IllegalArgumentException {
    try {
      return this.boardState[row][col];
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Invalid slot position");
    }
  }


  @Override
  public int getScore() {
    return this.score;
  }

  // Needs to be public for the tests
  /*
   * Returns the state of the board.
   */
  public SlotState[][] getBoardState() {
    return this.boardState;
  }

  // Needs to be public for the tests
  /*
   * Returns the state of the board at the given row and column.
   */
  public SlotState getBoardStateArray() {
    return this.boardState[sRow][sCol];
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {

    // Check if the move is in the board to avoid ArrayIndexOutOfBoundsException
    this.utils.checkInBoard(this, fromRow, fromCol, toRow, toCol);

    int distanceX = toRow - fromRow;
    int distanceY = toCol - fromCol;

    // Check if start cell is a marble and end cell is empty
    if (this.boardState[fromRow][fromCol] == SlotState.Marble
        && this.boardState[toRow][toCol] == SlotState.Empty) {
      // If move is in right direction
      if (distanceX == 2 && this.boardState[fromRow + 1][fromCol] == SlotState.Marble) {
        this.boardState[fromRow + 1][fromCol] = SlotState.Empty;
      }
      // If move is in left direction
      else if (distanceX == -2 && this.boardState[fromRow - 1][fromCol] == SlotState.Marble) {
        this.boardState[fromRow - 1][fromCol] = SlotState.Empty;
      }
      // If move is in down direction
      else if (distanceY == 2 && this.boardState[fromRow][fromCol + 1] == SlotState.Marble) {
        this.boardState[fromRow][fromCol + 1] = SlotState.Empty;
      }
      // If move is in up direction
      else if (distanceY == -2 && this.boardState[fromRow][fromCol - 1] == SlotState.Marble) {
        this.boardState[fromRow][fromCol - 1] = SlotState.Empty;
      }
      // If move is not in any of the above directions
      else {
        throw new IllegalArgumentException("This is not a valid move");
      }
    }
    // If start cell is not a marble or end cell is not empty
    else {
      throw new IllegalArgumentException("This is not a valid move");
    }

    this.boardState[fromRow][fromCol] = SlotState.Empty;
    this.boardState[toRow][toCol] = SlotState.Marble;
    this.score--;
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.boardState.length; i++) {
      for (int j = 0; j < this.boardState[i].length; j++) {
        if (this.boardState[i][j] == SlotState.Marble) {
          if (this.strategy.validMove(this, i, j, i + 2, j)
              || this.strategy.validMove(this, i, j, i - 2, j)
              || this.strategy.validMove(this, i, j, i, j + 2)
              || this.strategy.validMove(this, i, j, i, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  // Needs to be public for the tests
  /*
   * Returns the row of the empty slot.
   */
  public int getSCol() {
    return this.sCol;
  }

  // Needs to be public for the tests
  /*
   * Returns the column of the empty slot.
   */
  public int getSRow() {
    return this.sRow;
  }

  // Needs to be public for the tests
  /*
   * Returns the score of the game.
   */
  public void setScore(int num) {
    this.score = num;
  }

  // Needs to be public for the tests
  /*
   * Returns the size of the game board.
   */
  public int getSize() {
    return this.size;
  }

}
