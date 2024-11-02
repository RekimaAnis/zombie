package projet.player;

public class Healer extends Player{
	
	private boolean soigner;
	/**
	 * constructor of the Healer
	 * @param name, of the Healer
	 */
    public Healer(String name){
        super(name);
        this.soigner = true;
    }
    /**
     * get the capacity of this class to heal
     * @return boolean soigner, true if he can heal, false if he cannot
     */
    public boolean getSoigner() {
    	return this.soigner;
    }
    /**
     * set the ability to heal
     * @param s, true if he can heal, false if he cannot
     */
    public void setSoigner(boolean s) {
    	this.soigner=s;
    }
}
