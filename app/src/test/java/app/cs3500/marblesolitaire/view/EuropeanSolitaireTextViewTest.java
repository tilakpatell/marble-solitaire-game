package app.cs3500.marblesolitaire.view;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;
import cs3500.marblesolitaire.view.EuropeanSolitaireTextView;


/**
 * Tests the EuropeanSolitaireTextView class.
 */
public class EuropeanSolitaireTextViewTest {
  private EuropeanSolitaireModel model;
  private EuropeanSolitaireTextView view;

  /**
   * Sets up the test environment before each test. Initializes the model and view.
   */
  @BeforeEach
  public void setUp() {
    model = new EuropeanSolitaireModel(); 
    view = new EuropeanSolitaireTextView(model);
  }

  /**
   * Tests the constructor of the EuropeanSolitaireTextView class with a valid model.
   */
  @Test
  public void testConstructorWithValidModel() {
    assertNotNull(view, "View should not be null when model is valid");
  }

  /**
   * Tests the constructor of the EuropeanSolitaireTextView class with a valid model and appendable.
   */
  @Test
  public void testConstructorWithNullModel() {
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireTextView(null),
        "Constructor should throw IllegalArgumentException when model is null");
  }

  /**
   * Tests the constructor of the EuropeanSolitaireTextView class with a null appendable.
   */
  @Test
  public void testConstructorWithNullAppendable() {
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireTextView(model, null),
        "Constructor should throw IllegalArgumentException when appendable is null");
  }

  /**
   * Tests the toString method of the EuropeanSolitaireTextView class.
   */
  @Test
  public void testToString() {
    String expected = "    O O O    \n" +
                      "  O O O O O  \n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "  O O O O O  \n" +
                      "    O O O    ";
    assertEquals(expected, view.toString(), "Initial board should match expected layout");
  }

  /**
   * Tests the renderBoard method of the EuropeanSolitaireTextView class.
   */
  @Test
  public void testRenderBoard() throws IOException {
    StringWriter appendable = new StringWriter();
    EuropeanSolitaireTextView testView = new EuropeanSolitaireTextView(model, appendable);
    testView.renderBoard();
    String expected = "    O O O    \n" +
                      "  O O O O O  \n" +
                      "O O O O O O O\n" +
                      "O O O _ O O O\n" +
                      "O O O O O O O\n" +
                      "  O O O O O  \n" +
                      "    O O O    \n";
    assertEquals(expected, appendable.toString(), "Rendered board should match expected layout");
  }

  /**
   * Tests the renderBoard method of the EuropeanSolitaireTextView class with a custom board.
   */
  @Test
  public void testRenderMessage() throws IOException {
    StringWriter appendable = new StringWriter();
    EuropeanSolitaireTextView testView = new EuropeanSolitaireTextView(model, appendable);
    String message = "Test message";
    testView.renderMessage(message);
    assertEquals(message + "\n", appendable.toString(),
        "Rendered message should match input message");
  }

  /**
   * Tests the renderBoard method of the EuropeanSolitaireTextView class with multiple messages.
   */
  @Test
  public void testRenderMultipleMessages() throws IOException {
    StringWriter appendable = new StringWriter();
    EuropeanSolitaireTextView testView = new EuropeanSolitaireTextView(model, appendable);
    testView.renderMessage("First message");
    testView.renderMessage("Second message");
    String expected = "First message\nSecond message\n";
    assertEquals(expected, appendable.toString(),
        "Multiple rendered messages should be correctly appended");
  }
}
