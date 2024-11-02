package projet.item;

import java.util.Random;

import projet.player.Player;

public abstract class Item {
	public String id;
	protected static Random random = new Random( );
	/**
	 * constructor of the class Item
	 */
	public Item () {
		this.id=generateId();
	}
	/**
	 * get the id of the item
	 * @return id, the name of the player
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * put to string
	 */
	public String toString(){
		return "I";
	}
	/**
	 * generate an id 
	 * @return an id
	 */
	public String generateId() {
		return Item.random.nextInt(1000) + "";
	}
	
	/**
	 * get the minimum dice roll point to us the item
	 */
	public abstract int getTreshold();
	/**
	 * get the damage of this item
	 */
	public abstract int getDamage();
	/**
	 * get the range of the item
	 */
	public abstract int getRange();
	/**
	 * get the dice roll
	 */
	public abstract int getDice();
	/**
	 * set the damage of this item
	 */
	public abstract void setDamage(int x); 
	/**
	 * set the range of this item
	 */
	public abstract void setRange(int x);
	/**
	 * set the dice roll
	 */
	public abstract void setDice(int x);
	//get the noise of item
	public abstract int getNoise();
}

