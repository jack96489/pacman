package pacman.threads;

import pacman.PacmanGame;
import pacman.entity.BaseCreatura;
import pacman.entity.Fantasma;
import pacman.entity.Pacman;

public class chkMangiatoThread extends BaseThread {
    public chkMangiatoThread(BaseCreatura creatura, PacmanGame dati) {
        super(creatura, dati);
        if (!(creatura instanceof Pacman))
            throw new IllegalArgumentException("creature must be a Pacman!");
    }

    @Override
    public void run() {

        while (!isInterrupted()) {
                for (Fantasma f : dati.getFantasmi()) {
                    if (Math.abs(f.getX() - creatura.getX()) < MOVEMENT_ERROR_MARGIN && Math.abs(f.getY() - creatura.getY()) < MOVEMENT_ERROR_MARGIN) {
                        System.out.println("MANGIATO!!");
                        if (f.isVulnerable())
                            System.out.println("fantasma morto");
                        else{
                            dati.gameOver();
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        }

    }
}
