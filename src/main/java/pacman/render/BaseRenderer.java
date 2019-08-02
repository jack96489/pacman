package pacman.render;

import pacman.entity.BaseCreatura;

public abstract class BaseRenderer<T extends BaseCreatura, R extends RenderManager> implements Renderer<T> {
    protected R renderManager;

    public BaseRenderer(R renderManager) {
        this.renderManager = renderManager;
    }

}
