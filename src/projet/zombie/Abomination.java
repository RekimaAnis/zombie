package projet.zombie;

public class Abomination extends Zombie{
	
	public Abomination() {
		this.actionpoint=1;
		this.life=6;
		this.damage=3;
	}
	/**
	 * return the name of the zombie
	 */
	public String toString() {
		return "Abomination";
	}
}