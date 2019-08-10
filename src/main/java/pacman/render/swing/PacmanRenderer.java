package pacman.render.swing;

import pacman.entity.Pacman;

import java.awt.*;


public class PacmanRenderer extends BaseSwingRenderer<Pacman> {
    public PacmanRenderer(SwingRenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void onRender(Graphics2D g2d, Pacman toRender) {
        super.onRender(g2d, toRender);
        //System.out.println("pacman render");
        //System.out.println(toRender);
        //System.out.println(g2d);
        g2d.setColor(toRender.getColor());
        int startAngle = 0;
        switch (toRender.getCurrentDir()) {
            case UP:
                startAngle = 120;
                break;
            case DOWN:
                startAngle = 300;
                break;
            case LEFT:
                startAngle = 210;
                break;
            case RIGHT:
                startAngle = 30;
                break;
        }
        if (toRender.isAppenaMorto()) {
            if (framesRendered / (FPS / 5) % 2 == 0)
                g2d.fillArc(toRender.getX(), toRender.getY(), toRender.getWidth(), toRender.getHeight(), startAngle, 300);
        } else
            g2d.fillArc(toRender.getX(), toRender.getY(), toRender.getWidth(), toRender.getHeight(), startAngle, 300);

    }
}
