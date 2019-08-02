package pacman;

import pacman.entity.Fantasma;
import pacman.entity.Pacman;
import pacman.render.RenderManager;
import pacman.render.SwingRenderManager;
import pacman.threads.BaseThread;

import java.util.List;
import java.util.Vector;

public class PacmanGame {

    private static PacmanGame INSTANCE;
    private RenderManager renderManager;
    private List<Fantasma> fantasmi;
    private Pacman pacman;


    private PacmanGame() {
    }

    public void setup(){
        renderManager=new SwingRenderManager();
        fantasmi=new Vector<>();
        for(int i =0;i<5;i++)
            fantasmi.add(new Fantasma());
        pacman=new Pacman();
        renderManager.render();
        new BaseThread(pacman,this).start();
    }

    public static PacmanGame getInstance() {
        if(INSTANCE==null)
            INSTANCE=new PacmanGame();
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
}
