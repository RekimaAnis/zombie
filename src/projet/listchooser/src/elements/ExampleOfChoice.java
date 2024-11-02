package projet.listchooser.src.elements;
import java.util.ArrayList;
import java.util.List;

import projet.listchooser.src.listchooser.InteractiveListChooser;
import projet.listchooser.src.listchooser.ListChooser;
import projet.listchooser.src.listchooser.RandomListChooser;

import java.util.*;
import projet.listchooser.src.listchooser.*;

public class ExampleOfChoice {

	
	public static void runTest (ListChooser<Elements> lc) {
		{
			List<Elements> l = new ArrayList<>();
			l.add(new A());
			l.add(new B());
			l.add(new D());
			Elements chosenElement = lc.choose("Which element do you choose ?", l);
			System.out.println("You've chosen : " + chosenElement);
			if (chosenElement != null) chosenElement.doSomething();
		}
		
		{
			List<Elements> l = new ArrayList<>();
			l.add(new A());
			l.add(new D());
			l.add(new C());
			Elements chosenElement = lc.choose("Which element do you choose ?", l);
			System.out.println("You've chosen : " + chosenElement);
			if (chosenElement != null) chosenElement.doSomething();
		}
	}
	
	public static void main (String args[]) {
		InteractiveListChooser<Elements> ilc = new InteractiveListChooser<>();
		RandomListChooser<Elements> rlc = new RandomListChooser<>();
		// first test to make random choices
		ExampleOfChoice.runTest(rlc);
		// second test to make interactive choices
		ExampleOfChoice.runTest(ilc);
	}
}
