package projet.exception;

/**
 * InventoryFullException class
 */
public class InventoryFullException extends Exception{
	public InventoryFullException() {
		super("The inventory is full");
	}
}