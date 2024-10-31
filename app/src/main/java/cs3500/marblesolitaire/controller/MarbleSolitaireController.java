package cs3500.marblesolitaire.controller;

/**
 * This interface represents the controller for a Marble Solitaire game.
 * It defines the contract for playing the game.
 */
public interface MarbleSolitaireController {

  /**
   * Plays a game of Marble Solitaire.
   * <p>
   * This method should handle the game loop, including:
   * - Rendering the game board
   * - Accepting user input for moves
   * - Updating the game state based on valid moves
   * - Handling invalid moves or inputs
   * - Determining when the game is over
   *
   * @throws IllegalStateException if the game cannot be started or
   * if there's an error during gameplay that prevents the game
   * from continuing or if the game quits
   */
  void playGame() throws IllegalStateException;

}
