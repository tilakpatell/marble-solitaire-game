package app.cs3500.marblesolitaire.model.hw02;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;
import cs3500.marblesolitaire.model.hw02.EnglishSolitaireValidMoveStrategy;

/**
 * Tests the EnglishSolitaireValidMoveStrategy class.
 */
public class EnglishSolitaireValidMoveStrategyTest {

  private EnglishSolitaireValidMoveStrategy strategy;
  private EnglishSolitaireModel model;

  /**
   * Sets up the test environment before each test. Initializes the utility class
   * and the model.
   */
  @BeforeEach
  public void setUp() {
    strategy = new EnglishSolitaireValidMoveStrategy();
    model = new EnglishSolitaireModel();
  }

  /**
   * Tests the validMove method of the EnglishSolitaireValidMoveStrategy class.
   */
  @Test
  public void testValidMove() {
    // Valid moves
    assertTrue(strategy.validMove(model, 3, 1, 3, 3));
    assertTrue(strategy.validMove(model, 5, 3, 3, 3));

    // Invalid moves
    assertFalse(strategy.validMove(model, 0, 0, 0, 2)); // Out of bounds
    assertFalse(strategy.validMove(model, 3, 3, 3, 5)); // No marble to jump over
    assertFalse(strategy.validMove(model, 2, 3, 4, 3)); // Diagonal move
    assertFalse(strategy.validMove(model, 3, 3, 3, 4)); // Move to adjacent cell
  }
}
