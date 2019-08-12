package pacman;

import pacman.entity.CreatureManager;
import pacman.entity.Fantasma;
import pacman.mappa.Mappa;
import pacman.render.RenderManager;
import pacman.render.swing.SwingRenderManager;
import pacman.threads.BaseThread;
import pacman.threads.CreaturaThread;
import pacman.threads.RenderThread;
import pacman.threads.chkMangiatoThread;

import java.util.concurrent.Semaphore;

public class PacmanGame implements Costanti {

    private static PacmanGame INSTANCE;
    private RenderManager renderManager;
    private Mappa gameMap;
    private CreatureManager creature;
    private int punti;
    private int vite;
    private BaseThread[] threads;
    private Semaphore semMuovi, semControlla;


    private PacmanGame() {
    }

    public void setup() {
        renderManager = new SwingRenderManager();
        reset();
    }

    public static PacmanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PacmanGame();
        return INSTANCE;
    }


    public RenderManager getRenderManager() {
        return renderManager;
    }

    public Mappa getGameMap() {
        return gameMap;
    }

    public synchronized void melaMangiata() {
        punti += 10;
    }

    public synchronized void melonaMangiato() {
        punti += 50;
        creature.rendiFantasmiVulnerabili();
    }

    public int getPunti() {
        return punti;
    }

    public int getVite() {
        return vite;
    }

    public Semaphore getSemMuovi() {
        return semMuovi;
    }

    public Semaphore getSemControlla() {
        return semControlla;
    }


    public synchronized void gameOver() {
        System.out.println("MORTO");
        vite--;
        if (vite <= 0) {
            for (Thread t : threads) {
                t.interrupt();
            }
            renderManager.showDialog("Game over", "Hai perso! Vuoi ricominciare?", "Si", "No", this::reset, () -> System.exit(0));
        }
    }

    private void reset() {
        semMuovi = new Semaphore(0);
        semControlla = new Semaphore(NUM_FANTASMI + 1);
        gameMap = new Mappa();
        creature = new CreatureManager();
        threads = new BaseThread[NUM_FANTASMI + 2];
        renderManager.render();
        for (int i = 0; i < NUM_FANTASMI; i++) {
            threads[i] = new CreaturaThread(creature.getFantasma(i), this);
        }
        threads[NUM_FANTASMI] = new CreaturaThread(creature.getPacman(), this);
        threads[NUM_FANTASMI + 1] = new chkMangiatoThread(creature.getPacman(), this);
        for (Thread t : threads)
            t.start();
        new RenderThread(creature.getPacman(), this).start();
        punti = 0;
        vite = NUM_VITE;
    }

    public CreatureManager getCreature() {
        return creature;
    }
}
