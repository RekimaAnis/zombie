package projet.zombie;

public class Walkers extends Zombie{
	
	public Walkers() {
		this.actionpoint=1;
		this.life=1;
		this.damage=1;
	}
	
	/**
	 * return the name of the zombie
	 */
	public String toString() {
		return "Walker";
	}
}