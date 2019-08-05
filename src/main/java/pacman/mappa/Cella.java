package pacman.mappa;

import pacman.Costanti;
import pacman.render.BaseRenderable;

import java.awt.*;

public class Cella extends BaseRenderable<Cella> implements Costanti {

    public enum TipoCella {ANGOLO_NE, ANGOLO_NO, ANGOLO_SE, ANGOLO_SO, RIGA_VERT, RIGA_ORIZZ, VUOTA}

    private TipoCella tipoCella;

    public Cella(int x, int y, TipoCella tipoCella) {
        super(x, y, CELL_WIDTH, CELL_HEIGHT, Color.BLUE);
        this.tipoCella = tipoCella;
    }


    public TipoCella getTipoCella() {
        return tipoCella;
    }

    public boolean canBeUsed() {
        if (tipoCella == TipoCella.VUOTA)
            return true;
        return false;
    }
}
