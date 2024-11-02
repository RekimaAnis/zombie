package projet.action;

import projet.exception.*;
import projet.zombie.*;

public interface ActionForZombie {
	public void attack(Zombie zombie) throws ZombieDontHaveEnoughActionPointException,DoorIsClose;
	public void move(Zombie zombie)throws DoorIsClose;
	
}
