package pacman.threads;

import pacman.Costanti;
import pacman.PacmanGame;
import pacman.entity.BaseCreatura;

public class BaseThread extends Thread implements Costanti {
    protected BaseCreatura creatura;
    protected PacmanGame dati;

    public BaseThread(BaseCreatura creatura, PacmanGame dati) {
        this.creatura = creatura;
        this.dati = dati;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                creatura.onTick();
                dati.getRenderManager().render();
                sleep(TPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
