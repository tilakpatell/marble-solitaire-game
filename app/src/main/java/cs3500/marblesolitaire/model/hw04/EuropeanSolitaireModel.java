package cs3500.marblesolitaire.model.hw04;

import java.util.Arrays;

/**
 * Represents a European Solitaire game model.
 */
public class EuropeanSolitaireModel extends AbstractSolitaireModel {

  /**
   * Default constructor: creates a European Solitaire board with side length 3 and empty slot at the center.
   */
  public EuropeanSolitaireModel() {
    this(3, 3, 3);
  }

  /**
   * Constructor with custom side length: creates a board with given side length and empty slot at the center.
   * @param sideLength the side length of the board
   * @throws IllegalArgumentException if side length is even or less than 3
   */
  public EuropeanSolitaireModel(int sideLength) {
    this(sideLength, (sideLength * 3 - 2) / 2, (sideLength * 3 - 2) / 2);
  }

  /**
   * Constructor with custom empty slot position: creates a board with side length 3 and empty slot at given position.
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if the position is invalid
   */
  public EuropeanSolitaireModel(int row, int col) {
    this(3, row, col);
  }

  /**
   * Constructor with custom side length and empty slot position.
   * @param sideLength the side length of the board
   * @param row the row of the empty slot
   * @param col the column of the empty slot
   * @throws IllegalArgumentException if side length is even or less than 3, or if the position is invalid
   */
  public EuropeanSolitaireModel(int sideLength, int row, int col) throws IllegalArgumentException {
    super((sideLength * 3) - 2, row, col, calculateInitialScore(sideLength), new EuropeanSolitaireValidMoveStrategy());
    if (sideLength % 2 == 0 || sideLength < 3) {
      throw new IllegalArgumentException("Side length must be a positive odd number at least 3.");
    }
  }


  @Override
  protected void initializeBoard() throws IllegalArgumentException {
    this.boardState = new SlotState[this.size][this.size];

    for (SlotState[] row : this.boardState) {
      Arrays.fill(row, SlotState.Invalid);
    }
    int sideLength = (this.size-1)/2;
    int counter = sideLength - 1;
    int middleRows = sideLength;
    for (SlotState[] row : this.boardState) {
      if(counter == 0 || middleRows == 0){
        if(middleRows != 0){
          Arrays.fill(row, SlotState.Marble);
          middleRows--;
        }
        else{
          counter++;
          Arrays.fill(row, counter, this.size - counter , SlotState.Marble);
        }
      }
      else{
        Arrays.fill(row, counter, this.size - counter , SlotState.Marble);
        counter--;
      }
    }

    // Set the empty slot in valid positions (where marbles can be placed)
    if (this.boardState[this.sRow][this.sCol] == SlotState.Marble) {
      this.boardState[this.sRow][this.sCol] = SlotState.Empty;
    } else {
      throw new IllegalArgumentException("Invalid empty cell position");
    }

    // Set the empty slot
    this.boardState[this.sRow][this.sCol] = SlotState.Empty;
  }

  /**
   * Returns the size of the game board.
   *
   * @return the size of the board
   */
  private static int calculateInitialScore(int armThickness) {
    int boardSize = armThickness * 3 - 2;
    int cornerSize = armThickness - 1;
    return boardSize * boardSize - 4 * cornerSize * cornerSize - 1;
}





}
