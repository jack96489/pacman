package pacman;

public interface Costanti {

    float scale = 2f;

    int TPS = 60;
    int FPS = 144;

    int FRAME_WIDTH = (int) (450 * scale), FRAME_HEIGHT = (int) (500 * scale);
    int TABLE_WIDTH = (int) (420 * scale), TABLE_HEIGHT = (int) (465 * scale);
    int CREATURA_WIDTH = (int) (12 * scale), CREATURA_HEIGHT = (int) (12 * scale);
    int CELL_WIDTH = (int) (15 * scale), CELL_HEIGHT = (int) (15 * scale);
    int CELL_MARGIN = (int) (5 * scale);
    int NUM_COLONNE = 28, NUM_RIGHE = 31;

    int MOVEMENT_SPEED = (int) Math.ceil(scale);
    int MOVEMENT_ERROR_MARGIN = (int) (scale + 1);
    int MAX_STORED_MOVES = 4;

    int X_BORDER = (FRAME_WIDTH - TABLE_WIDTH) / 2;
    int Y_BORDER = (FRAME_HEIGHT - TABLE_HEIGHT) / 2;

    int NUM_FANTASMI = 4;
    int NUM_VITE = 3;

    int BLINK_TIME = 5000;
    int PAUSE_TIME = 3000;
}
