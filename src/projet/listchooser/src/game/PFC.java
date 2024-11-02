package projet.listchooser.src.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum PFC {

	Pierre, Feuille, Ciseaux;
	
	public static List<PFC> valuesAsList () {
		return new ArrayList<PFC>(Arrays.asList(PFC.values()));
	}
}
