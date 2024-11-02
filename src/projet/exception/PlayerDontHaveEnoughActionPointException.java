package projet.exception;

/**
 * PlayerDontHaveEnoughActionPointException class
 */
public class PlayerDontHaveEnoughActionPointException extends Exception{
	public PlayerDontHaveEnoughActionPointException() {
		super("Player don't have enough action point");
	}
}