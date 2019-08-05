package pacman.mappa;

import pacman.Costanti;
import pacman.render.BaseSwingRenderer;
import pacman.render.SwingRenderManager;

import java.awt.*;

public class CellaRenderer extends BaseSwingRenderer<Cella> implements Costanti {

    public CellaRenderer(SwingRenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void onRender(Graphics2D graphics, Cella toRender) {
        final int _x=toRender.getX()*CELL_WIDTH+ X_BORDER,
                _y=toRender.getY()*CELL_HEIGHT+Y_BORDER;
        switch(toRender.getTipoCella()){
            case ANGOLO_NE:
                graphics.setColor(Color.BLUE);
                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_MARGIN,_y+CELL_HEIGHT);// verticale
                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_MARGIN);    //orizzontale

                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);   //verticale
                graphics.drawLine(_x+CELL_WIDTH,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);   //orizzontale
                break;

            case ANGOLO_NO:
                graphics.setColor(Color.BLUE);
                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT);// verticale
                graphics.drawLine(_x,_y+CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN);  //orizzontale

                //graphics.drawLine(_x,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);    // verticale
                //graphics.drawLine(_x+CELL_WIDTH,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN); //orizzontale
                break;
        }
    }
}
