package app.cs3500.marblesolitaire.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.marblesolitaire.controller.MarbleSolitaireControllerImpl;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.view.MarbleSolitaireView;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Scanner;

/**
 * This class contains unit tests for the MarbleSolitaireControllerImpl class. It tests various
 * scenarios including valid moves, invalid moves, quitting the game, and handling invalid input.
 */
public class MarbleSolitaireControllerImplTest {

  private MarbleSolitaireModel model;
  private StringWriter output;
  private MarbleSolitaireView view;
  private StringReader input;
  private Scanner scanner;
  private MarbleSolitaireControllerImpl controller;

  /**
   * Sets up the test environment before each test method is run. Initializes the model, view,
   * input, and controller objects.
   */
  @BeforeEach
  public void setUp() {
    model = new EnglishSolitaireModel();
    output = new StringWriter();
    view = new MarbleSolitaireTextView(model, output);
    input = new StringReader("");
    scanner = new Scanner(input);
    controller = new MarbleSolitaireControllerImpl(model, view, input);
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException when given a null model.
   */
  @Test
  public void testConstructorNullModel() {
    assertThrows(IllegalArgumentException.class,
        () -> new MarbleSolitaireControllerImpl(null, view, input));
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException when given a null view.
   */
  @Test
  public void testConstructorNullView() {
    assertThrows(IllegalArgumentException.class,
        () -> new MarbleSolitaireControllerImpl(model, null, input));
  }

  /**
   * Tests that the constructor throws an IllegalArgumentException when given a null Readable.
   */
  @Test
  public void testConstructorNullReadable() {
    assertThrows(IllegalArgumentException.class,
        () -> new MarbleSolitaireControllerImpl(model, view, null));
  }

  /**
   * Tests the playGame method with a valid move followed by quitting.
   */
  @Test
  public void testPlayGameValidMove() {
    input = new StringReader("2 4 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Score: 32"));
    assertFalse(outputStr.contains("Score: 31"));
    assertTrue(outputStr.contains("Game quit!"));
  }

  /**
   * Tests the playGame method with an invalid move followed by quitting.
   */
  @Test
  public void testPlayGameInvalidMove() {
    input = new StringReader("2 2 4 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Invalid move"));
    assertTrue(outputStr.contains("Game quit!"));
  }

  /**
   * Tests the playGame method when the user quits immediately.
   */
  @Test
  public void testPlayGameQuitImmediately() {
    input = new StringReader("q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Game quit!"));
    assertTrue(outputStr.contains("Score: 32"));
  }

  /**
   * Tests the playGame method with invalid input followed by quitting.
   */
  @Test
  public void testPlayGameInvalidInput() {
    input = new StringReader("invalid q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Invalid input"));
    assertTrue(outputStr.contains("Game quit!"));
  }

  /**
   * Tests the playGame method with multiple moves until the game is over.
   */
  @Test
  public void testPlayGameUntilGameOver() {
    input = new StringReader("2 4 4 4 3 2 3 4 5 3 3 3 q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertFalse(outputStr.contains("Score: 29"));
    assertTrue(outputStr.contains("Game quit!"));
  }

  /**
   * Tests the playGame method with multiple valid moves followed by quitting.
   */
  @Test
  public void testPlayGameMultipleMoves() {
    input = new StringReader("2 4 4 4 5 4 3 4 q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Score: 32"));
    assertTrue(outputStr.contains("Game quit!"));
  }

  /**
   * Tests the playGame method with an out-of-bounds move followed by quitting.
   */
  @Test
  public void testPlayGameOutOfBoundsMove() {
    input = new StringReader("0 -1 2 2 q");
    controller = new MarbleSolitaireControllerImpl(model, view, input);
    controller.playGame();

    String outputStr = output.toString();
    assertTrue(outputStr.contains("Invalid move"));
    assertTrue(outputStr.contains("Select a cell inside the board"));
    assertTrue(outputStr.contains("Game quit!"));
  }
}
