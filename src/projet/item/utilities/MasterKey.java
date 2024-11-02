package projet.item.utilities;

public class MasterKey extends Utilities{
	
	public MasterKey() {
		super();
		this.id = "MasterKey" + generateId();
	}
	/**
	 * set the dice roll
	 */
	public void setDice(int dice) {
 
    }
	/**
	 * get the minimum dice roll point to us the item
	 */
	public int getTreshold(){
		return 0;
	}
	/**
	 * get the damage of this item
	 */
	public int getDamage(){
		return 0;
	}
	/**
	 * get the range of the item
	 */
	public int getRange(){
		return 0;
	}
	/**
	 * get the dice roll
	 */
	public int getDice(){
		return 0;
	}
	/**
	 * set the damage of this item
	 */
	public void setDamage(int x){

	}
	/**
	 * set the range of this item
	 */
	public void setRange(int x){

	}

	// get the noise of item
	public int getNoise(){
		return 0;
	}
}
