package test.projet;

import static java.sql.DriverManager.println;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import projet.Board;
import projet.player.*;
import projet.zombie.*;
import projet.action.*;
import projet.celulle.*;
import projet.exception.*;
import projet.item.weapon.Axe;

class ActionPlayerTest {

	@Test
	void makenoisetest() {
		Board board = new Board(5,5);
		Celulle[][] cell = board.getCell();
		ActionPlayer action= new ActionPlayer(board);
		Player player = new Player("player");
		action.MakeNoise(1, 1, 3, player);
		//bruit dans la cell
		assertEquals(cell[1][1].getNoise(),3);
		//propagation vers la droite
		assertEquals(cell[1][2].getNoise(),2);
		assertEquals(cell[1][3].getNoise(),1);//diminution de bruit
		assertEquals(cell[1][4].getNoise(),0);
		//propagation vers la gauche
		assertEquals(cell[1][0].getNoise(),2);
		//propagration vers le haut
		assertEquals(cell[0][1].getNoise(),2);
		//propagation vers le bas 
		assertEquals(cell[2][1].getNoise(),2);
		assertEquals(cell[3][1].getNoise(),1);
		assertEquals(cell[4][1].getNoise(),0);
	}

	@Test
	void movetest() throws DoorIsClose {
		Board board = new Board(5,5);
		Celulle[][] cell = board.getCell();
		ActionPlayer action= new ActionPlayer(board);
		List<Player> expectedPlayerList = new ArrayList<>();
		Player player = new Player("Jean-Luc");
		expectedPlayerList.add(player);
		player.setx(2);
		player.sety(2);
		//Vérifie si Jean-Luc est bien dans sa case initiale
		cell[player.getx()][player.gety()].setPlayer(player);
		assertEquals(cell[2][2].getPlayer(),expectedPlayerList);
		//On Fait bouger Jean-Luc vers la droite avec move
		action.move1(player, "est");
		assertEquals(cell[2][3].getPlayer(),expectedPlayerList);
		expectedPlayerList.remove(player);
		assertEquals(cell[2][2].getPlayer(),expectedPlayerList);
//		assertThrows(action.move1(player, "nord"));
	}
	
/** 	@Test 
	void attackTest() throws InventoryFullException, NotAWeaponException,ZombieIsOutOfRangeException,PlayerDontHaveEnoughActionPointException{
		Board board = new Board(5,5);
		Celulle [][] cell = board.getCell();
		ActionPlayer action= new ActionPlayer(board);
		Player player = new Player("Jean-Luc");
		Walkers zombie = new Walkers();
		Axe axe = new Axe();
		cell[1][2].SetItem(axe);
		player.setx(1);
		player.sety(2);
		zombie.setx(1);
		zombie.sety(2);
		cell[player.getx()][player.gety()].setPlayer(player);
		cell[zombie.getx()][zombie.gety()].SetZombie(zombie);
		player.takeAnItem2(cell[1][2],0);
		println("Points de vie du zombie :"+ zombie.getLife());
		println("Player attack le zombie avec une hache:");
		action.attack(player, zombie);
		if (zombie.getLife()== 1 ){
			println("le zombie a survecu au lancée de dés");
			assertEquals(zombie.getInLife(), true);
		}
		if (zombie.getLife()== -1){
			println("le zombie n'a pas survecu au lancée de dés");
			assertEquals(zombie.getInLife(),false);
		}

	}
	**/
}