package pacman.threads;

import pacman.PacmanGame;
import pacman.entity.BaseCreatura;

import java.util.concurrent.Semaphore;

public class CreaturaThread extends BaseThread {
    public CreaturaThread(BaseCreatura creatura, PacmanGame dati) {
        super(creatura, dati);
        setName("Thread creatura" + creatura.getClass());
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                dati.getSemControlla().acquire();
                try {
                    creatura.onTick();
                } catch (ArrayIndexOutOfBoundsException e) {
                    //ignored.... qualcosa è uscito dalla mappa
                    //TODO
                }
                sleep(1000 / TPS);
                dati.getSemMuovi().release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
