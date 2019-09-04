package pacman.mappa;

import pacman.Costanti;
import pacman.Direction;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Mappa implements Costanti {
    private final Cella[][] mappa;

    public Mappa() {
        //final Random rn = new Random();
        this.mappa = new Cella[NUM_RIGHE][NUM_COLONNE];

        try {
            loadMap(readMap());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(Arrays.toString(mappa[0]));
    }

    private List<String> readMap() throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("maps/map1.txt"))));
        List<String> ls = new ArrayList<>();
        String s;
        do {
            s = in.readLine();
            if (s != null)
                ls.add(s);
        } while (s != null);
        return ls;
    }

    private void loadMap(List<String> ls) {
        if (ls.size() != NUM_RIGHE)
            throw new UnsupportedOperationException("Numero righe mappa errato");
        for (int r = 0; r < NUM_RIGHE; r++) {
            if (ls.get(0).length() != NUM_COLONNE)
                throw new UnsupportedOperationException("Numero colonne mappa errato");
            for (int c = 0; c < NUM_COLONNE; c++) {
                mappa[r][c] = new Cella(c, r, charToTipoCella(ls.get(r).charAt(c)) /*Cella.TipoCella.values()[rn.nextInt(7)]*/);
            }
        }

    }

    private Cella.TipoCella charToTipoCella(char c) {
        switch (c){
            case 'd':
                return Cella.TipoCella.D_RIGA_ORIZZ;
            case 'b':
                return Cella.TipoCella.D_RIGA_VERT;
            case 'a':
                return Cella.TipoCella.D_ANGOLO_NO;
            case 'l':
                return Cella.TipoCella.D_ANGOLO_NE;
            case 'f':
                return Cella.TipoCella.D_ANGOLO_SE;
            case 's':
                return Cella.TipoCella.D_ANGOLO_SO;
            case 'm':
                return Cella.TipoCella.MELA;
            case 'v':
                return Cella.TipoCella.RIGA_VERT;
            case 'o':
                return Cella.TipoCella.RIGA_ORIZZ;
            case 'p':
                return Cella.TipoCella.ANGOLO_NE;
            case 'q':
                return Cella.TipoCella.ANGOLO_NO;
            case 'w':
                return Cella.TipoCella.ANGOLO_SO;
            case 'i':
                return Cella.TipoCella.ANGOLO_SE;
            case 'M':
                return Cella.TipoCella.MELONE;
            case 'e':
                return Cella.TipoCella.VUOTA;

        }
        return Cella.TipoCella.MELA;
    }

    public void render(Graphics2D graphics) {
        for (Cella[] cellas : mappa) {
            //System.out.println(Arrays.toString(cellas));
            for (Cella cella : cellas) {
                cella.onRender(graphics);
            }
        }
    }

    public Cella getCella(int x, int y) {
        return mappa[y][x];
    }

    public Cella getCellaAdiacente(int x, int y, Direction dir) {
        switch (dir) {
            case RIGHT:
                x++;
                break;
            case LEFT:
                x--;
                break;
            case DOWN:
                y++;
                break;
            case UP:
                y--;
                break;
        }
        return mappa[y][x];
    }


}
