package pacman.render.swing;


import pacman.Costanti;
import pacman.render.BaseRenderable;
import pacman.render.BaseRenderer;
import pacman.render.Renderer;

public abstract class BaseSwingRenderer<T extends BaseRenderable> extends BaseRenderer<T,SwingRenderManager> implements Renderer<T>, Costanti {

    public BaseSwingRenderer(SwingRenderManager renderManager) {
       super(renderManager);
    }
}
