package app.cs3500.marblesolitaire.model.hw04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.TriangleSolitaireModel;

/**
 * Test class for the TriangleSolitaireModel.
 * Contains unit tests to verify the functionality of the TriangleSolitaireModel class.
 */
public class TriangleSolitaireModelTest {

  private TriangleSolitaireModel defaultModel;
  private TriangleSolitaireModel customSizeModel;
  private TriangleSolitaireModel customEmptyModel;
  private TriangleSolitaireModel customSizeAndEmptyModel;

  /**
   * Sets up the test environment before each test method is run.
   * Initializes different TriangleSolitaireModel instances for testing.
   */
  @BeforeEach
  void setUp() {
    defaultModel = new TriangleSolitaireModel();
    customSizeModel = new TriangleSolitaireModel(7);
    customEmptyModel = new TriangleSolitaireModel(2, 1);
    customSizeAndEmptyModel = new TriangleSolitaireModel(6, 2, 1);
  }

  /**
   * Tests the constructors of TriangleSolitaireModel.
   * Verifies that the board is correctly initialized for different constructor parameters.
   */
  @Test
  void testConstructors() {
    assertEquals(5, defaultModel.getBoardSize());
    assertEquals(7, customSizeModel.getBoardSize());
    assertEquals(5, customEmptyModel.getBoardSize());
    assertEquals(6, customSizeAndEmptyModel.getBoardSize());

    assertEquals(SlotState.Empty, defaultModel.getSlotAt(0, 0));
    assertEquals(SlotState.Invalid, customSizeModel.getSlotAt(1, 4));
    assertEquals(SlotState.Empty, customEmptyModel.getSlotAt(2, 1));
    assertEquals(SlotState.Marble, customSizeAndEmptyModel.getSlotAt(3, 1));
  }

  /**
   * Tests invalid constructor parameters.
   * Verifies that appropriate exceptions are thrown for invalid inputs.
   */
  @Test
  void testInvalidConstructors() {
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(0));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(5, 5, 0));
    assertThrows(IllegalArgumentException.class, () -> new TriangleSolitaireModel(5, 0, 1));
  }

  /**
   * Tests the getBoardSize method.
   * Verifies that the correct board size is returned for different model instances.
   */
  @Test
  void testGetBoardSize() {
    assertEquals(5, defaultModel.getBoardSize());
    assertEquals(7, customSizeModel.getBoardSize());
  }

  /**
   * Tests the getSlotAt method.
   * Verifies that the correct slot states are returned for different positions on the board.
   */
  @Test
  void testGetSlotAt() {
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(0, 0));
    assertEquals(SlotState.Marble, defaultModel.getSlotAt(1, 0));
    assertEquals(SlotState.Invalid, defaultModel.getSlotAt(0, 1));

    assertThrows(IllegalArgumentException.class, () -> defaultModel.getSlotAt(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.getSlotAt(5, 0));
  }

  /**
   * Tests the getScore method.
   * Verifies that the correct initial score is returned for different model instances.
   */
  @Test
  void testGetScore() {
    assertEquals(14, defaultModel.getScore());
    assertEquals(27, customSizeModel.getScore());
  }

  /**
   * Tests the move method.
   * Verifies that valid moves are executed correctly and invalid moves throw appropriate exceptions.
   */
  @Test
  void testMove() {
    defaultModel.move(2, 0, 0, 0);
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(2, 0));
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(1, 0));
    assertEquals(SlotState.Marble, defaultModel.getSlotAt(0, 0));
    assertEquals(13, defaultModel.getScore());

    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(0, 0, 2, 2));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(3, 0, 3, 2));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(4, 2, 2, 2));
  }
}