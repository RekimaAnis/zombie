package projet.zombie;
import java.util.Random;

import projet.item.Item;

public class Zombie {
	protected boolean inlife;
	protected int life;
	protected int damage;
	protected int x;
	protected int y;
	protected int actionpoint; /*point d'action*/
	protected String id;
	protected static Random random = new Random( );
	
	/**
	 * constructor of the zombie
	 */
	public Zombie() {
		this.inlife = true;
		this.life = 3;
		this.damage = 1;
		this.actionpoint = 3;
		this.x=0;
		this.y=0;
		this.id = generateId();
	}
	/**
	 * generate the id of the zombie
	 * @return the id of the zombie
	 */
	public String generateId() {
		return "zombie" + Zombie.random.nextInt(1000) ;
	}
	/**
	 * get the id of the zombie
	 * @return the id of the zombie
	 */
	public String getId() {
		return this.id;
	}
	/**
	 * get the number of the axe x of the zombie
	 * @return x
	 */
	public int getx() {
		return this.x;
	}
	/**
	 * get the number of the axe y of the zombie
	 * @return y
	 */
	public int gety() {
		return this.y;
	}
	/**
	 * set the number of the axe x of the zombie
	 * @return x
	 */
	public void setx(int x) {
		this.x=x;
	}
	/**
	 * set the number of the axe y of the zombie
	 * @return y
	 */
	public void sety(int y) {
		this.y = y;
	}
	/**
	 * set the healt point of the zombie to l
	 * @param l, the life point of the zombie
	 */
	public void setLife(int l) {
		this.life=l;
	}
	/**
	 * set actionpoint
	 * @param the new actionpoint
	 */
	public void setActionpoint(int a){
		this.actionpoint = a;
	}
	/**
	 * get the health point of the zombie
	 * @return the life of the zombie
	 */
	public int getLife() {
		return this.life;
	}
	/**
	 * get the damage of the zombie
	 * @return
	 */
	public int getDamage() {
		return this.damage;
	}
	
	/**
	 * get the action point of the zombie
	 * @return the actionpoint of the zombie
	 */
	public int getActionpoint() {
		return this.actionpoint;
	}
	
	/**
	 * @return the status of the zombie life (dead or not)
	 */
	public boolean getInLife() {
		return this.inlife;
	}
	/**
	 * kill the zombie
	 */
	public void zombieDie() {
		if (this.life<=0) {
			this.inlife=false;
		}
	}
	/**
	 * return the name of the zombie
	 */
	public String toString() {
		return "Zombie";
	}
}