package pacman.render.swing;

import pacman.entity.Fantasma;

import java.awt.*;

public class FantasmaRenderer extends BaseSwingRenderer<Fantasma> {

    public FantasmaRenderer(SwingRenderManager renderManager) {
        super(renderManager);

    }

    @Override
    public void onRender(Graphics2D g2d, Fantasma toRender) {
        if (toRender.isAppenaMorto()) {
            if (renderManager.getFramesRendered() / (FPS / 5) % 2 == 0){
                g2d.drawImage(toRender.getImage(), toRender.getX(), toRender.getY(), toRender.getWidth(), toRender.getHeight(), null);
            }
        } else
            g2d.drawImage(toRender.getImage(), toRender.getX(), toRender.getY(), toRender.getWidth(), toRender.getHeight(), null);
    }

}
