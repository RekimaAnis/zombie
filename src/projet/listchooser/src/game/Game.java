package projet.listchooser.src.game;


import projet.listchooser.src.listchooser.InteractiveListChooser;
import projet.listchooser.src.listchooser.ListChooser;
import projet.listchooser.src.listchooser.RandomListChooser;


import projet.listchooser.src.listchooser.*;
 
public class Game {

	public static ListChooser<PFC> lc;
	
	public static void main (String args[]) {
		if ((args.length == 1) && args[0].equals("i")) {
			Game.lc = new InteractiveListChooser<>();
		} else {
			Game.lc = new RandomListChooser<>();
		} 
		Player p1 = new Player();
		Player p2 = new Player();
		PFC v1 = p1.play();
		PFC v2 = p2.play();
		System.out.println(v1 + " vs. " + v2);
	}
}
