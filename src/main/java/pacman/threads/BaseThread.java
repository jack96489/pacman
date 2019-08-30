package pacman.threads;

import pacman.Costanti;
import pacman.PacmanGame;
import pacman.entity.BaseActor;

public abstract class BaseThread extends Thread implements Costanti {
    protected BaseActor creatura;
    protected PacmanGame dati;

    public BaseThread(BaseActor creatura, PacmanGame dati) {
        this.creatura = creatura;
        this.dati = dati;
    }

}
