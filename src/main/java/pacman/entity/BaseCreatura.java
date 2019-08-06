package pacman.entity;

import pacman.Costanti;
import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;
import pacman.render.BaseRenderable;
import pacman.render.Renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseCreatura<T extends BaseCreatura> extends BaseRenderable<T> implements Entity, Costanti {
    protected Direction currentDir;

    public BaseCreatura() {
        this(0,0,CREATURA_WIDTH,CREATURA_HEIGHT, Color.BLACK);
    }

    public BaseCreatura(int x, int y, int width, int height, Color color) {
        super(x,y,width,height,color);
        currentDir= Direction.RIGHT;
    }

    public List<Direction> possibleNewDirections(){
        List<Direction> possibleDirs=new ArrayList<>();

        if(PacmanGame.getInstance().getGameMap().getCella(x+1,y).canBeUsed())
            possibleDirs.add(Direction.RIGHT);
        if(PacmanGame.getInstance().getGameMap().getCella(x-1,y).canBeUsed())
            possibleDirs.add(Direction.LEFT);
        if(PacmanGame.getInstance().getGameMap().getCella(x,y+1).canBeUsed())
            possibleDirs.add(Direction.DOWN);
        if(PacmanGame.getInstance().getGameMap().getCella(x,y-1).canBeUsed())
            possibleDirs.add(Direction.UP);

        possibleDirs.remove(currentDir);

        return possibleDirs;
    }


    public Renderer<T> getRenderer() {
        return renderer;
    }


    public Direction getCurrentDir() {
        return currentDir;
    }

    public Cella getCella(){
        int x=(this.x-X_BORDER)/TABLE_WIDTH;
        int y=(this.y-Y_BORDER)/TABLE_HEIGHT;

        return PacmanGame.getInstance().getGameMap().getCella(x,y);
    }
}
