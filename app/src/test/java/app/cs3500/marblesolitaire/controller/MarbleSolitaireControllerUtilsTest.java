package app.cs3500.marblesolitaire.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerUtils;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

/**
 * Test class for MarbleSolitaireControllerUtils. This class contains unit tests for the utility
 * methods used in the Marble Solitaire game controller.
 */
class MarbleSolitaireControllerUtilsTest {

  private MarbleSolitaireControllerUtils utils;
  private MarbleSolitaireModel model;
  private MarbleSolitaireView view;
  private StringBuilder output;

  /**
   * Sets up the test environment before each test method. Initializes the utility class, model,
   * view, and output objects.
   */
  @BeforeEach
  void setUp() {
    utils = new MarbleSolitaireControllerUtils();
    model = new EnglishSolitaireModel();
    output = new StringBuilder();
    view = new MarbleSolitaireTextView(model, output);
  }

  /**
   * Tests the checkQuit method when 'Q' is entered. Verifies that the game quits and displays the
   * correct quit message and score.
   */
  @Test
  void testCheckQuit() {

    utils.checkQuit(view, model, "Q");
    assertTrue(output.toString().contains("Game quit!"));

  }

  /**
   * Tests the checkQuit method when 'Q' is not entered. Verifies that the game continues and no
   * output is produced.
   */
  @Test
  void testCheckQuitNotEntered() {
    utils.checkQuit(view, model, "2");
    assertEquals("", output.toString());
  }

  /**
   * Tests the checkBadInput method with invalid input. Verifies that an appropriate error message
   * is displayed.
   */
  @Test
  void testCheckBadInputInvalid() {
    utils.checkBadInput(view, "i");
    assertEquals("Invalid input. Please enter a valid input.\n", output.toString());
  }

  /**
   * Tests the checkBadInput method with valid input. Verifies that no output is produced for valid
   * input.
   */
  @Test
  void testCheckBadInputValid() {
    utils.checkBadInput(view, "2");
    assertEquals("", output.toString());
  }

  /**
   * Tests the checkMove method with a valid move. Verifies that the move is executed and the score
   * is updated correctly.
   */
  @Test
  void testCheckMoveValid() {
    utils.checkMove(view, model, "3", "1", "3", "3");
    assertEquals("", output.toString());
    assertEquals(31, model.getScore());
  }

  /**
   * Tests the checkMove method with an invalid move. Verifies that an appropriate error message is
   * displayed.
   */
  @Test
  void testCheckMoveInvalid() {
    utils.checkMove(view, model, "2", "2", "4", "4");
    assertEquals(
        "Invalid move. Play again. Marbles should jump over another marble to a valid empty space.\n",
        output.toString());
  }

  /**
   * Tests the checkMove method with out-of-bounds values. Verifies that appropriate error messages
   * are displayed for each invalid input.
   */
  @Test
  void testInvalidValueOutOfBounds() {
    utils.checkMove(view, model, "10", "10", "12", "12");
    assertEquals("Invalid move. Play again. Select a cell inside the board\n"
        + "Invalid move. Play again. Select a cell inside the board\n"
        + "Invalid move. Play again. Select a cell inside the board\n"
        + "Invalid move. Play again. Select a cell inside the board\n"
        + "Invalid move. Play again. Marbles should jump over another marble to a valid empty space.\n",
        output.toString());
  }
}
