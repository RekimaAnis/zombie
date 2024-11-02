package projet.item.weapon;

public class Chainsaw extends Weapon{
	

	private int noise;
	/**
	 * Constructor of the weapon Chainsaw
	 */
	public Chainsaw (){
		super();
		this.treshold=5;
		this.damage=3;
		this.range=0;
		this.dice=2;
		this.id = "Chainsaw" + generateId();
		this.noise = 3;
	}

	public int getNoise(){
		return this.noise;
	}
}