package projet.exception;

/**
 * PlayerAlreadyDeadException class
 */
public class PlayerAlreadyDeadException extends Exception{
	public PlayerAlreadyDeadException() {
		super("The player is already dead");
	}
}