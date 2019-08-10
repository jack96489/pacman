package pacman.render;


import java.awt.*;

public abstract class BaseRenderer<T extends BaseRenderable, R extends RenderManager> implements Renderer<T> {
    protected R renderManager;
    protected long framesRendered;

    public BaseRenderer(R renderManager) {
        this.renderManager = renderManager;
    }

    @Override
    public void onRender(Graphics2D graphics, T toRender) {
        framesRendered++;
    }
}
