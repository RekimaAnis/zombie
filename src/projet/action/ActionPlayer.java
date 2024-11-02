package projet.action;
import java.util.*;
import projet.celulle.*;
import projet.player.*;
import projet.zombie.*;
import projet.item.*;
import projet.item.weapon.*;
import projet.item.heal.*;
import projet.*;
import projet.celulle.*;
import projet.exception.*;
import projet.item.utilities.*;

public class ActionPlayer implements ActionForPlayer {

	private Board board;
	private Random random;

	public ActionPlayer(Board board) {
		this.board = board;
		this.random = new Random();
	}

	/**
	 * Player can move with this methods
	 */
	public void move(Player player, String direction) {
		int deplacementLigne = 0;
		int deplacementColonne = 0;

		switch (direction.toLowerCase()) {
			case "nord":
				deplacementLigne = -1;
				break;
			case "sud":
				deplacementLigne = 1;
				break;
			case "est":
				deplacementColonne = 1;
				break;
			case "ouest":
				deplacementColonne = -1;
				break;
			default:
				System.out.println("Direction invalide.");
				return;
		}
		int newx = player.getx() + deplacementLigne;
		int newy = player.gety() + deplacementColonne;
		Celulle[][] cell = this.board.getCell();
		cell[player.getx()][player.gety()].getPlayer().remove(player);
		player.setx(newx);
		player.sety(newy);
		cell[player.getx()][player.gety()].setPlayer(player);
		System.out.println("le joueur a avancé d'une case vers " + direction);
	}

	public void move1(Player player, String direction) {
		int deplacementLigne = 0;
		int deplacementColonne = 0;
		Celulle[][] cell = this.board.getCell();
		int x = player.getx();
		int y = player.gety();
	
		try {
			switch (direction.toLowerCase()) {
				case "nord":
					deplacementLigne = -1;
					if (cell[x + deplacementLigne][y] instanceof Piece) {
						Piece piece = (Piece) cell[x + deplacementLigne][y];
						if (!piece.getAllDoor().get("south").getDoor()) {
							throw new DoorIsClose();
						}
					}
					break;
				case "sud":
					deplacementLigne = 1;
					if (cell[x + deplacementLigne][y] instanceof Piece) {
						Piece piece = (Piece) cell[x + deplacementLigne][y];
						if (!piece.getAllDoor().get("north").getDoor()) {
							throw new DoorIsClose();
						}
					}
					break;
				case "est":
					deplacementColonne = 1;
					if (cell[x][y + deplacementColonne] instanceof Piece) {
						Piece piece = (Piece) cell[x][y + deplacementColonne];
						if (!piece.getAllDoor().get("west").getDoor()) {
							throw new DoorIsClose();
						}
					}
					break;
				case "ouest":
					deplacementColonne = -1;
					if (cell[x][y + deplacementColonne] instanceof Piece) {
						Piece piece = (Piece) cell[x][y + deplacementColonne];
						if (!piece.getAllDoor().get("east").getDoor()) {
							throw new DoorIsClose();
						}
					}
					break;
				default:
					System.out.println("Direction invalide.");
					return;
			}
	
			int newx = player.getx() + deplacementLigne;
			int newy = player.gety() + deplacementColonne;
	
			// Déplacer le joueur
			cell[player.getx()][player.gety()].getPlayer().remove(player);
			player.setx(newx);
			player.sety(newy);
			cell[player.getx()][player.gety()].setPlayer(player);
			player.setActionpoint(player.getActionpoint() - 1);
			System.out.println(player.getName()+ " a avancé d'une case vers " + direction);
			System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
	
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Vous ne pouvez pas aller dans cette direction : bordure de la carte.");
		} catch (DoorIsClose e) {
			System.out.println("Vous ne pouvez pas aller dans cette direction : porte fermée.");
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}
	}
 

	/**
	 * programe that make the player attack the zombie
	 * 
	 * @param player the player that gonna attack the zombie
	 * 
	 * @param zombie the zombie who's been attack
	 */

	public void attack(Player player, Zombie zombie)
			throws NotAWeaponException, ZombieIsOutOfRangeException, PlayerDontHaveEnoughActionPointException {
				try {
					Item[] hand = player.getHand();
					if (!(hand[0] instanceof Weapon)) {
						throw new NotAWeaponException();
					}
					if (!zombie.getInLife()) {
						System.out.println("Ce zombie est déjà mort");
						return;
					}
					if (player.getActionpoint() <= 0) {
						throw new PlayerDontHaveEnoughActionPointException();
					} else {
						int dice = player.oneDieThrow();
						int dice2 = player.twoDiceThrow();
						int dice3 = player.threeDiceThrow();
						if (player instanceof Fighter) {
							dice += 1;
							dice2 += 2;
						}
						if (player instanceof Lucky) {
							dice = dice2;
							dice2 = dice3;
						}
						if ((hand[0] instanceof Axe) && dice >= hand[0].getTreshold()) {
							if (zombie.getx() == player.getx() && zombie.gety() == player.gety()) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								System.out.println(  zombie.getId()+ " a maintenant " + zombie.getLife() + " points de vie");
							} else {
								throw new ZombieIsOutOfRangeException();
							}
						}
						if ((hand[0] instanceof Chainsaw) && dice2 >= hand[0].getTreshold()) {
							if (zombie.getx() == player.getx() && zombie.gety() == player.gety()) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								MakeNoise(player.getx(), player.gety(), 1, player);
								System.out.println("Ce zombie a maintenant " + zombie.getLife() + " points de vie");
							} else {
								throw new ZombieIsOutOfRangeException();
							}
						}
						if ((hand[0] instanceof Crowbar) && dice >= hand[0].getTreshold()) {
							if (zombie.getx() == player.getx() && zombie.gety() == player.gety()) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								System.out.println("Ce zombie a maintenant " + zombie.getLife() + " points de vie");
							} else {
								throw new ZombieIsOutOfRangeException();
							}
						}
						if ((hand[0] instanceof Gun) && dice >= hand[0].getTreshold()) {
							if (zombie.getx() == player.getx() && zombie.gety() == player.gety()) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								MakeNoise(player.getx(), player.gety(), 2, player);
								System.out.println("Ce zombie a maintenant " + zombie.getLife() + " points de vie");
							} else if (Math.abs(zombie.getx() - player.getx()) <= 1 && Math.abs(zombie.gety() - player.gety()) <= 1) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								MakeNoise(player.getx(), player.gety(), 2, player);
								System.out.println("Ce zombie a maintenant " + zombie.getLife() + " points de vie");
							} else {
								throw new ZombieIsOutOfRangeException();
							}
						}
						if ((hand[0] instanceof Rifle) && dice2 >= hand[0].getTreshold()) {
							if ((Math.abs(zombie.getx() - player.getx()) <= 3 && zombie.gety() == player.gety()) ||
								(Math.abs(zombie.gety() - player.gety()) <= 3 && zombie.getx() == player.getx())) {
								zombie.setLife(zombie.getLife() - hand[0].getDamage());
								MakeNoise(player.getx(), player.gety(), 2, player);
								System.out.println("Ce zombie a maintenant " + zombie.getLife() + " points de vie");
							} else {
								throw new ZombieIsOutOfRangeException();
							}
						}
					}
					// Vérifie si le zombie est mort
					zombie.zombieDie();
					if (!zombie.getInLife()) {
						player.setLevel(player.getLevel() + 1);
						System.out.println("Le Zombie est mort donc le joueur prend 1 niveau");
					}
					if(zombie.getInLife()){
						System.out.println("Le zombie est encore en vie");
					}
					player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
					System.out.println(player.getName()+ " a encore " + player.getActionpoint() + " point d'action ");
				} catch (NotAWeaponException e) {
					System.out.println("Impossible d'attaquer : l'objet en main n'est pas une arme.");
				} catch (ZombieIsOutOfRangeException e) {
					System.out.println("Impossible d'attaquer : le zombie est hors de portée.");
				} catch (PlayerDontHaveEnoughActionPointException e) {
					System.out.println("Impossible d'attaquer : le joueur n'a pas assez de points d'action.");
				} catch (Exception e) {
					System.out.println("Une erreur s'est produite lors de l'attaque : " + e.getMessage());
				}
			}

	/**
	 * make a noise that can lure zombie into it
	 * @param int x , location of the sound on the axe X
	 * @param int y , location of the sound on the axe y 
	 * @param int noisemaked , unity of the sound produced
	 */
	public void MakeNoise(int x, int y, int noiseMaked, Player player) {

		Celulle[][] cell = this.board.getCell();
		cell[x][y].noise = noiseMaked;
		int n = 1;
		while (noiseMaked > 0) {
			// propagation vers la gauche 
			if (y - n >= 0 && cell[x][y - n] != null) {
				cell[x][y - n].noise += noiseMaked - 1;
			}
			// propagation bruit vers la doite
			if (y + n < this.board.getColonne()) {
				cell[x][y + n].noise += noiseMaked - 1;
			}
			// propagation bruit vers le bas
			if (x + n < this.board.getLigne()) {
				cell[x + n][y].noise += noiseMaked - 1;
			}
			// propagation bruit vers la haut
			if (x - n >= 0) {
				cell[x - n][y].noise += noiseMaked - 1;
			}
			noiseMaked -= 1;
			n++;
		}
		System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
	}

	/**
	 * return a text with a different color
	 * @param text      String
	 * @param colorCode value of the color
	 * @return 
	 */
	public static String getColorString(String text, String colorCode) {
		return "\u001B[" + colorCode + "m" + text + "\u001B[0m";
	}

	/**
	 * make the player look around himself to see if there is any piece, show if the piece has any door (closed or open)
	 * 
	 * @param int i , axe i where we stand on
	 * @param int j , axe j where we stand on
	 */
	public void lookAroundYourself(int i, int j) {
		Celulle[][] cell = this.board.getCell();
		if (cell[i][j] instanceof Piece) {
			Piece piece = (Piece) cell[i][j];

			if (piece.getAllDoor().get("north") != null && !piece.getAllDoor().get("north").getDoor())
				System.out.println(getColorString(" _______", "31"));
			if (piece.getAllDoor().get("north") != null && piece.getAllDoor().get("north").getDoor())
				System.out.println(" ________");

			if (piece.getAllDoor().get("west") != null && !piece.getAllDoor().get("west").getDoor())
				System.out.print(getColorString("| ", "31"));
			if (piece.getAllDoor().get("west") != null && piece.getAllDoor().get("west").getDoor())
				System.out.print("| ");

			for (Player player : cell[i][j].getPlayer()) {
				System.out.print(player.name);
			}
			for (Zombie zombie : cell[i][j].getZombie()) {
				System.out.print(" " + zombie.toString());
			}

			if (piece.getAllDoor().get("east") != null && !piece.getAllDoor().get("east").getDoor())
				System.out.println(getColorString(" | ", "31"));
			if (piece.getAllDoor().get("east") != null && piece.getAllDoor().get("east").getDoor())
				System.out.println("| ");

			if (piece.getAllDoor().get("south") != null && !piece.getAllDoor().get("south").getDoor())
				System.out.println(getColorString("-------", "31"));
			if (piece.getAllDoor().get("south") != null && piece.getAllDoor().get("south").getDoor())
				System.out.println("-------");
		} else {
			if (i - 1 > 0 && cell[i - 1][j] instanceof Piece) {
				Piece piece = (Piece) cell[i - 1][j];
				if (!piece.getAllDoor().get("north").getDoor())
					System.out.println(getColorString(" _______", "31"));
				else
					System.out.println(" ________");
			}
			if (j - 1 > 0 && cell[i][j - 1] instanceof Piece) {
				Piece piece = (Piece) cell[i][j - 1];
				if (!piece.getAllDoor().get("west").getDoor())
					System.out.print(getColorString("| ", "31"));
				else
					System.out.print("| ");
			}
			for (Player player : cell[i][j].getPlayer()) {
				System.out.print(player.name + " ");
			}
			for (Zombie zombie : cell[i][j].getZombie()) {
				System.out.print(" " + zombie.toString());
			}
			if (j + 1 < this.board.getColonne() && cell[i][j + 1] instanceof Piece) {
				Piece piece = (Piece) cell[i][j + 1];
				if (!piece.getAllDoor().get("east").getDoor())
					System.out.println(getColorString(" | ", "31"));
				else
					System.out.println("| ");
			}
			if (i + 1<this.board.getLigne() && cell[i + 1][j] instanceof Piece) {
				Piece piece = (Piece) cell[i + 1][j];
				if (!piece.getAllDoor().get("south").getDoor()) {
					System.out.println();
					System.out.println(getColorString(" -------", "31"));
				}
				else {
					System.out.println();
					System.out.print(" -------");
				}
			}
		}
	}

	/**
	 * use the glasses to see through the wall
	 * @param i, axe i where the player stand on
	 * @param j, axe j where the player stand on
	 * @param player, that use the glasses
	 * @throws NotGlassesException, throw when the item use is not a glasses
	 */
	public void infraredVision(int i, int j, Player player) throws NotGlassesException {
		try {
			Celulle[][] cells = this.board.getCell();
			int ligne = this.board.getLigne();
			int colonne = this.board.getColonne();
	
			if (!(player.getHand()[0] instanceof Glasses)) {
				throw new NotGlassesException();
			}
	
			if (player.getHand()[0] instanceof Glasses) {
				for (int l = Math.max(0, i - 1); l <= Math.min(ligne - 1, i + 1); l++) {
					for (int c = Math.max(0, j - 1); c <= Math.min(colonne - 1, j + 1); c++) {
						if (!(l == i && c == j)) {
							if (cells[l][c] instanceof Piece) {
								Piece piece = (Piece) cells[l][c];
								for (Player p : cells[l][c].getPlayer()) {
									System.out.print(p.getName() + " ");
								}
								for (Zombie zombie : cells[l][c].getZombie()) {
									System.out.print(zombie.toString() + " ");
								}
								if (!piece.getAllDoor().get("north").getDoor()) {
									System.out.println(getColorString(" _______", "31"));
								} else {
									System.out.println(" ________");
								}
								if (!piece.getAllDoor().get("south").getDoor()) {
									System.out.println();
									System.out.println("\n" + getColorString("-------", "31"));
								} else {
									System.out.println();
									System.out.println(" -------");
								}
								if (!piece.getAllDoor().get("east").getDoor()) {
									System.out.println(getColorString(" | ", "31"));
								} else {
									System.out.println("| ");
								}
								if (!piece.getAllDoor().get("west").getDoor()) {
									System.out.print(getColorString("| ", "31"));
								} else {
									System.out.print("| ");
								}
								player.setActionpoint(player.getActionpoint() - 1);
								System.out.print("Un joueur a utilisé " + player.getHand()[0].getId() + ", cet item n'est plus utilisable.");
								player.destroyHandItem();
							}
						}
					}
				}
			}
			player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
			System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
		} 
		catch (NotGlassesException e) {
			System.out.println("Impossible d'utiliser l'infrarouge : le joueur ne porte pas de lunettes.");
		} 
		catch (Exception e) {
			System.out.println("Une erreur s'est produite lors de l'utilisation de l'infrarouge : " + e.getMessage());
		}
	}
	

	/**
	 * search item in a cell where the player stand on
	 * @param i, axe i where we stand on to search an item
	 * @param j, axe j where we stand on to search an item
	 * @throws CanNotSearchInStreet, you cannot search item in a street cell
	 */
	public void searchInRoom(int i, int j, Player player) throws CanNotSearchInStreet {
		try {
			Celulle[][] cell = this.board.getCell();
			if (cell[i][j] instanceof Street) {
				throw new CanNotSearchInStreet();
			} else if (cell[i][j].getItem().size() < 1) {
				System.out.println("Aucun item n'est présent dans la cellule");
			} else {
				System.out.print("Il y a : ");
				for (Item item : cell[i][j].getItem()) {
					System.out.print(item.getId() + " , ");
				}
				System.out.println(" dans la cellule");
			}
			player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
			System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
		} catch (CanNotSearchInStreet e) {
			System.out.println("Impossible de rechercher dans la rue.");
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite lors de la recherche : " + e.getMessage());
		}
	}

	/**
	 * Open a door 
	 * @param i, axe i next to a cell with a door
	 * @param j, axe j next to a cell with a door
	 * @param direction, name of the direction with the door we want to open
	 * @param player, the player that open the door
	 * @throws DoorIsClose, throws exception when we can't open the door (need master key)
	 */
	public void OpenADoor(int i, int j, String direction, Player player)throws DoorIsClose {
		Celulle[][] cell = this.board.getCell();
		try {
			switch (direction.toLowerCase()) {
				case "nord":
					if (cell[i][j] instanceof Piece && cell[i-1][j] instanceof Piece) {
						Piece piece_nord = (Piece) cell[i][j];
						if (!piece_nord.getAllDoor().get("north").getDoor()) {
							try {
								Piece piece_above = (Piece) cell[i-1][j];
								handleDoorOpening(piece_above.getAllDoor().get("south"), player, i-1, j);
								if (piece_above.getAllDoor().get("south").getDoor()){
									piece_nord.getAllDoor().get("north").OpenTheDoor();
								}
								System.out.println("La porte a été ouverte avec succès.");
								player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
								System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
							} catch (DoorIsClose e) {
								System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
							}
						}
					} else {
						if(cell[i-1][j] instanceof Piece){
							Piece piece_nord = (Piece) cell[i - 1][j];
							if (!piece_nord.getAllDoor().get("south").getDoor()) {
								try {
									handleDoorOpening(piece_nord.getAllDoor().get("south"), player, i - 1, j);
									System.out.println("La porte a été ouverte avec succès.");
								} catch (DoorIsClose e) {
									System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
								}
							}
						}
					}
					break;
	
				case "sud":
					if (cell[i][j] instanceof Piece && cell[i+1][j] instanceof Piece) {
						Piece piece_sud = (Piece) cell[i][j];
						if (!piece_sud.getAllDoor().get("south").getDoor()) {
							try {
								Piece piece_below = (Piece) cell[i+1][j];
								handleDoorOpening(piece_below.getAllDoor().get("north"), player, i+1, j);
								if (!piece_below.getAllDoor().get("north").getDoor()){
									piece_sud.getAllDoor().get("south").OpenTheDoor();
								}
								System.out.println("La porte a été ouverte avec succès.");
								player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
								System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
							} catch (DoorIsClose e) {
								System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
							}
						}
					} else {
						if (cell[i+1][j] instanceof Piece){
							Piece piece_sud = (Piece) cell[i + 1][j];
							if (!piece_sud.getAllDoor().get("south").getDoor()) {
								try {
									handleDoorOpening(piece_sud.getAllDoor().get("south"), player, i + 1, j);
									System.out.println("La porte a été ouverte avec succès.");
									player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
									System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
								} catch (DoorIsClose e) {
									System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
								}

							}
						}
					}
					break;
				case "est":
					if (cell[i][j] instanceof Piece && cell [i][j+1] instanceof Piece) {
						Piece piece_east = (Piece) cell[i][j];
						if (!piece_east.getAllDoor().get("east").getDoor()) {
							try {
								Piece piece_right = (Piece) cell[i][j+1];
								handleDoorOpening(piece_right.getAllDoor().get("west"), player, i, j+1);
								if(piece_right.getAllDoor().get("west").getDoor()){
									piece_east.getAllDoor().get("east").OpenTheDoor();
								}
								System.out.println("La porte a été ouverte avec succès.");
								player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
								System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
							} catch (DoorIsClose e) {
								System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
							}
						}
					} else {
						if (cell[i][j+1] instanceof Piece){
							Piece piece_nord = (Piece) cell[i][j+1];
							if (!piece_nord.getAllDoor().get("west").getDoor()) {
								try {
									handleDoorOpening(piece_nord.getAllDoor().get("west"), player, i, j+1);
									System.out.println("La porte a été ouverte avec succès.");
									player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
									System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
								} catch (DoorIsClose e) {
									System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
								}
							}
						}
					}
					break;
				case "ouest":
					if (cell[i][j] instanceof Piece && cell[i][j-1] instanceof Piece) {
						Piece piece_west = (Piece) cell[i][j];
						if (!piece_west.getAllDoor().get("west").getDoor()) {
							try {
								Piece piece_left = (Piece) cell[i][j-1];
								handleDoorOpening(piece_left.getAllDoor().get("east"), player, i, j-1);
								if(piece_left.getAllDoor().get("east").getDoor()){
									piece_west.getAllDoor().get("west").OpenTheDoor();
								}
								System.out.println("La porte a été ouverte avec succès.");
								player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
								System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
							} catch (DoorIsClose e) {
								System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
							}
						}
					} else {
						if (cell[i][j-1] instanceof Piece){
							Piece piece_left = (Piece) cell[i][j-1];
							if (!piece_left.getAllDoor().get("east").getDoor()) {
								try {
									handleDoorOpening(piece_left.getAllDoor().get("east"), player, i, j-1);
									System.out.println("La porte a été ouverte avec succès.");
									player.setActionpoint(player.getActionpoint() - 1); // Décrémente le nombre de points d'action du joueur
									System.out.println(player.getName()+ " a ecncore " + player.getActionpoint() + " point d'action ");
								} catch (DoorIsClose e) {
									System.out.println("Vous avez besoin d'une arme ou d'une clé pour ouvrir la porte.");
								}
							}
						}
					}
					break;
	
				default:
					System.out.println("Direction invalide.");
			}
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite : " + e.getMessage());
		}
	}

	//Regard si le joueur possede une arme ou une clé et ouvre la porte en faisant spawn des zombies a l'interieur 
	private void handleDoorOpening(Door door, Player player, int i, int j) throws DoorIsClose {
		if (player.getHand()[0] instanceof Weapon) {
			door.OpenTheDoor();
			Zombie zombie = new Zombie();
			this.board.getCell()[i][j].SetZombie(zombie);
			zombie.setx(i);
			zombie.sety(j);
			this.board.getZombie().add(zombie);
			this.MakeNoise(i, j, player.getHand()[0].getNoise(), player);
			System.out.println("La porte a été ouverte avec une arme.");
		} else if (player.getHand()[0] instanceof MasterKey) {
			door.OpenTheDoor();
			System.out.println("La porte a été ouverte avec une clé maîtresse.");
		} else {
			throw new DoorIsClose();
		}
	}
	
	/**  Make the player heals himself with an heal item
	 * @param player1 the player who use the heal
	 * @throws NotAHealException , cannot heal with an item different than an heal item
	 */
	public void heal(Player player) {
		try {
			if (!(player.getHand()[0] instanceof Heal)) {
				throw new NotAHealException();
			}
			
			if (player.getHand()[0] instanceof Flask) {
				player.setLife(player.getLife() + 1);
				player.setActionpoint(player.getActionpoint() - 1);
				System.out.println("Ce joueur a maintenant " + player.getLife() + " points de vie");
				System.out.println("Un joueur a utilisé " + player.getHand()[0].getId() + ", cet item n'est plus utilisable.");
				player.destroyHandItem();
			}
			
			if (player.getHand()[0] instanceof MedKit) {
				for (Player p : this.board.getCell()[player.getx()][player.gety()].getPlayer()) {
					if (p.getInlife()) {
						p.setLife(p.getLife() + 1);
						System.out.println("Ce joueur a maintenant " + p.getLife() + " points de vie");
						System.out.println("Un joueur a utilisé " + player.getHand()[0].getId() + ", cet item n'est plus utilisable.");
						player.destroyHandItem();
					}
				}
				player.setActionpoint(player.getActionpoint() - 1);
			}
		} catch (NotAHealException e) {
			System.out.println("Erreur : Le premier objet dans la main du joueur n'est pas un objet de type Heal.");
			// Autres actions à effectuer en cas d'exception NotAHealException, si nécessaire
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erreur : Tableau d'objets dans la main du joueur vide ou indice incorrect.");
			// Autres actions à effectuer en cas d'exception ArrayIndexOutOfBoundsException, si nécessaire
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite lors de l'exécution de la méthode heal : " + e.getMessage());
			// Autres actions à effectuer en cas d'autres exceptions génériques, si nécessaire
		}
	}

	/** for healer player, make them use their ability to heal an other player
	 * @param player1 the player who use the heal
	 * @param player2 the player who gonna be heal
	 */
	public void soigner(Healer player1, Player player2) throws NotAHealerException, PlayerAlreadyDeadException, HealerDontHaveThisSpell{
		try {
			// Vérifier si player1 est un Healer
			if (!(player1 instanceof Healer)) {
				throw new NotAHealerException();
			}
			
			// Vérifier si player1 a la capacité de soigner
			if (!player1.getSoigner()) {
				throw new HealerDontHaveThisSpell();
			}
			
			// Vérifier si player2 est en vie
			if (!player2.getInlife()) {
				throw new PlayerAlreadyDeadException();
			}
			
			// Effectuer l'action de soin en fonction du joueur cible
			if (player1.getName().equals(player2.getName())) {
				player1.setLife(player1.getLife() + 1);
			} else {
				player2.setLife(player2.getLife() + 1);
			}
			
			// Marquer player1 comme ayant utilisé sa capacité de soin
			player1.setSoigner(false);
			
			// Afficher les informations sur le soin effectué
			System.out.println("Ce joueur a maintenant " + player2.getLife() + " points de vie");
			System.out.println("Ce joueur a utilisé " + player1.getHand()[0].getId() + ", cet item n'est plus utilisable.");
			
			// Détruire l'objet utilisé de la main du joueur
			player1.destroyHandItem();
			
		} catch (NotAHealerException e) {
			System.out.println("Erreur : Le joueur 1 n'est pas un Healer.");
			// Autres actions à effectuer en cas d'exception NotAHealerException, si nécessaire
			
		} catch (HealerDontHaveThisSpell e) {
			System.out.println("Erreur : Le Healer n'a pas la capacité de soigner.");
			// Autres actions à effectuer en cas d'exception HealerDontHaveThisSpell, si nécessaire
			
		} catch (PlayerAlreadyDeadException e) {
			System.out.println("Erreur : Le joueur 2 est déjà mort.");
			// Autres actions à effectuer en cas d'exception PlayerAlreadyDeadException, si nécessaire
			
		} catch (Exception e) {
			System.out.println("Une erreur s'est produite lors de l'exécution de la méthode soigner : " + e.getMessage());
			// Autres actions à effectuer en cas d'autres exceptions génériques, si nécessaire
		}
	}

	public void ResetPoint(List<Player> player){
		for(int i =0; i<player.size(); i++){
			if(player.get(i).getLevel()<3){
				player.get(i).setActionpoint(3);
			}
			else if(player.get(i).getLevel()<7){
				player.get(i).setActionpoint(4);
			}
			else{
				player.get(i).setActionpoint(5);
			}
		}
	}


		/**
	 * take an item on the floor, if there is no item in the hand, the item go in the hand else in the inventory(stock)
	 * @param b the board where the Player and the Item are
	 * @param i index of the item on the floor
	 * @exception throw if the inventory of the player is full
	 */
	public void takeAnItem(Board b,Player player, Item i) throws InventoryFullException{
		Celulle c =b.getCell()[player.getx()][player.gety()];
		List<Item> l = c.getItem(); //liste des Item dans la cellule
		int x=0;
		int cmp=0;
		boolean end=false;
		if (player.getHand()[0]==null) { // on verifie si la main du joueur est vide, si oui on ajoute l'Item dans la main du joueur
			player.getHand()[0]=i; // ajout de l'Item dans la main
			l.remove(i); // aprés l'ajout de l'Item dans la main du joueur, on enleve l'Item de la Cellule
			System.out.println("Le joueur a prit un item");
		}
		else {
			while(player.getStock().length>x && !end){
				
				if (player.getStock()[x]!=null) {
					cmp++;
				}
				else {
					player.getStock()[x]=i;
					l.remove(i);
					end=true;
					System.out.println("Le joueur a prit un item");
				}
				x++;
			}
			if (cmp==4) {
				throw new InventoryFullException();
			}
		}
	}

	public void TakeAHand(Player player,int i){
		if (player.getHand()[0]==null){
			player.getHand()[0]=player.getStock()[i];
			System.out.println("Le joueur a prit un item en main ");
		}
		if (player.getHand()[0]!=null){
			Item cmp=player.getHand()[0];
			player.getHand()[0]=player.getStock()[i];
			player.getStock()[i]=cmp;
			System.out.println("Le joueur a prit un item en main ");
		}
	}

	public void display(Player player){
		if(player.getHand()[0] instanceof Maps){
			board.display();
		}
		else{
			System.out.println("Le joueur n'a pas map en main");
		}
	}

	public void lookAtTheStock(Player player){ 
		if (player.getStock()[0]==null && player.getStock()[1]==null && player.getStock()[2]==null && player.getStock()[3]==null){
			System.out.println("L'inventaire est vide.");
		}
		else{
			String text="";
			if(player.getStock()[0]!=null){
				text+=player.getStock()[0].getId()+" ";
			}
			if(player.getStock()[1]!=null){
				text=player.getStock()[1].getId()+" ";
			}
			if(player.getStock()[2]!=null){
				text+=player.getStock()[2].getId()+" ";
			}
			if(player.getStock()[3]!=null){
				text+=player.getStock()[3].getId()+" ";
			}
			System.out.println("Le jour a en stock : " + text);
		}
	}

}
	
