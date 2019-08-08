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

    private static final Cella.TipoCella[][] mappa1 = new Cella.TipoCella[NUM_RIGHE][NUM_COLONNE];

    static {
        mappa1[0] = new Cella.TipoCella[]{Cella.TipoCella.D_ANGOLO_NO, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_ANGOLO_NE, Cella.TipoCella.D_ANGOLO_NO, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_RIGA_ORIZZ, Cella.TipoCella.D_ANGOLO_NE};
        mappa1[1] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[2] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_NO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_NE, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_NO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_NE, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_NO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_NE, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_NO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_NE, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[3] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELONE, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.VUOTA, Cella.TipoCella.VUOTA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELONE, Cella.TipoCella.RIGA_VERT};
        mappa1[4] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_SO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_SE, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_SO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_SE, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_SO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_SE, Cella.TipoCella.MELA, Cella.TipoCella.ANGOLO_SO, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.RIGA_ORIZZ, Cella.TipoCella.ANGOLO_SE, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[5] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[6] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[7] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[8] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[9] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[10] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[11] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[12] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[13] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[14] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[15] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[16] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[17] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[18] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[19] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[20] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[21] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[22] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[23] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[24] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[25] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[26] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[27] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[28] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[29] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};
        mappa1[30] = new Cella.TipoCella[]{Cella.TipoCella.RIGA_VERT, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.MELA, Cella.TipoCella.RIGA_VERT};

    }

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
