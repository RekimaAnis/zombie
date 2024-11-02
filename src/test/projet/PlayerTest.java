package test.projet;

import projet.player.*;
import static java.sql.DriverManager.println;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import projet.item.Item;


import projet.player.*;


import projet.item.Item;


import projet.item.heal.Flask;
import projet.item.*;
class PlayerTest {


	@Test
	void testGetName() {
		Player player = new Player("Jean-Luc");
		assertEquals(player.getName(),"Jean-Luc");
	}


	@Test
	void testTakeAHand() {
		Player player = new Player("Jean-Luc");

	}
	@Test
	void testTakeAnItem() {
		Player player = new Player("Jean-Luc");
		Flask flask = new Flask() ;
		//player.takeAnItem(flask);
		//assertEquals("test flask",player.getHand());

	}



}
