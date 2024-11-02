package projet.celulle;
import projet.item.*;
import projet.zombie.Zombie;
import projet.*;
import java.util.*;
import projet.player.*;

public class Celulle {
	protected int height;
	protected int width;
	protected List<Player> player;
	protected List<Item> item;
	protected List<Zombie> zombie;
	public int noise;

	/**
	 * constructor of the cell class, initialize and sets up the configuration
	 * 
	 * @param width the location of the cell based on width of the board
	 * @param height the location of the location based on height of the board
	 */
	public Celulle(int width, int height) {
		this.height=height;
		this.width=width;
		this.item = new ArrayList<Item>();
		this.player = new ArrayList<Player>();
		this.zombie = new ArrayList<Zombie>();
		this.noise = 0;
	}

	/**
	 * get the player information
	 * 
	 * @return the player
	 */
	public List<Player> getPlayer(){
		return this.player;
	}

	/**
	 * add the player on the cell
	 * 
	 * @param player the player configuration
	 */
	public void setPlayer(Player player){
		this.player.add(player);
	}
	
	public void setZombieList(List<Zombie> zombielist) {
		this.zombie = zombielist;
	}
	
	public void setPlayerList(List<Player> playerlist) {
		this.player = playerlist;
	}
	/**
	 * get the item on the cell
	 * 
	 * @return the item in the cell
	 */
	public List<Item> getItem(){
		return this.item;
	}
	
	/**
	 * add the zombie on the cell
	 * 
	 * @param zombie the zombie configuration
	 */
	public void SetZombie(Zombie zombie){
		this.zombie.add(zombie);
	}
	
	/**
	 * get the zombie on the cell
	 * 
	 * @return the zombie in the cell
	 */
	public List<Zombie> getZombie(){
		return this.zombie;
	}
	
	/**
	 * add the item on the cell
	 * 
	 * @param item the item configuration
	 */
	public void SetItem(Item item){
		this.item.add(item);
	}
	
	
	/**
	 * destroy the player from the cell
	 * 
	 * @param player the player configuration
	 */
	public void DestroyPlayer(Player player){
		this.player.remove(player);
	}
	/**
	 * destroy the item in the cell
	 */
	public void DestroyItem() {
		int cpm = this.item.size();
		while(!this.item.isEmpty()) {
			this.item.remove(cpm);
			cpm--;
		}
	}
	/**
	 * pass on the cell with open door
	 * @param porte , door of the cell
	 * @return true
	 */
	public boolean PassageDePorte(Door porte){
		return true;
	}
	
	/**
	 * get the width position of the  cell
	 * 
	 * @return the width position of the cell
	 */
	public int getWidth(){
		return this.width;
	}
	/**
	 * get the height position of the cell
	 *
	 * @return the height position of the cell
	 */
	public int getHeight(){
		return this.height;
	}
	/**
	 * @return string
	 */
	public String getRepresentation() {
		return "";
	}

	/**
	 * get the noise of this cell
	 * @return
	 */
	public int getNoise() {
		return this.noise;
	}
}
