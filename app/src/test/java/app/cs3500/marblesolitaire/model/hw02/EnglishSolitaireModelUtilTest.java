package app.cs3500.marblesolitaire.model.hw02;

import cs3500.marblesolitaire.model.hw02.MarbleSolitaireModelUtil;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cs3500.marblesolitaire.model.hw02.EnglishSolitaireModel;

/**
 * This class contains tests for the EnglishSolitaireModelUtil class.
 */
public class EnglishSolitaireModelUtilTest {

  private MarbleSolitaireModelUtil util;
  private EnglishSolitaireModel model;

  /**
   * Sets up the test environment before each test. Initializes the utility class
   * and the model.
   */
  @BeforeEach
  public void setUp() {
    util = new MarbleSolitaireModelUtil();
    model = new EnglishSolitaireModel();
  }

 

  /**
   * Tests the checkInBoard method to ensure it does not throw an exception for
   * valid board
   * positions and throws an IllegalArgumentException for invalid board positions.
   */
  @Test
  public void testCheckInBoard() {
    // Valid positions
    assertDoesNotThrow(() -> util.checkInBoard(model, 0, 3, 6));

    // Invalid positions
    assertThrows(IllegalArgumentException.class, () -> util.checkInBoard(model, -1));
    assertThrows(IllegalArgumentException.class, () -> util.checkInBoard(model, 7));
    assertThrows(IllegalArgumentException.class, () -> util.checkInBoard(model, 0, 3, 7));
  }
}
