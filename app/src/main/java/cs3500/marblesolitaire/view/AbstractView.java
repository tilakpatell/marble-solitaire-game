package cs3500.marblesolitaire.view;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState;

/**
 * Abstract base class for Marble Solitaire views.
 * Implements common functionality for different view implementations.
 */
public abstract class AbstractView implements MarbleSolitaireView {

  /**
   * The model state to be displayed.
   */
  protected final MarbleSolitaireModelState model;

  /**
   * The output destination for the view.
   */
  protected final Appendable appendable;

  /**
   * Constructs an AbstractView with the given model and System.out as the default output.
   *
   * @param model the model state to be displayed
   * @throws IllegalArgumentException if the model is null
   */
  AbstractView(MarbleSolitaireModelState model) throws IllegalArgumentException{
    if (model == null) {
      throw new IllegalArgumentException("There is no model associated");
    }

    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Constructs an AbstractView with the given model and custom output destination.
   *
   * @param model the model state to be displayed
   * @param appendable the output destination
   * @throws IllegalArgumentException if either the model or appendable is null
   */
  AbstractView(MarbleSolitaireModelState model, Appendable appendable) throws IllegalArgumentException {
    if (model == null || appendable == null) {
      throw new IllegalArgumentException("There is no model or appendable associated");
    }

    this.model = model;
    this.appendable = appendable;
  }

  /**
   * Returns a string representation of the current game board.
   *
   * @return a string representation of the game board
   */
  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    int size = model.getBoardSize();

    for (int i = 0; i < size; i++) {
      StringBuilder row = new StringBuilder();
      for (int j = 0; j < size; j++) {
        switch (model.getSlotAt(i, j)) {
          case Invalid -> row.append("  ");
          case Marble -> row.append(" O");
          case Empty -> row.append(" _");
        }
      }
      result.append(row.substring(1)).append("\n");
    }
    return result.substring(0, result.length() - 1);
  }
 
  /**
   * Renders the current game board to the output destination.
   *
   * @throws IOException if there's an error writing to the output
   */
  @Override
  public void renderBoard() throws IOException {
    try {
      this.appendable.append(this.toString()).append("\n");
    } catch (Exception e) {
      throw new IOException(e);
    }
  }

  /**
   * Renders a message to the output destination.
   *
   * @param message the message to be rendered
   * @throws IOException if there's an error writing to the output
   */
  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.appendable.append(message + "\n");
    } catch (Exception e) {
      throw new IOException(e);
    }
  }
}