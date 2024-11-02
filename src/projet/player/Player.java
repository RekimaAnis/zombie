package projet.player;
import projet.item.*;
import projet.*;
import projet.celulle.*;
import projet.exception.*;
import projet.action.*;
import java.util.Random;

import java.util.*;
import projet.item.heal.*;

public class Player {
	protected static Random random = new Random( );
	public String name;
	protected int actionpoint;
	protected int level;
	protected int life;
	protected Item stock[];
	protected boolean inLife;
	protected Item hand[];
	protected int x; //coordonnées x du joueur
	protected int y; //coordonnées y du joueur

	

	/**
	 * Constructor of the class player, it initializes the player and sets up the configuration
	 *
	 * @param name Name of the player
	*/
	public Player(String name) {
		this.name = name;
		this.actionpoint = 3;
		this.level = 1;
		this.life = 5;
		this.stock = new Item[4];
		this.inLife = true;
		this.hand = new Item[1];
		this.x=0;
		this.y=0;
	}

	/**
	 * get height x of the player
	 * @returnx
	 */
	public int getx() {
		return this.x;
	}
	/**
	 * get width y of the player
	 * @return y
	 */
	public int gety() {
		return this.y;
	}
	/**
	 * set x
	 * @param x
	 */
	public void setx(int x) {
		this.x=x;
	}
	/**
	 * set player hp
	 * @param l new hp
	 */
	public void setLife(int l) {
		this.life = l;
	}
	/**
	 * set y
	 * @param y
	 */
	public void sety(int y) {
		this.y=y;
	}
	/**
	 * set actionpoint
	 * @param the new actionpoint
	 */
	public void setActionpoint(int a){
		this.actionpoint = a;
	}
	
	/**
	 * Get the name of the player
	 *
	 * @return the name of the player
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * get the action point of the player
	 *
	 * @return the number of action point from the player
	 */
	public int getActionpoint() {
		return this.actionpoint;
	}
	/**
	 * get the level of the player
	 *
	 * @return the level of the player
	 */
	public int getLevel() {
		return this.level;
	}
	/**
	 * set the level of the player
	 * @param l, the level of the player
	 */
	public void setLevel(int l) {
		this.level = l;
	}
	/**
	 * get what is the hand of the player
	 * @return what the player got in his hand
	 */
	public Item[] getHand() {
		return this.hand;
	}
	/**
	 * get the life of the player
	 *
	 * @return the number of health point of the player
	 */
	public int getLife() {
		return this.life;
	}
	/**
	 * get the stock of the player
	 *
	 * @return the stock of the player
	 */
	public Item[] getStock() {
		return this.stock;
	}
	/**
	 * get the player status, in life or not
	 *
	 * @return the player status
	 */
	public boolean getInlife() {
		return this.inLife;
	}
	
	/**
	 * take an item into the player hand
	 */
/* 	public void TakeAHand(int i){
		if (this.hand.length==1){
			this.hand[0]=null;
		}
		this.hand[0]=this.stock[i];
	} */
	/**
	 * Kill the player if his life is equals to 0
	 */
	public void PlayerDie(){
		if (this.life<=0) {
			this.inLife=false;
		}
	}


	
	/**
	 * take an item on the floor, if there is no item in the hand, the item go in the hand else in the inventory(stock)
	 * @param i the item on the floor
	 * @exception
	 */
/* 	public void takeAnItem(Board c,Item i) { //throws InventoryFullException{
		int x=0;
		int cmp=0;
		if (this.hand[0]==null) {
			this.hand[0]=i;
		}
		else {
			while(this.stock.length>x){
				if (this.stock[x]!=null) {
					cmp++;
				}
				else {
					this.stock[x]=i;
				}
				x++;
			}
			if (cmp==4) {
				//throw new InventoryFullException();
			}
		}
	} */


	/**
	 * take an item on the floor, if there is no item in the hand, the item go in the hand else in the inventory(stock)
	 * @param b the board where the Player and the Item are
	 * @param i index of the item on the floor
	 * @exception throw if the inventory of the player is full
	 */
/* 	public void takeAnItem2(Board b, int i) throws InventoryFullException{
		Celulle c =b.getCell()[this.x][this.y];
		List<Item> l = c.getItem(); //liste des Item dans la cellule
		int x=0;
		int cmp=0;
		if (this.hand[0]==null) { // on verifie si la main du joueur est vide, si oui on ajoute l'Item dans la main du joueur
			this.hand[0]=l.get(i); // ajout de l'Item dans la main
			c.getItem().remove(i); // aprés l'ajout de l'Item dans la main du joueur, on enleve l'Item de la Cellule
		}
		else {
			while(this.stock.length>x){
				
				if (this.stock[x]!=null) {
					cmp++;
				}
				else {
					this.stock[y]=l.get(i);
					c.getItem().remove(i);
				}
				x++;
			}
			if (cmp==4) {
				throw new InventoryFullException();
			}
		}
	}
 */
	/**
	 * delete an item from the player stock
	 * @param i, the item that we delete
	 */
	public void deleteItem(Item i) {
		for (int x=0; x<this.stock.length; x++) {
			if (this.stock[x]==i) {
				this.stock[x]=null;
			}
		}
	}
	
	/** @return random result of a 1d6 throw*/
	  public int oneDieThrow() {
	    return Player.random.nextInt(6)+ 1;
	  }

	 /** @return random result of a 2d6 throw */
		public int twoDiceThrow() {
			int result = oneDieThrow() + oneDieThrow();
		return result ;
		}
		
		/** @return random result of a 3d6 throw */
		public int threeDiceThrow() {
			int result = oneDieThrow() + oneDieThrow() + oneDieThrow();
		return result ;
		}
		/**
		 * set the number of action points thanks to his level
		 */
		public void checkPalier() {
			if (this.level < 3) {
				this.actionpoint = 3;
			}
			if(this.level >=3 && this.level<7) {
				this.actionpoint = 4;
			}
			if(this.level >=7 && this.level<11) {
				this.actionpoint = 5;
			}
			if(this.level >=11) {
				this.actionpoint = 6;
			}
		}
		
		public void destroyHandItem() {
			if (this.hand[0]!=null) {
				this.hand[0]=null;
			}
		}

		public int NbItem(){
			int nb = 0;
			for(Item item : this.stock){
				if(item != null){
					nb++;
				}
			}
			return nb;
		}
}

