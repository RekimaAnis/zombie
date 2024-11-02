package projet.item.weapon;

public class Axe extends Weapon{
	
	private int noise;
	/**
	 * constructor of the weapon Axe
	 */
	public Axe (){
		super();
		this.treshold=4;
		this.damage=2;
		this.range=0;
		this.dice=1;
		this.id = "Axe" + generateId();
		this.noise = 0;
	}

	public int getNoise(){
		return this.noise;
	}
}