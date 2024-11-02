package projet.item.weapon;

public class Crowbar extends Weapon{
	
	private int noise;
	/**
	 * constructor of the weapon Crowbar
	 */
	public Crowbar (){
		super();
		this.treshold=4;
		this.damage=1;
		this.range=0; 
		this.dice=1;
		this.id = "Crowbar" + generateId();
		this.noise = 1;
	}

	public int getNoise(){
		return this.noise;
	}
}