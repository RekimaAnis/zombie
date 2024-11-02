package projet;
import projet.zombie.*;
import java.util.*;

import projet.exception.InventoryFullException;
import projet.item.Item;
import projet.item.heal.*;
import projet.item.utilities.Maps;
import projet.player.*;

public class TrainingBoardMain {
    public static void main(String[] args) throws InventoryFullException{
       TrainingBoard board = new TrainingBoard();
       ArrayList<Player> player = new ArrayList<>();
       ArrayList<Zombie> zombie = new ArrayList<>();       
       ArrayList<Item> item = new ArrayList<>();
       Flask flask = new Flask();
       Maps map = new Maps();
       Player player1 = new Player("1");
       player.add(player1);
       zombie.add(new Zombie());
       zombie.add(new Zombie());
       item.add(map);
       item.add(flask);
       board.placePlayer(player);
       board.placeZombie();
       System.out.println();
       System.out.println("le joueur a dans sa main : " + player1.getHand()[0].getId());
       System.out.println("le joueur a dans son sac : " + player1.getStock()[0].getId());
       System.out.println("Le joueur avance d'une case");
       System.out.println();
       board.display();
    
    }
}