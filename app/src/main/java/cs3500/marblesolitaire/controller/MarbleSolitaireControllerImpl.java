package cs3500.marblesolitaire.controller;

import java.io.IOException;
import java.util.Scanner;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Implementation of the MarbleSolitaireController interface. This class controls the flow of the
 * Marble Solitaire game, handling user input and updating the model and view accordingly.
 */
public class MarbleSolitaireControllerImpl implements MarbleSolitaireController {

  private final MarbleSolitaireModel model;
  private final MarbleSolitaireView view;
  private final MarbleSolitaireControllerUtils utils;
  private final Scanner scanner;

  /**
   * Constructs a MarbleSolitaireControllerImpl with the given model, view, and readable input.
   *
   * @param model the marble solitaire model
   * @param view the marble solitaire view
   * @param readable the source of input
   * @throws IllegalArgumentException if model, view, or readable is null
   */
  public MarbleSolitaireControllerImpl(MarbleSolitaireModel model, MarbleSolitaireView view,
      Readable readable) throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Model, view, or readable cannot be null");
    }

    this.model = model;
    this.view = view;
    this.utils = new MarbleSolitaireControllerUtils();
    this.scanner = new Scanner(readable);
  }

  /**
   * Plays the game of Marble Solitaire. This method controls the game loop, rendering the board,
   * handling user input, and updating the game state until the game is over.
   *
   * @throws IllegalStateException if there's an error rendering the board or handling input
   */
  @Override
  public void playGame() throws IllegalStateException {
    try {
      while (!this.model.isGameOver()) {
        this.view.renderBoard();
        this.view.renderMessage("Score: " + this.model.getScore());
        this.view.renderMessage("Enter a move: ");

        String fromRow = getNextNum();
        if (fromRow == null)
          return;

        String fromCol = getNextNum();
        if (fromCol == null)
          return;

        String toRow = getNextNum();
        if (toRow == null)
          return;

        String toCol = getNextNum();
        if (toCol == null)
          return;

        utils.checkMove(view, model, toCol, toRow, fromCol, fromRow);
      }

      // Game over
      this.view.renderBoard();
      this.view.renderMessage("Game over!");
      this.view.renderMessage("Score: " + this.model.getScore());

    } catch (IOException e) {
      throw new IllegalStateException("Error rendering board");
    }
  }

  /**
   * Retrieves the next number from the input. This method reads the next token from the scanner,
   * checks for bad input, checks for quit command, and returns the input as a string.
   *
   * @return the next input as a String, or null if there is no more input
   * @throws IllegalStateException if there's an error handling the input or if the quit command is
   *         entered
   */
  private String getNextNum() {
    if (scanner.hasNext()) {
      String next = scanner.next();
      utils.checkBadInput(view, next);
      utils.checkQuit(view, model, next);
      return next;
    }
    return null;
  }
}
