package projet.item.weapon;

public class Rifle extends Weapon{

	private int noise;
	
	/**
	 * constructor of the weapon Rifle
	 */
	public Rifle (){
		super();
		this.treshold=4;
		this.damage=1;
		this.range=3; /*1 à 3*/
		this.dice=2;
		this.id = "Rifle" + generateId();
		this.noise = 2;
	}

	public int getNoise(){
		return this.noise;
	}
}