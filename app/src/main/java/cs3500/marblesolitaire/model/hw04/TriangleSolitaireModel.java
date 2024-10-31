package cs3500.marblesolitaire.model.hw04;

import java.util.Arrays;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Represents a model for a triangular solitaire game.
 */
public class TriangleSolitaireModel extends AbstractSolitaireModel {

  /**
   * Constructs a default TriangleSolitaireModel with dimension 5 and empty slot
   * at the top.
   */
  public TriangleSolitaireModel() {
    this(5, 0, 0);
  }

  /**
   * Constructs a TriangleSolitaireModel with specified dimension and empty slot
   * at the top.
   *
   * @param dimensions the number of rows in the triangular board
   * @throws IllegalArgumentException if the dimension is not positive
   */
  public TriangleSolitaireModel(int dimensions) {
    this(dimensions, 0, 0);
  }

  /**
   * Constructs a TriangleSolitaireModel with dimension 5 and specified empty slot
   * position.
   *
   * @param sRow the row of the empty slot
   * @param sCol the column of the empty slot
   * @throws IllegalArgumentException if the specified position is invalid
   */
  public TriangleSolitaireModel(int sRow, int sCol) {
    this(5, sRow, sCol);
  }

  /**
   * Constructs a TriangleSolitaireModel with specified dimension and empty slot
   * position.
   *
   * @param dimensions the number of rows in the triangular board
   * @param sRow       the row of the empty slot
   * @param sCol       the column of the empty slot
   * @throws IllegalArgumentException if the dimension or position is invalid
   */
  public TriangleSolitaireModel(int dimensions, int sRow, int sCol) {
    super(dimensions, sRow, sCol, ((dimensions * (dimensions + 1)) / 2) - 1, new TriangleSolitaireValidMoveStrategy());
    if (dimensions <= 0) {
      throw new IllegalArgumentException("Dimension must be positive");
    }
  }

  @Override
  protected void initializeBoard() {
    this.boardState = new SlotState[this.size][this.size];

    for (SlotState[] row : this.boardState) {
      Arrays.fill(row, SlotState.Invalid);
    }

    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j <= i; j++) {
        this.boardState[i][j] = SlotState.Marble;
      }
    }

    if (this.boardState[this.sRow][this.sCol] == SlotState.Marble) {
      this.boardState[this.sRow][this.sCol] = SlotState.Empty;
    }
    else{
      throw new IllegalArgumentException("This is a invalid position");
    }
  }

  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    this.utils.checkInBoard(this, fromRow, fromCol, toRow, toCol);

    if (!this.strategy.validMove(this, fromRow, fromCol, toRow, toCol)) {
      throw new IllegalArgumentException("Invalid move");
    }

    SlotState[][] board = this.boardState;
    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;

    board[fromRow][fromCol] = SlotState.Empty;
    board[midRow][midCol] = SlotState.Empty;
    board[toRow][toCol] = SlotState.Marble;

    setScore(this.score - 1);
  }

  @Override
  public boolean isGameOver() {
    for (int i = 0; i < this.size; i++) {
      for (int j = 0; j <= i; j++) {
        if (getSlotAt(i, j) == SlotState.Marble) {
          if (this.strategy.validMove(this, i, j, i + 2, j) ||
              this.strategy.validMove(this, i, j, i - 2, j) ||
              this.strategy.validMove(this, i, j, i, j + 2) ||
              this.strategy.validMove(this, i, j, i, j - 2) ||
              this.strategy.validMove(this, i, j, i + 2, j + 2) ||
              this.strategy.validMove(this, i, j, i - 2, j - 2)) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
