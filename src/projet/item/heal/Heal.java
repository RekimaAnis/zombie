package projet.item.heal;
import projet.item.*;

public abstract class Heal extends Item{
	protected int heal;
	
	/**
	 * constructor of the class Heal
	 */
	public Heal() {
		super();
	}
	
	/**
	 * return the value of heal
	 */
	public int getHeal() {
		return this.heal;
	}

	// get the noise of item
	public int getNoise(){
		return 0;
	}
}