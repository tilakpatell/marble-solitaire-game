package cs3500.marblesolitaire.controller;

import java.io.IOException;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelUtil;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Utility class for the MarbleSolitaireController. Provides helper methods for checking user input
 * and handling game moves.
 */
public class MarbleSolitaireControllerUtils {

  MarbleSolitaireModelUtil modelUtils = new MarbleSolitaireModelUtil();

  /**
   * Checks if the user has entered a quit command.
   *
   * @param view the game view
   * @param model the game model
   * @param input the user input to check
   * @throws IllegalStateException if there's an error rendering the message
   */
  public void checkQuit(MarbleSolitaireView view, MarbleSolitaireModel model, String input)
      throws IllegalStateException {
    if (input.equalsIgnoreCase("Q")) {
      this.errorMessage(view, "Game quit!" + "\nState of the Game: \n" + view.toString()
          + "\nScore: " + model.getScore());
    }
  }

  /**
   * Checks if the user input is valid.
   *
   * @param view the game view
   * @param input the user input to check
   * @throws IllegalStateException if there's an error rendering the message
   */
  public void checkBadInput(MarbleSolitaireView view, String input) throws IllegalStateException {
    try {
      Integer.parseInt(input);
    } catch (NumberFormatException e) {
      if (!input.equalsIgnoreCase("Q")) {
        this.errorMessage(view, "Invalid input. Please enter a valid input.");
      }
    }
  }

  /**
   * Checks and executes a move based on user input.
   *
   * @param view the game view
   * @param model the game model
   * @param utils the model utility
   * @param fromRow the starting row of the move
   * @param fromCol the starting column of the move
   * @param toRow the ending row of the move
   * @param toCol the ending column of the move
   * @throws IllegalArgumentException if the move is invalid
   * @throws IllegalStateException if there's an error rendering the message
   */
  public void checkMove(MarbleSolitaireView view, MarbleSolitaireModel model,
     String fromRow, String fromCol, String toRow, String toCol)
      throws IllegalArgumentException {
    int fromRowChecked = this.invalidValue(Integer.parseInt(fromRow), model, view);
    int fromColChecked = this.invalidValue(Integer.parseInt(fromCol), model, view);
    int toRowChecked = this.invalidValue(Integer.parseInt(toRow), model, view);
    int toColChecked = this.invalidValue(Integer.parseInt(toCol), model, view);
    try {
      model.move(fromRowChecked, fromColChecked, toRowChecked, toColChecked);
    } catch (IllegalArgumentException e) {
      this.errorMessage(view,
          "Invalid move. Play again. Marbles should jump over another marble to a valid empty space.");
    }
  }

  /**
   * Validates if a given value is within the board's bounds.
   *
   * @param num the value to check
   * @param model the game model
   * @param view the game view
   * @param utils the model utility
   * @return the validated value
   * @throws IllegalArgumentException if the value is outside the board's bounds
   * @throws IllegalStateException if there's an error rendering the message
   */
  private int invalidValue(int num, MarbleSolitaireModel model, MarbleSolitaireView view) throws IllegalArgumentException {
    try {
      this.modelUtils.checkInBoard(model, num);
    } catch (IllegalArgumentException e) {
      this.errorMessage(view, "Invalid move. Play again. Select a cell inside the board");
    }
    return num;
  }

  /**
   * Renders an error message to the view.
   *
   * @param view the game view
   * @param message the error message to display
   * @throws IllegalStateException if there's an error rendering the message
   */
  private void errorMessage(MarbleSolitaireView view, String message) throws IllegalStateException {
    try {
      view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Error rendering message");
    }
  }
}
