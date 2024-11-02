package projet;
import java.util.*;
import projet.action.*;
import projet.celulle.*;
import projet.item.heal.*;
import projet.item.weapon.*;
import projet.item.utilities.*;
import projet.player.*;
import projet.zombie.*;
import projet.listchooser.src.listchooser.*;
import projet.item.*;
import projet.item.utilities.Maps;
import projet.exception.*;

public class Game{
	public static void main(String[] args)
	throws NotAWeaponException, ZombieIsOutOfRangeException, PlayerDontHaveEnoughActionPointException, NotGlassesException, CanNotSearchInStreet, DoorIsClose, NotAHealException, NotAHealerException, PlayerAlreadyDeadException, HealerDontHaveThisSpell, ZombieDontHaveEnoughActionPointException, InventoryFullException {
		System.out.print("What type of board you want to play in? \n");
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		Board board = new Board(a,b);
		InteractiveListChooser<String> listChooser = new InteractiveListChooser<>();
		ActionPlayer action = new ActionPlayer(board);
		ActionZombie action_z = new ActionZombie(board);
		List<String> action_player = Arrays.asList("move", "attack", "lookAroundYourSelf", "infraredVision", "searchInRoom", "OpenADoor", "soigner", "heal","Map", "MakeNoise", "TakeAHand", "TakeAnItem", "lookAtTheStock");          
		int tour = 0;

		ArrayList<Player> player = new ArrayList<>();
		ArrayList<Zombie> zombie = new ArrayList<>();     
		ArrayList<Item> item = new ArrayList<>();
		ArrayList<Item> medoc = new ArrayList<>();

		Flask flask = new Flask();
		Maps map = new Maps();
		Gun gun = new Gun();
		Axe axe = new Axe();
		Glasses glasses = new Glasses();
		MedKit medkit = new MedKit();
		MasterKey key = new MasterKey();
		Chainsaw chainsaw = new Chainsaw();
		Crowbar crowbar = new Crowbar();
		Rifle rifle = new Rifle();


		Player player1 = new Player("player");
		Lucky lucky = new Lucky("lucky");
		Fighter fighter = new Fighter("fighter");
		Healer healer = new Healer("healer");
		Scavenger scavenger = new Scavenger("scavenger");

		player.add(player1);
		player.add(fighter);
	    player.add(healer);
	    player.add(lucky);
	    player.add(scavenger);

		action.takeAnItem(board,fighter,axe);
       	action.takeAnItem(board,lucky,axe);
		action.takeAnItem(board,player1,axe);
		action.takeAnItem(board,healer,axe);
		action.takeAnItem(board,scavenger,axe);

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
		item.add(gun);
		item.add(medkit);
		item.add(key);
		item.add(chainsaw);
		item.add(crowbar);
		item.add(rifle);

		medoc.add(medkit);
		medoc.add(flask);
		
		board.placePlayer(player);
		board.placeZombie();
		board.placeItem(item);

       
       board.placeHeal(medoc);


		while(board.checkAllPlayerAlive() && board.checkAllZombieAlive() && !board.checkLevel30()){	
			board.display();
			for(int i=0; i<player.size(); i++){
				while(player.get(i).getActionpoint()>0){
					String chosenAction = listChooser.choose("choose an action : ", action_player);
					if(chosenAction.equals("move")){
						List<String> directions = Arrays.asList("nord", "sud", "est", "ouest");
						String chosenDirection = listChooser.choose("Choisissez une direction :", directions);
						action.move1(player.get(i), chosenDirection);
						board.display();
					}
					else if(chosenAction.equals("attack")){
						List<String> listeZombie = new ArrayList<>(); 
						for(int j=0; j < board.getCell()[player.get(i).getx()][player.get(i).gety()].getZombie().size(); j++){
							listeZombie.add(board.getCell()[player.get(i).getx()][player.get(i).gety()].getZombie().get(j).getId());
						}
						String chosenZombie = listChooser.choose("choose an zombie to attack : ",listeZombie);
						for(int z=0; z < board.getCell()[player.get(i).getx()][player.get(i).gety()].getZombie().size(); z++){
							if(board.getCell()[player.get(i).getx()][player.get(i).gety()].getZombie().get(z).getId().equals(chosenZombie)){
								action.attack(player.get(i), board.getCell()[player.get(i).getx()][player.get(i).gety()].getZombie().get(z) );
							}
							break;
						}
						board.display();
					}
					else if(chosenAction.equals("lookAroundYourSelf")){
						action.lookAroundYourself(player.get(i).getx(), player.get(i).gety());
						System.out.println();
					}
					else if(chosenAction.equals("infraredVision")){
						action.infraredVision(player.get(i).getx(), player.get(i).gety(), player.get(i));
						System.out.println();
					}
					else if(chosenAction.equals("searchInRoom")){
						action.searchInRoom(player.get(i).getx(), player.get(i).gety(), player.get(i));
						System.out.println();
					}
					else if(chosenAction.equals("OpenADoor")){
						List<String> directions = Arrays.asList("nord", "sud", "est", "ouest");
						String chosenDirection = listChooser.choose("Choisissez une direction :", directions);
						action.OpenADoor(player.get(i).getx(), player.get(i).gety(), chosenDirection, player.get(i));
						System.out.println();
					}
					else if(chosenAction.equals("heal")){
						action.heal(player.get(i));
						System.out.println();
					}
					else if(chosenAction.equals("Map")){
						action.display(player.get(i));
					}
					else if(chosenAction.equals("MakeNoise")){
						action.MakeNoise(player.get(i).getx(), player.get(i).gety(), 1, player.get(i));
						System.out.println("Du bruit a été fait");
					}
					else if(chosenAction.equals("TakeAHand")){
						List<String> items = new ArrayList<>();
						for(int j=0; j<player.get(i).NbItem(); j++ ){
							items.add(player.get(i).getStock()[j].getId());
						}
						String chosenItem = listChooser.choose("Choisissez un item :", items);
						for(int z=0; z < player.get(i).getStock().length; z++){
							if(player.get(i).getStock()[z].getId().equals(chosenItem)){
								action.TakeAHand(player.get(i),z);
							}
							break;
						}
					}
					else if(chosenAction.equals("TakeAnItem")){
						List<String> items = new ArrayList<>();
						for(int j=0; j<board.getCell()[player.get(i).getx()][player.get(i).gety()].getItem().size(); j++){
							items.add(board.getCell()[player.get(i).getx()][player.get(i).gety()].getItem().get(j).getId());//[Axe287,Flask574,...]
						}
						String chosenItem = listChooser.choose("Choisissez un item :", items);
						for(int z=0; z < board.getCell()[player.get(i).getx()][player.get(i).gety()].getItem().size(); z++){
							if(board.getCell()[player.get(i).getx()][player.get(i).gety()].getItem().get(z).getId().equals(chosenItem)){
								action.takeAnItem(board, player.get(i), (board.getCell()[player.get(i).getx()][player.get(i).gety()].getItem().get(z)));
							}
							break;
						}
					}
					else if(chosenAction.equals("lookAtTheStock")){
						action.lookAtTheStock(player.get(i));
					}
					else{
						action.soigner(healer, player.get(i));
						System.out.println();
					}
				}
			}
			for(int j=0; j<board.getZombie().size(); j++){
				if(board.getZombie().get(j).getInLife()){
					if(board.getCell()[board.getZombie().get(j).getx()][board.getZombie().get(j).gety()].getPlayer().size()>0){
						action_z.attack(board.getZombie().get(j));
					}
					else{
						action_z.move(board.getZombie().get(j));
					}
				}
				else{
					System.out.println("ce zombie est deja mort il ne peut plus bouger");
				}
			}
			tour++;
			action.ResetPoint(player);
			action_z.ResetPoint(board.getZombie(), 1);
			board.removeplayerWhenDead();
			board.removezombieWhenDead();
			for(int k=0; k<board.averangePlayer(); k++){
				board.placeZombie();
			}
			board.placeHeal(medoc);

		}
		System.out.println("etat du plateau a la fin : ");
		board.display();
		System.out.println("partie fini avec : "+tour);
	}
}
