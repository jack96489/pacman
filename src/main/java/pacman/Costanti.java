package pacman;

public interface Costanti {

    int scale = 2;

    int TPS = 60;
    int FPS = 144;

    int FRAME_WIDTH = 450 * scale, FRAME_HEIGHT = 500 * scale;
    int TABLE_WIDTH = 420 * scale, TABLE_HEIGHT = 465 * scale;
    int CREATURA_WIDTH = 12 * scale, CREATURA_HEIGHT = 12 * scale;
    int CELL_WIDTH = 15 * scale, CELL_HEIGHT = 15 * scale;
    int CELL_MARGIN = 5 * scale;
    int NUM_COLONNE = 28, NUM_RIGHE = 31;

    int MOVEMENT_SPEED = scale;
    int MOVEMENT_ERROR_MARGIN = MOVEMENT_SPEED;
    int MAX_STORED_MOVES = 4;

    int X_BORDER = (FRAME_WIDTH - TABLE_WIDTH) / 2;
    int Y_BORDER = (FRAME_HEIGHT - TABLE_HEIGHT) / 2;

    int NUM_FANTASMI = 4;
    int NUM_VITE = 3;
}
