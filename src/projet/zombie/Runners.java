package projet.zombie;

public class Runners extends Zombie{
	
	public Runners() {
		this.actionpoint=2;
		this.life=1;
		this.damage=2;
	}
	/**
	 * return the name of the zombie
	 */
	public String toString() {
		return "Runners";
	}
}