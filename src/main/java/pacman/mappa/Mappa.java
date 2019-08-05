package pacman.mappa;

import pacman.Costanti;

import java.awt.*;
import java.util.Random;

public class Mappa implements Costanti {
    private final Cella[][] mappa;

    public Mappa() {
        final Random rn=new Random();
        this.mappa = new Cella[NUM_CELL_X][NUM_CELL_Y];
        for (int x = 0; x < NUM_CELL_X; x++) {
            for (int y = 0; y < NUM_CELL_Y; y++) {
                mappa[x][y]=new Cella(x,y, Cella.TipoCella.values()[rn.nextInt(7)]);
            }
        }
    }

    public void render(Graphics2D graphics) {
        for (Cella[] cellas : mappa) {
            for (Cella cella : cellas) {
                System.out.println(cella);
                cella.onRender(graphics);
            }
        }
    }

    public Cella getCella(int x,int y) {
        return mappa[x][y];
    }
}