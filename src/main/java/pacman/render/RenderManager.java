package pacman.render;


public interface RenderManager {
    void render();

    <T extends Renderable> Renderer<T> getRendererFor(Class<? extends BaseRenderable> type);

    void showDialog(String title, String message,
                    String yesButton, String noButton,
                    Runnable onYes, Runnable onNo);
}
