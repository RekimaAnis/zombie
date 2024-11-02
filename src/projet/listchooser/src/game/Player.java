package projet.listchooser.src.game;

public class Player {

	
	public PFC play() {
		
		return Game.lc.choose("Choisissez parmi", PFC.valuesAsList());
	}
}
