package projet.celulle;
import projet.zombie.*;

public class Sewer extends Street{
	 /**
     * the constructor of the class sewer, it initialize the cell and sets up the configuration
     * 
     * @param x the location of the cell sewer based on axes x
     * @param y the location of the cell sewer based on axes y
     */
	public Sewer(int x,int y) {
		super(x,y);
	}
	/**
	 * @return string
	 */
	public String toString(){
		return "E";
	}
}
