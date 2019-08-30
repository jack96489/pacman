package pacman.render;

import pacman.Costanti;
import pacman.PacmanGame;

import java.awt.*;

public abstract class BaseRenderable<T extends BaseRenderable> implements Renderable, Costanti {
    private final Renderer<T> renderer;
    protected int x,y;
    protected final int width, height;
    protected Color color;

    public BaseRenderable(int x, int y, int width, int height, Color color) {
        renderer = PacmanGame.getInstance().getRenderManager().getRendererFor(getClass());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onRender(Graphics2D g2d) {
        renderer.onRender(g2d,(T) this);
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

    public int getMiddleX(){
        return x+width/2;
    }

    public int getMiddleY(){
        return y+height/2;
    }

    protected int getTableX(){
        return (int) Math.floor((getMiddleX() - (float)X_BORDER) / (float)CELL_WIDTH);
    }

    protected int getTableY(){
        return (int) Math.floor((getMiddleY() - (float)Y_BORDER) / (float)CELL_HEIGHT);
    }
}
