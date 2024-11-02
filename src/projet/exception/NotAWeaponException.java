package projet.exception;

/**
 * NotAWeaponException class
 */
public class NotAWeaponException extends Exception{
	public NotAWeaponException() {
		super("this is not a weapon");
	}
}