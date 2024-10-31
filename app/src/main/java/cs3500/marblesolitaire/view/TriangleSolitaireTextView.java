package cs3500.marblesolitaire.view;


import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a text-based view for the Triangle Solitaire game.
 */
public class TriangleSolitaireTextView extends AbstractView {


  /**
   * Constructs a TriangleSolitaireTextView with the given model.
   *
   * @param model the MarbleSolitaireModelState to be displayed
   * @throws IllegalArgumentException if the model is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructs a TriangleSolitaireTextView with the given model and custom output destination.
   * @param model the MarbleSolitaireModelState to be displayed
   * @param appendable the output destination
   * @throws IllegalArgumentException if either the model or appendable is null
   */
  public TriangleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) throws IllegalArgumentException {
    super(model, appendable);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    int size = this.model.getBoardSize();

    for (int i = 0; i < size; i++) {
      StringBuilder row = new StringBuilder();
      
      for (int j = 0; j < size - i - 1; j++) {
        row.append(" ");
      }

      for (int j = 0; j < size; j++) {
        switch (this.model.getSlotAt(i, j)) {
          case Invalid -> row.append("");
          case Marble -> row.append(" O");
          case Empty -> row.append(" _");
        }
      }
      result.append(row.substring(1)).append("\n");
    }
    return result.substring(0, result.length() - 1);
  }

}
