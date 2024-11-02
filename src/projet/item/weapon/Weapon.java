package projet.item.weapon;
import projet.item.*;

public abstract class Weapon extends Item{
	
	protected int treshold;
	protected int damage;
	protected int range;
	protected int dice; /*throw of dice*/
	
	/**
	 * constructor of the Weapon
	 */
	public Weapon() {
		super();
	}
	
	/**
	 * get the dice roll point minimum to use the weapon
	 */
	public int getTreshold() {
		return this.treshold;
	}
	/**
	 * get the damage of the weapon
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * get the range of the weapon
	 */
	public int getRange() {
		return this.range;
	}
	/**
	 * get the throw of dice
	 */
	public int getDice() {
		return this.dice;
	}
	/**
	 * set the minimum dice roll point to use the weapon
	 * @param x, the minimum dice roll point
	 */
	public void setTreshold(int x) {
		this.treshold=x;
	}
	/**
	 * set the damage of the weapon
	 */
	public void setDamage(int x) {
		this.damage=x;
	}
	/**
	 * set the range of the weapon
	 */
	public void setRange(int x) {
		this.range=x;
	}
	/**
	 * set the throw of dice
	 */
	public void setDice(int x) {
		this.dice=x;
	}
}