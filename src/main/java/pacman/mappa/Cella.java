package pacman.mappa;

import pacman.Costanti;
import pacman.render.BaseRenderable;

import java.awt.*;

public class Cella extends BaseRenderable<Cella> implements Costanti {

    public enum TipoCella {ANGOLO_NE, ANGOLO_NO, ANGOLO_SE, ANGOLO_SO, RIGA_VERT, RIGA_ORIZZ, VUOTA, MELA, MELONE, D_RIGA_ORIZZ, D_RIGA_VERT, D_ANGOLO_NE, D_ANGOLO_NO, D_ANGOLO_SE, D_ANGOLO_SO}

    private TipoCella tipoCella;

    public Cella(int x, int y, TipoCella tipoCella) {
        super(x, y, CELL_WIDTH, CELL_HEIGHT, Color.BLUE);
        this.tipoCella = tipoCella;
    }


    public TipoCella getTipoCella() {
        return tipoCella;
    }


    public boolean canBeUsed() {
        if (tipoCella == TipoCella.VUOTA || tipoCella == TipoCella.MELA || tipoCella == TipoCella.MELONE)
            return true;
        return false;
    }

    public void melaMangiata() {
        if (tipoCella == TipoCella.MELA || tipoCella == TipoCella.MELONE) {
            tipoCella = TipoCella.VUOTA;
        } else throw new UnsupportedOperationException("Mela non presente");
    }

    @Override
    public String toString() {
        return x + "-" + y + ": " + tipoCella;
    }
}
