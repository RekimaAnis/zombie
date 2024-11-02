package projet.celulle;

public class Street extends Celulle{
	
	/**
     * the constructor of the class street, it initialize the cell and sets up the configuration
     * 
     * @param x the location of the cell street based on axes x
     * @param y the location of the cell street based on axes y
     */
	public Street(int x,int y){
        super(x,y);
    }
	/**
	 * @return string
	 */
    public String toString() {
    	return "*";
    }
}
