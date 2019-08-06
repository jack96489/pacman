package pacman.threads;

import pacman.Costanti;
import pacman.PacmanGame;
import pacman.entity.BaseCreatura;

public abstract class BaseThread extends Thread implements Costanti {
    protected BaseCreatura creatura;
    protected PacmanGame dati;

    public BaseThread(BaseCreatura creatura, PacmanGame dati) {
        this.creatura = creatura;
        this.dati = dati;
    }

}
