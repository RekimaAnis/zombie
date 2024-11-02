package projet.exception;

/**
 * ZombieDontHaveEnoughActionPointException class
 */
public class ZombieDontHaveEnoughActionPointException extends Exception{
	public ZombieDontHaveEnoughActionPointException() {
		super("Zombie don't have enough action point");
	}
}