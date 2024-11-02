package projet.celulle;

public class Door {
	private boolean door;
	
	/**
	 * constructor of the class door, initialize the door and set up the configuration
	 */
	public Door() {
		this.door=false;
	}
	/**
	 * get the door status(true = open; false = close)
	 * 
	 * @return the status of the door
	 */
	public boolean getDoor() {
		return this.door;
	}
	
	/**
	 * change the status of the door to open, if the door is close we open it and if the door is already open we change nothing
	 * 
	 * @return the status of the door to open (false)
	 */
	public boolean OpenTheDoor(){
		if(this.door == true) {
			this.door=true;
		}
		else {
			this.door=true;
		}
		return this.door;
	}
	
	/**
	 * change the status of the door to close, if the door is open we close it and if the door is already close we change nothing
	 * 
	 * @return the status o the door to close (true)
	 */
	public boolean CloseTheDoor(){
		if(this.door == false) {
			this.door=false;
		}
		else {
			this.door=false;
		}
		return this.door;
	}

}
