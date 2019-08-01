package pacman.entity;

import pacman.Costanti;
import pacman.Direction;
import pacman.render.Renderer;

import java.awt.*;


public abstract class BaseCreatura<T extends BaseCreatura> implements Entity, Costanti {
    protected Renderer<T> renderer;
    protected final int x,y;
    protected final int width, height;
    protected final Color color;
    protected Direction currentDir;

    public BaseCreatura() {
        this(0,0,CREATURA_WIDTH,CREATURA_HEIGHT,Color.BLACK);
    }

    public BaseCreatura(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onRender() {
        renderer.onRender((T) this);
    }

}
