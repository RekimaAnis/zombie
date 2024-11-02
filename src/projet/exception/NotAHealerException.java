package projet.exception;

/**
 * NotAHealerException class
 */
public class NotAHealerException extends Exception{
	public NotAHealerException() {
		super("The player is not a healer");
	}
}