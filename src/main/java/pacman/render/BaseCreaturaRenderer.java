package pacman.render;

import pacman.entity.BaseCreatura;

import java.awt.*;

public abstract class BaseCreaturaRenderer<T extends BaseCreatura> extends BaseRenderer<T,SwingRenderManager> implements Renderer<T> {

    public BaseCreaturaRenderer(SwingRenderManager renderManager) {
       super(renderManager);
    }
}
