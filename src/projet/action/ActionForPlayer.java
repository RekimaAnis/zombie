package projet.action;

import projet.Board;
import projet.exception.*;
import projet.item.*;
import projet.player.Healer;
import projet.player.Player;
import projet.zombie.Zombie;

public interface ActionForPlayer {
	public void move(Player player,String direction);
	public void attack(Player player, Zombie zombie) throws NotAWeaponException, ZombieIsOutOfRangeException, PlayerDontHaveEnoughActionPointException;
	public void lookAroundYourself(int i, int j);
	public void heal(Player player) throws NotAHealException;
	//public void takeInHand();
	//public void useAItem();
	public void OpenADoor(int i, int j, String direction, Player player) throws DoorIsClose;
	public void MakeNoise(int x,int y,int noiseMaked, Player player);
	public void searchInRoom(int i, int j, Player player) throws CanNotSearchInStreet;
	public void soigner(Healer player1, Player player2) throws NotAHealerException, PlayerAlreadyDeadException, HealerDontHaveThisSpell;
	public void takeAnItem(Board b,Player player, Item I)throws InventoryFullException;
	public void TakeAHand(Player player,int i);
}