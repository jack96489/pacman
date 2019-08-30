package pacman.threads;

import pacman.PacmanGame;
import pacman.entity.BaseActor;

public class RenderThread extends BaseThread {
    public RenderThread(BaseActor creatura, PacmanGame dati) {
        super(creatura, dati);
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            try {
                dati.getRenderManager().render();
                sleep(1000/FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
