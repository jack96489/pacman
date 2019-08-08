package pacman;

import pacman.entity.Fantasma;
import pacman.entity.Pacman;
import pacman.mappa.Mappa;
import pacman.render.RenderManager;
import pacman.render.swing.SwingRenderManager;
import pacman.threads.CreaturaThread;
import pacman.threads.RenderThread;

import java.awt.*;
import java.util.List;
import java.util.Vector;

public class PacmanGame {

    private static PacmanGame INSTANCE;
    private RenderManager renderManager;
    private Mappa gameMap;
    private List<Fantasma> fantasmi;
    private Pacman pacman;
    private int punti;


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
        renderManager.render();
        new CreaturaThread(pacman, this).start();
        for (Fantasma f : fantasmi)
            new CreaturaThread(f, this).start();
        new RenderThread(pacman, this).start();
        punti = 0;

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
        //TODO: disattiva fantasmi
    }

    public int getPunti() {
        return punti;
    }
}
