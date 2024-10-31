package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Represents a text-based view for the Marble Solitaire game.
 */
public class MarbleSolitaireTextView extends AbstractView {

  /**
   * Constructs a MarbleSolitaireTextView with the given model.
   *
   * @param model the MarbleSolitaireModelState to be displayed
   * @throws IllegalArgumentException if the model is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructs a MarbleSolitaireTextView with the given model and custom output destination.
   *
   * @param model the MarbleSolitaireModelState to be displayed
   * @param appendable the output destination
   * @throws IllegalArgumentException if either the model or appendable is null
   */
  public MarbleSolitaireTextView(MarbleSolitaireModelState model, Appendable appendable) {
    super(model, appendable);
  }

}