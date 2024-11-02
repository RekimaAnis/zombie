package projet.item.weapon;

public class Gun extends Weapon{
	
	private int noise;
	/**
	 * constructor of the weapon Gun
	 */
	public Gun (){
		super();
		this.treshold=4;
		this.damage=1;
		this.range=1; /*0 à 1*/
		this.dice=1;
		this.id = "Gun" + generateId();
		this.noise = 2;
	}

	public int getNoise(){
		return this.noise;
	}
}