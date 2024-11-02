package projet.zombie;

public class Balaise extends Zombie{
	
	public Balaise() {
		this.actionpoint=1;
		this.life=4;
		this.damage=2;
	}
	/**
	 * return the name of the zombie
	 */
	public String toString() {
		return "Balaise";
	}
}