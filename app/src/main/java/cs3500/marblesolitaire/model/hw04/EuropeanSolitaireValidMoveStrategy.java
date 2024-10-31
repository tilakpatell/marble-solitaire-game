package cs3500.marblesolitaire.model.hw04;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;

/**
 * Represents a strategy for determining if a move is valid in a European Solitaire game.
 */
public class EuropeanSolitaireValidMoveStrategy implements MarbleSolitaireValidMoveStrategy {
  @Override
  public boolean validMove(MarbleSolitaireModel model, int fromRow, int fromCol, int toRow, int toCol) {
    try{
        if (model.getSlotAt(fromRow, fromCol) == SlotState.Invalid || model.getSlotAt(toRow, toCol) == SlotState.Invalid) {
            return false;
        }
    }
    catch(IllegalArgumentException e){
        return false;
    }

    int rowDiff = Math.abs(fromRow - toRow);
    int colDiff = Math.abs(fromCol - toCol);

    if ((rowDiff == 2 && colDiff == 0) || (rowDiff == 0 && colDiff == 2)) {
      int middleRow = (fromRow + toRow) / 2;
      int middleCol = (fromCol + toCol) / 2;

      return model.getSlotAt(fromRow, fromCol) == MarbleSolitaireModel.SlotState.Marble
          && model.getSlotAt(toRow, toCol) == MarbleSolitaireModel.SlotState.Empty
          && model.getSlotAt(middleRow, middleCol) == MarbleSolitaireModel.SlotState.Marble;
    }

    return false;
  }
}
