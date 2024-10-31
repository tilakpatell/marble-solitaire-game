package app.cs3500.marblesolitaire.view;

import java.io.IOException;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;
import cs3500.marblesolitaire.view.TriangleSolitaireTextView;

/**
 * Tests the TriangleSolitaireTextView class.
 */
public class TriangleSolitaireTextViewTest {
  private TriangleSolitaireModel model;
  private TriangleSolitaireTextView view;

  /**
   * Sets up the test environment before each test. Initializes the model and view.
   */
  @BeforeEach
  public void setUp() {
    model = new TriangleSolitaireModel(); 
    view = new TriangleSolitaireTextView(model);
  }

  /* 
   * Tests the constructor of the TriangleSolitaireTextView class with a valid model.
   */
  @Test
  public void testConstructorWithValidModel() {
    assertNotNull(view, "View should not be null when model is valid");
  }

  /* 
   * Tests the constructor of the TriangleSolitaireTextView class with a null model.
   */
  @Test
  public void testConstructorWithNullModel() {
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(null),
        "Constructor should throw IllegalArgumentException when model is null");
  }

  /* 
   * Tests the constructor of the TriangleSolitaireTextView class with a valid model and appendable.
   */
  @Test
  public void testConstructorWithNullAppendable() {
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireTextView(model, null),
        "Constructor should throw IllegalArgumentException when appendable is null");
  }

  /* 
   * Tests the toString method of the TriangleSolitaireTextView class.
   */
  @Test
  public void testToString() {
    String expected;
      expected = """
                     _
                    O O
                   O O O
                  O O O O
                 O O O O O""";
    assertEquals(expected, view.toString(), "Initial board should match expected layout");
  }

  /* 
   * Tests rendering the board. Asserts that the rendered board matches the expected layout.
   */
  @Test
  public void testRenderBoard() throws IOException {
    StringWriter appendable = new StringWriter();
    TriangleSolitaireTextView testView = new TriangleSolitaireTextView(model, appendable);
    testView.renderBoard();
    String expected = """
                          _
                         O O
                        O O O
                       O O O O
                      O O O O O
                      """;
    assertEquals(expected, appendable.toString(), "Rendered board should match expected layout");
  }

  /* 
   * Tests rendering a message. Asserts that the rendered message matches the input message.
   */
  @Test
  public void testRenderMessage() throws IOException {
    StringWriter appendable = new StringWriter();
    TriangleSolitaireTextView testView = new TriangleSolitaireTextView(model, appendable);
    String message = "Test message";
    testView.renderMessage(message);
    assertEquals(message + "\n", appendable.toString(),
        "Rendered message should match input message");
  }

  /* 
   * Tests rendering multiple messages. Asserts that multiple rendered messages are correctly appended.
   */
  @Test
  public void testRenderMultipleMessages() throws IOException {
    StringWriter appendable = new StringWriter();
    TriangleSolitaireTextView testView = new TriangleSolitaireTextView(model, appendable);
    testView.renderMessage("First message");
    testView.renderMessage("Second message");
    String expected = "First message\nSecond message\n";
    assertEquals(expected, appendable.toString(),
        "Multiple rendered messages should be correctly appended");
  }

  /* 
   * Tests the toString method of the TriangleSolitaireTextView class after a move.
   */
  @Test
  public void testToStringAfterMove() {
    model.move(2, 0, 0, 0);
    String expected = """
                          O
                         _ O
                        _ O O
                       O O O O
                      O O O O O""" ;
    assertEquals(expected, view.toString(), "Board should match expected layout after a move");
  }

  /* 
   * Tests the toString method of the TriangleSolitaireTextView class with a larger board.
   */
  @Test
  public void testToStringWithLargerBoard() {
    TriangleSolitaireModel largeModel = new TriangleSolitaireModel(6);
    TriangleSolitaireTextView largeView = new TriangleSolitaireTextView(largeModel);
    String expected = """
                           _
                          O O
                         O O O
                        O O O O
                       O O O O O
                      O O O O O O""";
    assertEquals(expected, largeView.toString(), "Larger board should match expected layout");
  }
}
