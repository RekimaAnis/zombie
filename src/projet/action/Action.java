package projet.action;

public interface Action {
	
	/**
	 * make the entity move
	 */
	public void move();
	
	/**
	 * make the entity attack
	 */
	public void attack();
	
	/**
	 * make the entity look around
	 */
	public void lookAroundYourself();
	
	/**
	 * take an item from the player invetory to his hand
	 */
	public void takeInHand();
	
	/**
	 * use the item in the player name
	 */
	public void useAItem();
	
	/**
	 * make the entity open a door
	 */
	public void openADoor();
	
	/**
	 * make a noise that lure the zombie in
	 */
	public void makeNoise();
	
	/**
	 * make the player search item in a cell his on 
	 */
	public void searchInRoom();
}