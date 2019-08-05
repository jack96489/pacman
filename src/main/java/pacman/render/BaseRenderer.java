package pacman.render;


public abstract class BaseRenderer<T extends BaseRenderable, R extends RenderManager> implements Renderer<T> {
    protected R renderManager;

    public BaseRenderer(R renderManager) {
        this.renderManager = renderManager;
    }

}
