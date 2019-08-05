package pacman.render;


public interface RenderManager {
    void render();

    <T extends Renderable> Renderer<T> getRendererFor(Class<? extends BaseRenderable> type);
}
