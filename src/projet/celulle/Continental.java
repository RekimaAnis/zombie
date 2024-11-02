package projet.celulle;

public class Continental extends Piece{
    /**
     * the constructor of the class continental, it initialize the cell and sets up the configuration
     * 
     * @param x the location of the cell continental based on axes x
     * @param y the location of the cell continental based on axes y
     */
	public Continental(int x,int y){
        super(x,y);
    }
	
	/**
	 * @return string
	 */
    public String toString() {
    	return "C";
    }
}
