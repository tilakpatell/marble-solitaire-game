package app.cs3500.marblesolitaire.view;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;

/**
 * This class contains tests for the MarbleSolitaireTextView class.
 */
public class MarbleSolitaireTextViewTest {
  private EnglishSolitaireModel model;
  private MarbleSolitaireTextView view;

  /**
   * Sets up the test environment before each test. Initializes the model and view.
   */
  @BeforeEach
  public void setUp() {
    model = new EnglishSolitaireModel(); // Assumes a default constructor setup
    view = new MarbleSolitaireTextView(model);
  }

  /**
   * Tests that the constructor initializes the view with a valid model. Asserts that the view is
   * not null.
   */
  @Test
  public void testConstructorWithValidModel() {
    assertNotNull(view, "View should not be null when model is valid");
  }

  /**
   * Tests the toString method for the initial board state. Asserts that the initial board matches
   * the expected layout.
   */
  @Test
  public void testToStringInitialBoard() {
    String expected = """
    O O O\s\s\s\s
    O O O\s\s\s\s
O O O O O O O
O O O _ O O O
O O O O O O O
    O O O\s\s\s\s
    O O O\s\s\s\s""";
    assertEquals(expected, view.toString(), "Initial board should match expected layout");
  }

  /**
   * Tests the toString method after making a move. Asserts that the board state after a move
   * matches the expected layout.
   */
  @Test
  public void testToStringAfterMove() {
    model.move(3, 1, 3, 3);
    String expected = """
                          O O O\s\s\s\s
                          O O O\s\s\s\s
                      O O O O O O O
                      O _ _ O O O O
                      O O O O O O O
                          O O O\s\s\s\s
                          O O O\s\s\s\s""";
    assertEquals(expected, view.toString(), "Board should match expected layout after a move");
  }

  /**
   * Tests the renderBoard method. Asserts that the rendered board matches the expected layout.
   */
  @Test
  public void testRenderBoard() throws IOException {
    StringWriter appendable = new StringWriter();
    MarbleSolitaireTextView testView = new MarbleSolitaireTextView(model, appendable);
    testView.renderBoard();
    String expected = """
                          O O O\s\s\s\s
                          O O O\s\s\s\s
                      O O O O O O O
                      O O O _ O O O
                      O O O O O O O
                          O O O\s\s\s\s
                          O O O\s\s\s\s
                      """;
    assertEquals(expected, appendable.toString(), "Rendered board should match expected layout");
  }

  /**
   * Tests the renderMessage method. Asserts that the rendered message matches the input message.
   */
  @Test
  public void testRenderMessage() throws IOException {
    StringWriter appendable = new StringWriter();
    MarbleSolitaireTextView testView = new MarbleSolitaireTextView(model, appendable);
    String message = "Test message";
    testView.renderMessage(message);
    assertEquals(message + "\n", appendable.toString(),
        "Rendered message should match input message");
  }

  /**
   * Tests rendering multiple messages. Asserts that multiple rendered messages are correctly
   * appended.
   */
  @Test
  public void testRenderMultipleMessages() throws IOException {
    StringWriter appendable = new StringWriter();
    MarbleSolitaireTextView testView = new MarbleSolitaireTextView(model, appendable);
    testView.renderMessage("First message");
    testView.renderMessage("Second message");
    String expected = "First message\nSecond message\n";
    assertEquals(expected, appendable.toString(),
        "Multiple rendered messages should be correctly appended");
  }

  /**
   * Tests the constructor with null model. Asserts that it throws an IllegalArgumentException.
   */
  @Test
  public void testConstructorWithNullModel() {
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(null),
        "Constructor should throw IllegalArgumentException when model is null");
  }

  /**
   * Tests the constructor with null appendable. Asserts that it throws an IllegalArgumentException.
   */
  @Test
  public void testConstructorWithNullAppendable() {
    assertThrows(IllegalArgumentException.class, () -> new MarbleSolitaireTextView(model, null),
        "Constructor should throw IllegalArgumentException when appendable is null");
  }
}
