package pacman.render;

import pacman.entity.BaseCreatura;

public interface RenderManager {
    void render();

    <T extends Renderable> Renderer<T> getRendererFor(Class<? extends BaseCreatura> type);
}
