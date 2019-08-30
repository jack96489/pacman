package pacman.threads;

import pacman.PacmanGame;
import pacman.entity.BaseActor;

public class CreaturaThread extends BaseThread {
    public CreaturaThread(BaseActor creatura, PacmanGame dati) {
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
                    e.printStackTrace();
                }
                sleep(1000 / TPS);
                dati.getSemMuovi().release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
