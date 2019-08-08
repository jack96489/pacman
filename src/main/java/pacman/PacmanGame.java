package pacman;

import pacman.entity.Fantasma;
import pacman.entity.Pacman;
import pacman.mappa.Mappa;
import pacman.render.RenderManager;
import pacman.render.swing.SwingRenderManager;
import pacman.threads.BaseThread;
import pacman.threads.CreaturaThread;
import pacman.threads.RenderThread;
import pacman.threads.chkMangiatoThread;

import java.awt.*;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;

public class PacmanGame implements Costanti{

    private static PacmanGame INSTANCE;
    private RenderManager renderManager;
    private Mappa gameMap;
    private List<Fantasma> fantasmi;
    private Pacman pacman;
    private int punti;
    private int vite;
    private BaseThread[] threads;


    private PacmanGame() {
    }

    public void setup() {
        renderManager = new SwingRenderManager();
        gameMap = new Mappa();
        fantasmi = new Vector<>();
        fantasmi.add(new Fantasma(Color.CYAN));
        fantasmi.add(new Fantasma(Color.RED));
        fantasmi.add(new Fantasma(Color.orange));
        fantasmi.add(new Fantasma(Color.pink));
        pacman = new Pacman();
        threads=new BaseThread[fantasmi.size()+2];
        renderManager.render();
        for (int i = 0; i < fantasmi.size(); i++) {
            threads[i] = new CreaturaThread(fantasmi.get(i), this);
        }
        threads[fantasmi.size()] = new CreaturaThread(pacman, this);
        threads[fantasmi.size()+1] = new chkMangiatoThread(pacman, this);
        for (Thread t : threads)
            t.start();
        new RenderThread(pacman, this).start();
        punti = 0;
        vite=3;
    }

    public static PacmanGame getInstance() {
        if (INSTANCE == null)
            INSTANCE = new PacmanGame();
        return INSTANCE;
    }

    public List<Fantasma> getFantasmi() {
        return fantasmi;
    }

    public Pacman getPacman() {
        return pacman;
    }

    public RenderManager getRenderManager() {
        return renderManager;
    }

    public Mappa getGameMap() {
        return gameMap;
    }

    public void melaMangiata() {
        punti++;
    }

    public void melonaMangiato() {
        punti += 5;
        for (Fantasma f : fantasmi)
            f.makeVulnerable();
    }

    public int getPunti() {
        return punti;
    }

    public int getVite() {
        return vite;
    }

    public void gameOver(){
        System.out.println("MORTO");
        vite--;
        if(vite<=0){
            for (Thread t : threads)
                t.interrupt();
            renderManager.showDialog("Game over");
        }
    }
}
