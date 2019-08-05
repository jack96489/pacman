package pacman.render;



public abstract class BaseSwingRenderer<T extends BaseRenderable> extends BaseRenderer<T,SwingRenderManager> implements Renderer<T> {

    public BaseSwingRenderer(SwingRenderManager renderManager) {
       super(renderManager);
    }
}
