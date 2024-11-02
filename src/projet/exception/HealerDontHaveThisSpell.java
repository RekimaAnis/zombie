package projet.exception;

/**
 * HealerDontHaveThisSpell class
 */
public class HealerDontHaveThisSpell extends Exception{
	public HealerDontHaveThisSpell() {
		super("The healer have already use this spell");
	}
}