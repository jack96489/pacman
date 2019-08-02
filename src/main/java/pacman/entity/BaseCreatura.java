package pacman.entity;

import pacman.Costanti;
import pacman.Direction;
import pacman.PacmanGame;
import pacman.render.Renderer;

import java.awt.*;


public abstract class BaseCreatura<T extends BaseCreatura> implements Entity, Costanti {
    protected Renderer<T> renderer;
    protected int x,y;
    protected final int width, height;
    protected Color color;
    protected Direction currentDir;

    public BaseCreatura() {
        this(0,0,CREATURA_WIDTH,CREATURA_HEIGHT,Color.BLACK);
    }

    public BaseCreatura(int x, int y, int width, int height, Color color) {
        renderer = PacmanGame.getInstance().getRenderManager().getRendererFor(getClass());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        currentDir= Direction.RIGHT;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onRender(Graphics2D g2d) {
        System.out.println("creatura renderrrr");
        renderer.onRender(g2d,(T) this);
    }

    public Renderer<T> getRenderer() {
        return renderer;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getColor() {
        return color;
    }

    public Direction getCurrentDir() {
        return currentDir;
    }
}
