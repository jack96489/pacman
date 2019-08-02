package pacman.entity;

import java.awt.*;

public class Fantasma extends BaseCreatura<Fantasma> {

    public enum StatoFantasma{ATTIVO, VULNERABILE}
    private StatoFantasma stato;
    public Fantasma() {
        super();
        stato=StatoFantasma.ATTIVO;
    }

    public Fantasma(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        stato=StatoFantasma.ATTIVO;
    }

    @Override
    public void onKeyPressed(int key) {

    }

    @Override
    public void onTick() {

    }
}
