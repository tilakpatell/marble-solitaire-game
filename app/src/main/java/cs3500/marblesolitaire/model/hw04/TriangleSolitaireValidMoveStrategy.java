package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a strategy for determining if a move is valid in a Triangle Solitaire game.
 */
public class TriangleSolitaireValidMoveStrategy implements MarbleSolitaireValidMoveStrategy {
  @Override
  public boolean validMove(MarbleSolitaireModel model, int fromRow, int fromCol, int toRow, int toCol) {
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0 ||
        fromRow >= model.getBoardSize() || fromCol > fromRow ||
        toRow >= model.getBoardSize() || toCol > toRow) {
      return false;
    }

    int rowDiff = Math.abs(fromRow - toRow);
    int colDiff = Math.abs(fromCol - toCol);

    if (!((rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2) || (rowDiff == 2 && colDiff == 2))) {
      return false;
    }

    int midRow = (fromRow + toRow) / 2;
    int midCol = (fromCol + toCol) / 2;

    return model.getSlotAt(fromRow, fromCol) == MarbleSolitaireModel.SlotState.Marble &&
        model.getSlotAt(toRow, toCol) == MarbleSolitaireModel.SlotState.Empty &&
        model.getSlotAt(midRow, midCol) == MarbleSolitaireModel.SlotState.Marble;
  }
}
