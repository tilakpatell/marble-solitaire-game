package app.cs3500.marblesolitaire.model.hw02;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.view.MarbleSolitaireTextView;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * Test class for EnglishSolitaireModel. This class contains unit tests for various constructors and
 * methods of EnglishSolitaireModel.
 */
public class EnglishSolitaireModelTest {

  EnglishSolitaireModel EM1;
  EnglishSolitaireModel EM2;
  EnglishSolitaireModel EM3;
  EnglishSolitaireModel EM4;
  MarbleSolitaireTextView test;

  /**
   * Sets up the test environment before each test method is run. Initializes four different
   * EnglishSolitaireModel objects with various parameters.
   */
  @BeforeEach
  void setUpTest() {
    EM1 = new EnglishSolitaireModel();
    EM2 = new EnglishSolitaireModel(3, 3);
    EM3 = new EnglishSolitaireModel(5);
    EM4 = new EnglishSolitaireModel(5, 5, 5);
  }

  /**
   * Tests the default constructor of EnglishSolitaireModel. Verifies the board size, empty slot
   * position, and states of various slots.
   */
  @Test
  void testDefaultConstructor() {
    assertEquals(7, EM1.getBoardSize());
    assertEquals(SlotState.Empty, EM1.getSlotAt(3, 3));
    assertEquals(SlotState.Marble, EM1.getSlotAt(0, 3));
    assertEquals(SlotState.Invalid, EM1.getSlotAt(0, 0));
  }

  /**
   * Tests the second constructor of EnglishSolitaireModel that takes row and column parameters.
   * Verifies the board size and empty slot position.
   */
  @Test
  void testSecondConstructor() {
    assertEquals(7, EM2.getBoardSize());
    assertEquals(SlotState.Empty, EM2.getSlotAt(3, 3));
  }

  /**
   * Tests the third constructor of EnglishSolitaireModel that takes an arm thickness parameter.
   * Verifies the board size, empty slot position, and states of various slots.
   */
  @Test
  void testThirdConstructor() {
    assertEquals(13, EM3.getBoardSize());
    assertEquals(SlotState.Marble, EM3.getSlotAt(6, 6));
    assertEquals(SlotState.Marble, EM3.getSlotAt(6, 6));
    assertEquals(SlotState.Invalid, EM3.getSlotAt(0, 0));
  }

  /**
   * Tests the fourth constructor of EnglishSolitaireModel that takes arm thickness and position
   * parameters. Verifies the board size and empty slot position.
   */
  @Test
  void testFourthConstructor() {
    assertEquals(13, EM4.getBoardSize());
    assertEquals(SlotState.Empty, EM4.getSlotAt(5, 5));
  }

  /**
   * Tests the constructors with invalid parameters. Verifies that appropriate exceptions are thrown
   * for invalid inputs.
   */
  @Test
  void testInvalidConstructors() {
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(4));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(3, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(5, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(-2, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new EnglishSolitaireModel(-10, 2, 0));

  }

  /**
   * Tests the getBoardSize method. Verifies that the correct board size is returned for different
   * models.
   */
  @Test
  void testGetBoardSize() {
    assertEquals(7, EM1.getBoardSize());
    assertEquals(13, EM3.getBoardSize());
  }

  /**
   * Tests the getScore method. Verifies the initial scores and the score after making moves.
   */
  @Test
  void testGetScore() {
    assertEquals(32, EM1.getScore());
    assertEquals(104, EM3.getScore());

    EM1.move(3, 1, 3, 3);
    assertEquals(31, EM1.getScore());

    EM1.move(3, 4, 3, 2);
    assertEquals(30, EM1.getScore());

    EM2.move(3, 1, 3, 3);
    assertEquals(31, EM2.getScore());
  }

  /**
   * Tests the getSlotAt method. Verifies the state of various slots and checks for exceptions with
   * invalid coordinates.
   */
  @Test
  void testGetSlotAt() {
    assertEquals(SlotState.Marble, EM1.getSlotAt(2, 3));
    assertEquals(SlotState.Empty, EM1.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, EM1.getSlotAt(0, 0));

    assertThrows(IllegalArgumentException.class, () -> EM1.getSlotAt(-1, 3));
    assertThrows(IllegalArgumentException.class, () -> EM1.getSlotAt(7, 3));
  }

  /**
   * Tests the move method. Verifies that valid moves are executed correctly and invalid moves throw
   * exceptions.
   */
  @Test
  void testMove() {
    // Test a valid move
    EM1.move(3, 1, 3, 3);
    assertEquals(SlotState.Empty, EM1.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, EM1.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, EM1.getSlotAt(3, 3));

    // Test another valid move
    EM1.move(5, 2, 3, 2);
    assertEquals(SlotState.Empty, EM1.getSlotAt(5, 2));
    assertEquals(SlotState.Empty, EM1.getSlotAt(4, 2));
    assertEquals(SlotState.Marble, EM1.getSlotAt(3, 2));

    // Test invalid moves
    assertThrows(IllegalArgumentException.class, () -> EM1.move(0, 0, 0, 2));
    assertThrows(IllegalArgumentException.class, () -> EM1.move(3, 3, 3, 5));
    assertThrows(IllegalArgumentException.class, () -> EM1.move(2, 2, 2, 4));
  }

  /**
   * Tests the isGameOver method. Verifies that the game is not over at the start, after some moves,
   * and is over when appropriate.
   */
  @Test
  void testIsGameOver() {
    assertFalse(EM1.isGameOver());

    EM1.move(3, 1, 3, 3);
    EM1.move(3, 4, 3, 2);
    EM1.move(1, 3, 3, 3);

    assertFalse(EM1.isGameOver());

    EnglishSolitaireModel almostFinished = new EnglishSolitaireModel();
    for (int i = 0; i < 7; i++) {
      for (int j = 0; j < 7; j++) {
        if (almostFinished.getSlotAt(i, j) == SlotState.Marble) {
          almostFinished.getBoardState()[i][j] = SlotState.Empty;
        }
      }
    }
    almostFinished.getBoardState()[3][3] = SlotState.Marble;
    almostFinished.getBoardState()[3][4] = SlotState.Marble;

    assertFalse(almostFinished.isGameOver());

    almostFinished.move(3, 4, 3, 2);

    assertTrue(almostFinished.isGameOver());
  }

}
