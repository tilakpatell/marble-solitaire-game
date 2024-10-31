package cs3500.marblesolitaire.model.hw04;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a strategy for determining if a move is valid in a Marble Solitaire game.
 */
public interface MarbleSolitaireValidMoveStrategy {

    /**
     * Determines if the move from the given position to the other given position is valid.
     * @param model the model to validate the move on
     * @param fromRow the row of the marble to move
     * @param fromCol the column of the marble to move
     * @param toRow the row to move the marble to
     * @param toCol the column to move the marble to
     * @return true if the move is valid, false otherwise
     */
    public boolean validMove(MarbleSolitaireModel model, int fromRow, int fromCol, int toRow, int toCol);

}
