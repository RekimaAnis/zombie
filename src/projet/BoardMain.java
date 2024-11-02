package projet;
import projet.zombie.*;
import java.util.*;
import projet.item.*;
import projet.item.heal.*;
import projet.item.utilities.Maps;
import projet.item.weapon.*;
import projet.player.*;
import projet.action.*;
import projet.exception.*;
import projet.listchooser.src.listchooser.*;



public class BoardMain {
    public static void main(String[] args)throws CanNotSearchInStreet, DoorIsClose,NotAHealException, ZombieDontHaveEnoughActionPointException, PlayerDontHaveEnoughActionPointException, ZombieIsOutOfRangeException, NotAWeaponException {
       //select type of board to play
       System.out.print("What type of board you want to play in? \n");
       Scanner sc = new Scanner(System.in);
       Scanner sca = new Scanner(System.in);
       int i = sc.nextInt();
       int o = sc.nextInt();
       Board board = new Board(i,o);
       InteractiveListChooser<String> listChooser = new InteractiveListChooser<>();
       List<String> directions = Arrays.asList("nord", "sud", "est", "ouest");
       String chosenDirection = listChooser.choose("Choisissez une direction :", directions);

       ActionPlayer action = new ActionPlayer(board);
       ActionZombie action_z = new ActionZombie(board);

       ArrayList<Player> player = new ArrayList<>();
       ArrayList<Zombie> zombie = new ArrayList<>();     
       ArrayList<Item> item = new ArrayList<>();
       ArrayList<Item> medoc = new ArrayList<>();
       
       Flask flask = new Flask();
       Maps map = new Maps();
       Gun gun = new Gun();
       Axe axe = new Axe();
       Player player1 = new Player("player");
       Lucky lucky = new Lucky("lucky");
       Fighter fighter = new Fighter("fighter");
       Healer healer = new Healer("healer");
       Scavenger scavenger = new Scavenger("scavenger");
       
       
/*        fighter.takeAnItem(board,axe);
       lucky.takeAnItem(board,flask); */

       player.add(player1);
       player.add(fighter);
       player.add(healer);
       player.add(lucky);
       player.add(scavenger);

       Zombie zombie1 = new Zombie();
       Runners runners = new Runners();
       Walkers walkers = new Walkers();
       Zombie zombie2 = new Zombie();
       zombie.add(zombie1);
       zombie.add(runners);
       zombie.add(walkers);
       zombie.add(zombie2);
       
       item.add(map);
       item.add(flask);
       medoc.add(flask);
       
       board.placePlayer(player);
       board.placeZombie();
       board.placeItem(item);

       
       board.placeHeal(medoc);
       board.display();
       System.out.println();
       System.out.println("le fighter a dans sa main : " + fighter.getHand()[0].getId());
       System.out.println("le lucky a dans sa main : " + lucky.getHand()[0].getId());
       System.out.println();
       action.move1(player1,chosenDirection);
       action.move1(fighter,chosenDirection);
       action.move1(lucky, chosenDirection);
       action.move1(healer, chosenDirection);
       action.move1(scavenger, chosenDirection);
       board.display();
       action.lookAroundYourself(player1.getx(), player1.gety());
       action.move1(fighter, chosenDirection);
       action.OpenADoor(fighter.getx(), fighter.gety(),chosenDirection,fighter);
       action.move1(scavenger,chosenDirection);
       action.searchInRoom(scavenger.getx(),scavenger.gety(), scavenger);
       action.heal(lucky);
       board.display();
       action_z.attack(zombie1);
       action_z.attack(runners);
       action_z.move(walkers);
       action_z.move(zombie2);
       board.display();
       action.attack(fighter,board.getCell()[scavenger.getx()][scavenger.gety()].getZombie().get(0));
    }
}
