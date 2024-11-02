package projet;
import java.util.ArrayList;
import java.util.Random;
import projet.Board;
import projet.celulle.*;
import projet.item.heal.Flask;
import projet.item.utilities.Maps;
import projet.zombie.Zombie;
import projet.player.*;

public class TrainingBoard extends Board {

	public Celulle[][] board;
    private Random random;

	public TrainingBoard() {
		super(5,5);
	}
}


