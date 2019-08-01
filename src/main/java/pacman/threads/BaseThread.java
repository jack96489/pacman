package pacman.threads;

import pacman.Costanti;
import pacman.DatiCondivisi;
import pacman.entity.BaseCreatura;

public class BaseThread extends Thread implements Costanti {
    protected BaseCreatura creatura;
    protected DatiCondivisi dati;

    public BaseThread(BaseCreatura creatura, DatiCondivisi dati) {
        this.creatura = creatura;
        this.dati = dati;
    }

    @Override
    public void run() {
        try {
            creatura.onTick();
            sleep(TPS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
