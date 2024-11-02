package projet.celulle;

public class DrogStore extends Piece{
	 /**
     * the constructor of the class DrogStore, it initialize the cell and sets up the configuration
     * 
     * @param x the location of the cell DrogStore based on axes x
     * @param y the location of the cell DrogStore based on axes y
     */
    public DrogStore(int x,int y){
        super(x,y);
    }
    /**
     * @return string
     */
    public String toString() {
    	return "D";
    }
}
