package pacman.mappa;

import com.sun.org.apache.xpath.internal.objects.XBoolean;
import pacman.Costanti;
import pacman.render.Renderable;

import java.awt.*;

public class Cella implements Renderable, Costanti {

    private final int x,y;
    private final int _x,_y;
    public enum TipoCella{ANGOLO_NE,ANGOLO_NO,ANGOLO_SE,ANGOLO_SO,RIGA_VERT,RIGA_ORIZZ,VUOTA}
    private TipoCella tipoCella;

    public Cella(int x, int y, TipoCella tipoCella) {
        this.x = x;
        this.y = y;
        this.tipoCella = tipoCella;
        _x=x*CELL_WIDTH+ X_BORDER;
        _y=y*CELL_HEIGHT+Y_BORDER;
    }

    @Override
    public void onRender(Graphics2D graphics) {
        switch(tipoCella){
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
