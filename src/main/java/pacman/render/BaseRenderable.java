package pacman.render;

import pacman.PacmanGame;

import java.awt.*;

public abstract class BaseRenderable<T extends BaseRenderable> implements Renderable{
    protected final Renderer<T> renderer;
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
}
