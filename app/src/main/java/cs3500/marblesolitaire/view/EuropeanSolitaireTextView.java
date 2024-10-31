package cs3500.marblesolitaire.view;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;

/**
 * Represents a text-based view for the European Solitaire game.
 * This class extends the AbstractView to provide specific functionality for European Solitaire.
 */
public class EuropeanSolitaireTextView extends AbstractView {

  /**
   * Constructs a EuropeanSolitaireTextView with the given model.
   *
   * @param model the MarbleSolitaireModel to be displayed
   * @throws IllegalArgumentException if the model is null
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModel model) throws IllegalArgumentException {
    super(model);
  }

  /**
   * Constructs a EuropeanSolitaireTextView with the given model and custom output destination.
   *
   * @param model the MarbleSolitaireModel to be displayed
   * @param appendable the output destination
   * @throws IllegalArgumentException if either the model or appendable is null
   */
  public EuropeanSolitaireTextView(MarbleSolitaireModel model, Appendable appendable) throws IllegalArgumentException {
    super(model, appendable);
  }
  
}