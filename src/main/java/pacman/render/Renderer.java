package pacman.render;

import java.awt.*;

public interface Renderer<T extends Renderable> {
    void onRender(Graphics2D graphics, T toRender);
}
