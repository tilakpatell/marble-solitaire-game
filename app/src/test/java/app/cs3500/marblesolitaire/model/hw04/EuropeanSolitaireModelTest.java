package app.cs3500.marblesolitaire.model.hw04;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelState.SlotState;
import cs3500.marblesolitaire.model.hw04.EuropeanSolitaireModel;

/**
 * Tests the EuropeanSolitaireModel class.
 */
public class EuropeanSolitaireModelTest {

  private EuropeanSolitaireModel defaultModel;
  private EuropeanSolitaireModel customEmptyModel;
  private EuropeanSolitaireModel largeModel;
  private EuropeanSolitaireModel customSizeAndEmptyModel;

  /** 
   * Set up the test environment.
   */
  @BeforeEach
  void setUp() {
    defaultModel = new EuropeanSolitaireModel();
    customEmptyModel = new EuropeanSolitaireModel(3, 3);
    largeModel = new EuropeanSolitaireModel(5);
    customSizeAndEmptyModel = new EuropeanSolitaireModel(5, 5, 5);
  }

  /** 
   * Test the constructors.
   */
  @Test
  void testConstructors() {
    assertEquals(7, defaultModel.getBoardSize());
    assertEquals(7, customEmptyModel.getBoardSize());
    assertEquals(13, largeModel.getBoardSize());
    assertEquals(13, customSizeAndEmptyModel.getBoardSize());

    assertEquals(SlotState.Empty, defaultModel.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, customEmptyModel.getSlotAt(3, 3));
    assertEquals(SlotState.Empty, largeModel.getSlotAt(6, 6));
    assertEquals(SlotState.Empty, customSizeAndEmptyModel.getSlotAt(5, 5));
  }

  /** 
   * Test invalid constructors.
   */
  @Test
  void testInvalidConstructors() {
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(-2));
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(-3, 0, 0));
    assertThrows(IllegalArgumentException.class, () -> new EuropeanSolitaireModel(-5, 0, 0));
  }

  /** 
   * Test the getBoard method.
   */
  @Test
  void testGetBoardSize() {
    assertEquals(7, defaultModel.getBoardSize());
    assertEquals(13, largeModel.getBoardSize());
  }

  /** 
   * Test the getSlotAt method.
   */
  @Test
  void testGetSlotAt() {
    assertEquals(SlotState.Marble, defaultModel.getSlotAt(2, 2));
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(3, 3));
    assertEquals(SlotState.Invalid, defaultModel.getSlotAt(0, 0));

    assertThrows(IllegalArgumentException.class, () -> defaultModel.getSlotAt(-1, 3));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.getSlotAt(7, 3));
  }

  /** 
   * Test the getScore method.
   */
  @Test
  void testGetScore() {
    assertEquals(32, defaultModel.getScore());
    assertEquals(104, largeModel.getScore());
  }

  /** 
   * Test the move method.
   */
  @Test
  void testMove() {
    defaultModel.move(3, 1, 3, 3);
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(3, 1));
    assertEquals(SlotState.Empty, defaultModel.getSlotAt(3, 2));
    assertEquals(SlotState.Marble, defaultModel.getSlotAt(3, 3));
    assertEquals(31, defaultModel.getScore());

    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(0, 0, 0, 2));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(3, 3, 3, 5));
    assertThrows(IllegalArgumentException.class, () -> defaultModel.move(2, 2, 2, 4));
  }

}
