package pacman;

public interface Costanti {

    int TPS = 60;
    int FPS = 144;

    int FRAME_WIDTH = 800, FRAME_HEIGHT = 650;
    int TABLE_WIDTH = 750, TABLE_HEIGHT = 570;
    int CREATURA_WIDTH = 20, CREATURA_HEIGHT = 20;
    int CELL_WIDTH=30,CELL_HEIGHT=30;
    int CELL_MARGIN=10;
    int NUM_CELL_X=25,NUM_CELL_Y=19;

    int MOVEMENT_SPEED = 4;
    int MOVEMENT_ERROR_MARGIN = 3;
    int MAX_STORED_MOVES = 4;

    int X_BORDER=(FRAME_WIDTH - TABLE_WIDTH) / 2;
    int Y_BORDER=(FRAME_HEIGHT - TABLE_HEIGHT) / 2;
}
