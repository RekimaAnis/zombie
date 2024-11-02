package projet.celulle;
import java.util.*;

public class Piece extends Celulle{
	protected boolean north;
	protected boolean south;
	protected boolean east;
	protected boolean west;
	protected Map<String,Door> allDoor;
	
	/**
	 * constructor of the class piece
	 * @param x, location of the piece on axe x
	 * @param y, location of the piece on axe y
	 */
    public Piece(int x,int y){
        super(x,y);
        this.north=true;
        this.south=true;
        this.east=true;
        this.west=true;
        this.allDoor=new HashMap<String,Door>();
   }
    
    /**
     * get the piece north
     * @return boolean (true if the north door is closed, else it's false)
     */
    public boolean getNorth() {
    	return this.north;
    }
    /**
     * get the piece south
     * @return boolean (true if the north door is closed, else it's false)
     */
    public boolean getSouth() {
    	return this.south;
    }
    /**
     * get the piece East
     * @return boolean (true if the north door is closed, else it's false)
     */
    public boolean getEast() {
    	return this.east;
    }
    /**
     * get the piece West
     * @return boolean (true if the north door is closed, else it's false)
     */
    public boolean getWest() {
    	return this.west;
    }
    /**
     * map will all door status
     * @return the status of all door
     */
    public Map<String,Door> getAllDoor() {
    	return this.allDoor;
    }
	/**
	 * verify if the can can be set in the room
	 * 
	 * @param x 
	 * @param y
	 */
    public void canDoor(int x,int y) {
    	if(this.height==0) {
    		this.west=false;
    	}
    	if(this.width==0) {
    		this.north=false;
    	}
    	if(this.width==x-1) {
    		this.south=false;
    	}
    	if(this.height== y-1) {
    		this.east=false;
    	}
    	
    }

	/**
	 * add the door in the room
	 * @param nbColonne 
	 * @param nbLigne 
	 */
    public void addDoor(int nbLigne, int nbColonne) {
    	if(north==true) {
    		this.allDoor.put("north",new Door());
    	}
    	if(south==true) {
    		this.allDoor.put("south",new Door());
    	}
    	if(west==true) {
    		this.allDoor.put("west",new Door());
    	}
    	if(east==true) {
    		this.allDoor.put("east",new Door());
    	}
    }

	/**
	 * get String
	 *
	 * @return String
	 */
    public String toString() {
    	return "P";
    }
}


