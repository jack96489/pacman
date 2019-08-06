package pacman.mappa;

import pacman.Costanti;
import pacman.render.swing.BaseSwingRenderer;
import pacman.render.swing.SwingRenderManager;

import java.awt.*;

public class CellaRenderer extends BaseSwingRenderer<Cella> implements Costanti {

    public CellaRenderer(SwingRenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void onRender(Graphics2D graphics, Cella toRender) {
        final int _x=toRender.getX()*CELL_WIDTH+ X_BORDER,
                _y=toRender.getY()*CELL_HEIGHT+Y_BORDER;
        //graphics.setColor(Color.green);
        //graphics.drawRect(_x,_y,CELL_WIDTH,CELL_HEIGHT);
        //graphics.drawString(Integer.toString(toRender.getX())+"-"+Integer.toString(toRender.getY()),_x,_y+15);
        graphics.setColor(toRender.getColor());
        switch(toRender.getTipoCella()){
            case D_ANGOLO_NO:
                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_MARGIN,_y+CELL_HEIGHT);// verticale
                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_MARGIN);    //orizzontale
                //graphics.drawArc(_x,_y+CELL_MARGIN,CELL_WIDTH,CELL_HEIGHT,180,-90);

                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);   //verticale
                graphics.drawLine(_x+CELL_WIDTH,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);   //orizzontale
                break;

            case D_ANGOLO_NE:
                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT);// verticale
                graphics.drawLine(_x,_y+CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN);  //orizzontale

                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_MARGIN,_y+CELL_HEIGHT);    // verticale
                graphics.drawLine(_x,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN); //orizzontale
                break;

            case D_ANGOLO_SE:
                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);// verticale
                graphics.drawLine(_x,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);  //orizzontale

                graphics.drawLine(_x+CELL_MARGIN,_y,_x+CELL_MARGIN,_y+CELL_MARGIN);    // verticale
                graphics.drawLine(_x,_y+CELL_MARGIN,_x+CELL_MARGIN,_y+CELL_MARGIN); //orizzontale
                break;

            case D_ANGOLO_SO:
                graphics.drawLine(_x+CELL_MARGIN,_y,_x+CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN);// verticale
                graphics.drawLine(_x+CELL_MARGIN,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_HEIGHT-CELL_MARGIN);  //orizzontale

                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN);    // verticale
                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_MARGIN); //orizzontale
                break;

            case D_RIGA_VERT:
                graphics.drawLine(_x+CELL_MARGIN,_y,_x+CELL_MARGIN,_y+CELL_HEIGHT);
                graphics.drawLine(_x+CELL_WIDTH-CELL_MARGIN,_y,_x+CELL_WIDTH-CELL_MARGIN,_y+CELL_HEIGHT);
                break;

            case D_RIGA_ORIZZ:
                graphics.drawLine(_x,_y+CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_MARGIN);
                graphics.drawLine(_x,_y+CELL_HEIGHT-CELL_MARGIN,_x+CELL_WIDTH,_y+CELL_HEIGHT-CELL_MARGIN);
                break;

            case MELONE:
                int raggio=Math.min(CELL_HEIGHT, CELL_WIDTH)/2;
                graphics.fillOval(_x+(CELL_WIDTH-raggio)/2,_y+(CELL_HEIGHT-raggio)/2,raggio,raggio);
                break;

            case MELA:
                int width=CELL_WIDTH/5;
                int height=CELL_HEIGHT/5;
                graphics.fillRect(_x+(CELL_WIDTH-width)/2,_y+(CELL_HEIGHT-height)/2,width,height);
                break;

            case RIGA_VERT:
                graphics.drawLine(_x+CELL_WIDTH/2,_y,_x+CELL_WIDTH/2,_y+CELL_HEIGHT);
                break;

            case RIGA_ORIZZ:
                graphics.drawLine(_x,_y+CELL_HEIGHT/2,_x+CELL_WIDTH,_y+CELL_HEIGHT/2);
                break;

        }
    }
}
