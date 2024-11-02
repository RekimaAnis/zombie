package projet.action;
import projet.celulle.*;
import projet.player.*;
import projet.zombie.*;
import projet.item.*;
import projet.item.weapon.*;
import projet.*;
import projet.celulle.*;
import projet.exception.*;
import java.util.*;
import java.util.Random;

public class ActionZombie implements ActionForZombie {
	
	protected static Random random = new Random( );
	private Board board;

	public ActionZombie(Board board) {
		this.board = board;
	}
	
	
	/**
	 * Can make a zombie attack a player
	 * @param zombie who attack the player
	 * @throws ZombieDontHaveEnoughActionPointException
	 */
	public void attack(Zombie zombie) throws ZombieDontHaveEnoughActionPointException, DoorIsClose {
		try {
			if (zombie.getActionpoint() <= 0) {
				throw new ZombieDontHaveEnoughActionPointException();
			}
	
			List<Player> playerlist = this.board.getCell()[zombie.getx()][zombie.gety()].getPlayer();
			if (playerlist.isEmpty()) {
				move(zombie);
				return;
			}
	
			/**int livingPlayersCount = 0;
			for (Player player : playerlist) {
				if (player.getInlife()) {
					livingPlayersCount++;
				}
			}**/
			List<Player> livingPlayer = new ArrayList<>();
			for(Player player: playerlist){
				if(player.getInlife()){
					livingPlayer.add(player);
				}
			}
	
			if (livingPlayer.size() == 0) {
				move(zombie);
				return;
			}
	
			int cible = ActionZombie.random.nextInt(livingPlayer.size());
			/**while (!playerlist.get(cible).getInlife()) {
				cible = ActionZombie.random.nextInt(playerlist.size());
				System.out.println("on arrive jusqua la ");
			}**/
	
			Player target = livingPlayer.get(cible);
			int damage = zombie.getDamage();
			target.setLife(target.getLife() - damage);
			target.PlayerDie();
			this.board.getCell()[zombie.getx()][zombie.gety()].setPlayerList(playerlist);
			zombie.setActionpoint(zombie.getActionpoint() - 1);
			System.out.println(target.getName() + " a maintenant "+ target.getLife() + " points de vie ");
		} catch (ZombieDontHaveEnoughActionPointException e) {
			System.out.println("Impossible d'attaquer : le zombie n'a pas assez de points d'action.");
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite lors de l'attaque : " + e.getMessage());
		}
	}

	
	//alternative
	/**
	 * Can make a zombie attack a player
	 * @param zombie who attack the player
	 * @param playerlist all the player in the same cell of the zombie
	 * @throws ZombieDontHaveEnoughActionPointException
	 */
	/*
	public void attack(Zombie zombie, List<Player> playerlist) throws ZombieDontHaveEnoughActionPointException {
		if (zombie.getActionpoint()<=0) {
			throw new ZombieDontHaveEnoughActionPointException();
		}
		int cible = ActionZombie.random.nextInt(playerlist.size()+1);
		if (playerlist.get(cible).getInlife()==false) {
			this.zombieAction(zombie);
		}
		if (zombie instanceof Runners) {
			playerlist.get(cible).setLife(playerlist.get(cible).getLife() - zombie.getDamage());
			zombie.setActionpoint(zombie.getActionpoint()-1);
			playerlist.get(cible).PlayerDie();
			this.zombieAction(zombie);
		}
		playerlist.get(cible).setLife(playerlist.get(cible).getLife() - zombie.getDamage());
		zombie.setActionpoint(zombie.getActionpoint()-1);
		playerlist.get(cible).PlayerDie();
		this.zombieAction(zombie);
	}
	*/
	/**
	 * make the zombie move or attack
	 * @param zombie
	 * @throws ZombieDontHaveEnoughActionPointException
	 */
	/*
	public void zombieAction(Zombie zombie) throws ZombieDontHaveEnoughActionPointException{//peut etre faire un while zombie.getActionpoint()>0
		//if zombie dead systemout
		//if liste de player vide alors move
		for (Player p : this.board.getCell()[zombie.getx()][zombie.gety()].getPlayer()) {
			if(p.getInlife()) {
				attack(zombie, this.board.getCell()[zombie.getx()][zombie.gety()].getPlayer());
			}
		//else move
		}
	}
	*/

	/**
	 * if the zombie is in the same cell as the player, he will automatically attack the player 
	 * @param zombie
	 * @param playerlist
	 */

	public void zombieAttackPlayer(Zombie zombie, List<Player> playerlist){
		for (Player player : playerlist) {
			if (player.getx() == zombie.getx() && player.gety() == zombie.gety()) {
				if (player.getInlife()) {
					//attack(zombie, Collections.singletonList(player));
				}
				break; 
			}
		}	
	}	

	/**public void move(Zombie zombie) throws DoorIsClose{
		Celulle[][] cell = this.board.getCell();
		int deplacementLigne = 0;
		int deplacementColonne = 0;
		if(cell[zombie.getx()][zombie.gety()].getNoise()>0){
			int noise = cell[zombie.getx()][zombie.gety()].getNoise();
			if(cell[zombie.getx()+1][zombie.gety()].getNoise() > noise ){
				deplacementLigne++;
			}
			else if(cell[zombie.getx()-1][zombie.gety()].getNoise() > noise ){
				deplacementLigne--;
			}
			else if(cell[zombie.getx()][zombie.gety()+1].getNoise() > noise){
				deplacementColonne++;
			}
			else if(cell[zombie.getx()][zombie.gety()-1].getNoise() > noise){
				deplacementColonne--;
			}
			int newx = zombie.getx() + deplacementLigne;
			int newy = zombie.gety() + deplacementColonne;
			cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
			zombie.setx(newx);
			zombie.sety(newy);
			cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
			System.out.println("le zombie a avancé d'une case ");
		}
		else if(cell[zombie.getx()][zombie.gety()].getNoise()==0){
			int x = random.nextInt(2);
			int y = random.nextInt(2);
			if(x == 1 ){
				deplacementLigne++;
				int newx = zombie.getx() + deplacementLigne;
				int newy = zombie.gety() + deplacementColonne;
				if(newy < this.board.getColonne() && cell[newx][newy] instanceof Piece ){
					Piece piece = (Piece)cell[newx][newy];
					if(zombie.getx() - newx == -1){
						if(piece.getAllDoor().get("north").getDoor()){
							cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
							zombie.setx(newx);
							zombie.sety(newy);
							cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
							System.out.println("le zombie a avancé d'une case ");
						}
						else{
							System.out.println("int x " + x);
							throw new DoorIsClose();
						}
					}
					else {
						this.move(zombie);
					}
				}
			}
			else if(x == 0){
				deplacementLigne--;
				int newx = zombie.getx() + deplacementLigne;
				int newy = zombie.gety() + deplacementColonne;
				if(newy > 0 && cell[newx][newy] instanceof Piece ){
					Piece piece = (Piece)cell[newx][newy];
					if(zombie.getx() - newx == 1){
						if(piece.getAllDoor().get("south").getDoor()){
							cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
							zombie.setx(newx);
							zombie.sety(newy);
							cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
							System.out.println("le zombie a avancé d'une case ");
						}
						else{
							System.out.println("int x "+x);
							throw new DoorIsClose();
						}
					}
					else {
						this.move(zombie);
					}
				}
			}
			else if(y == 1){
				deplacementColonne++;
				int newx = zombie.getx() + deplacementLigne;
				int newy = zombie.gety() + deplacementColonne;
				if(newy < this.board.getColonne() && cell[newx][newy] instanceof Piece ){
					Piece piece = (Piece)cell[newx][newy];
					if(zombie.gety() - newy == 1){
						if(piece.getAllDoor().get("west").getDoor()){
							cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
							zombie.setx(newx);
							zombie.sety(newy);
							cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
							System.out.println("le zombie a avancé d'une case ");
						}
						else{
							System.out.println("int "+y);
							throw new DoorIsClose();
						}
					}
					else {
						this.move(zombie);
					}
				}
			}
			else if(y == 0){
				deplacementColonne--;
				int newx = zombie.getx() + deplacementLigne;
				int newy = zombie.gety() + deplacementColonne;
				if(newy > 0 && cell[newx][newy] instanceof Piece ){
					Piece piece = (Piece)cell[newx][newy];
					if(zombie.gety() - newy == -1){
						if(piece.getAllDoor().get("east").getDoor()){
							cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
							zombie.setx(newx);
							zombie.sety(newy);
							cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
							System.out.println("le zombie a avancé d'une case ");
						}
						else{
							System.out.println("int y "+y);
							throw new DoorIsClose();
						}
					}
					else {
						this.move(zombie);
					}
				}
			}
		}
	}**/

	/**
	 * make the zombie moove
	 * @param zombie
	 * @param direction
	 * @throws DoorIsClose, the zombie can't go through a piece with the enter closed by a door
	 */
	public void move1(Zombie zombie, String direction)throws DoorIsClose {
		Celulle[][] cell = this.board.getCell();
		int deplacementLigne = 0;
		int deplacementColonne = 0;
	
		try {
			if (cell[zombie.getx()][zombie.gety()].getNoise() > 0) {
				int noise = cell[zombie.getx()][zombie.gety()].getNoise();
				if (cell[zombie.getx() + 1][zombie.gety()].getNoise() > noise) {
					deplacementLigne = 1;
				} else if (cell[zombie.getx() - 1][zombie.gety()].getNoise() > noise) {
					deplacementLigne = -1;
				} else if (cell[zombie.getx()][zombie.gety() + 1].getNoise() > noise) {
					deplacementColonne = 1;
				} else if (cell[zombie.getx()][zombie.gety() - 1].getNoise() > noise) {
					deplacementColonne = -1;
				}
				int newx = zombie.getx() + deplacementLigne;
				int newy = zombie.gety() + deplacementColonne;
				cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
				zombie.setx(newx);
				zombie.sety(newy);
				cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
				System.out.println("Le zombie a avancé d'une case.");
			} else {
				int x = zombie.getx();
				int y = zombie.gety();
				int newx = x;
				int newy = y;
	
				switch (direction.toLowerCase()) {
					case "nord":
						deplacementLigne = -1;
						newx = x + deplacementLigne;
						break;
					case "sud":
						deplacementLigne = 1;
						newx = x + deplacementLigne;
						break;
					case "est":
						deplacementColonne = 1;
						newy = y + deplacementColonne;
						break;
					case "ouest":
						deplacementColonne = -1;
						newy = y + deplacementColonne;
						break;
					default:
						System.out.println("Direction invalide.");
						return;
				}
	
				if (newx >= 0 && newx < this.board.getLigne() && newy >= 0 && newy < this.board.getColonne()) {
					if (cell[newx][newy] instanceof Piece) {
						Piece piece = (Piece) cell[newx][newy];
						switch (direction.toLowerCase()) {
							case "nord":
								if (!piece.getAllDoor().get("south").getDoor()) {
									throw new DoorIsClose();
								}
								break;
							case "sud":
								if (!piece.getAllDoor().get("north").getDoor()) {
									throw new DoorIsClose();
								}
								break;
							case "est":
								if (!piece.getAllDoor().get("west").getDoor()) {
									throw new DoorIsClose();
								}
								break;
							case "ouest":
								if (!piece.getAllDoor().get("east").getDoor()) {
									throw new DoorIsClose();
								}
								break;
						}
					}
	
					cell[zombie.getx()][zombie.gety()].getZombie().remove(zombie);
					zombie.setx(newx);
					zombie.sety(newy);
					cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
					System.out.println("Le zombie a avancé d'une case vers " + direction);
				} else {
					System.out.println("Déplacement impossible, en dehors des limites du tableau.");
				}
			}
		} catch (DoorIsClose e) {
			System.out.println("Impossible de déplacer le zombie : la porte est fermée dans la direction indiquée.");
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}
	}
	
	/**
	 * give a random way between north, south, est, west for the move1
	 * @param zombie, the zombie that will move in a random way
	 * @throws DoorIsClose, the zombie can't go through a piece with the enter closed by a door
	 */
	public void move(Zombie zombie) throws DoorIsClose {
		String[] direction = {"nord", "sud", "est", "ouest"};
		int rand = random.nextInt(4);
	
		try {
			this.move1(zombie, direction[rand]);
			zombie.setActionpoint(zombie.getActionpoint() - 1);
		} catch (DoorIsClose e) {
			System.out.println("Impossible de déplacer le zombie : la porte est fermée dans la direction indiquée.");
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite lors du déplacement du zombie : " + e.getMessage());
		}
	}

	public void ResetPoint(List<Zombie> zombie, int point){
		for(int i=0; i<zombie.size(); i++){
			zombie.get(i).setActionpoint(point);
		}
	}
	
}
