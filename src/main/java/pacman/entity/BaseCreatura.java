package pacman.entity;

import pacman.Costanti;
import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;
import pacman.render.BaseRenderable;
import pacman.render.Renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


public abstract class BaseCreatura<T extends BaseCreatura> extends BaseRenderable<T> implements Entity, Costanti {
    protected static final PacmanGame game = PacmanGame.getInstance();
    protected Direction currentDir;
    private boolean appenaMorto = false;

    public BaseCreatura() {
        this(X_BORDER + NUM_COLONNE / 2 * CELL_WIDTH + (CELL_WIDTH - CREATURA_WIDTH) / 2, Y_BORDER + NUM_RIGHE / 2 * CELL_HEIGHT + (CELL_HEIGHT - CREATURA_HEIGHT) / 2, CREATURA_WIDTH, CREATURA_HEIGHT, Color.BLACK);
    }

    public BaseCreatura(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        currentDir = Direction.RIGHT;
    }

    public List<Direction> possibleNewDirections() {
        List<Direction> possibleDirs = new ArrayList<>();

        if (PacmanGame.getInstance().getGameMap().getCella(x + 1, y).canBeUsed())
            possibleDirs.add(Direction.RIGHT);
        if (PacmanGame.getInstance().getGameMap().getCella(x - 1, y).canBeUsed())
            possibleDirs.add(Direction.LEFT);
        if (PacmanGame.getInstance().getGameMap().getCella(x, y + 1).canBeUsed())
            possibleDirs.add(Direction.DOWN);
        if (PacmanGame.getInstance().getGameMap().getCella(x, y - 1).canBeUsed())
            possibleDirs.add(Direction.UP);

        possibleDirs.remove(currentDir);

        return possibleDirs;
    }

    protected boolean checkMovement() {
//        System.out.println(TABLE_WIDTH + X_BORDER - (x + width));
        if (x - X_BORDER < MOVEMENT_SPEED + MOVEMENT_ERROR_MARGIN && currentDir == Direction.LEFT) {
            x += TABLE_WIDTH - width;
            return true;
        } else if (TABLE_WIDTH + X_BORDER - (x + width) < MOVEMENT_SPEED + MOVEMENT_ERROR_MARGIN && currentDir == Direction.RIGHT) {
            x -= TABLE_WIDTH - width;
            return true;
        } else {
            int _x = x, _y = y;
            switch (currentDir) {
                case UP:
                    _y -= CELL_HEIGHT / 2 + MOVEMENT_ERROR_MARGIN / 2;
                    break;
                case DOWN:
                    _y += CELL_HEIGHT / 2 + MOVEMENT_ERROR_MARGIN / 2;
                    break;
                case RIGHT:
                    _x += CELL_WIDTH / 2 + MOVEMENT_ERROR_MARGIN / 2;
                    break;
                case LEFT:
                    _x -= CELL_WIDTH / 2 + MOVEMENT_ERROR_MARGIN / 2;
                    break;
            }
//        System.out.println("X: " + x + "-" + _x);
//        System.out.println("Y: " + y + "-" + _y);
            int tableX = (_x + width / 2 - X_BORDER) / CELL_WIDTH, tableY = (_y + height / 2 - Y_BORDER) / CELL_HEIGHT;
            Cella nuova = game.getGameMap().getCella(tableX, tableY);

            if (nuova.canBeUsed()) {
                switch (currentDir) {
                    case UP:
                        y -= MOVEMENT_SPEED;
                        break;
                    case DOWN:
                        y += MOVEMENT_SPEED;
                        break;
                    case RIGHT:
                        x += MOVEMENT_SPEED;
                        break;
                    case LEFT:
                        x -= MOVEMENT_SPEED;
                        break;
                }
                return true;
            }
        }
        return false;
    }

    protected boolean canChangeDir(Direction dir) {
        Cella adiacente = game.getGameMap().getCellaAdiacente(getTableX(), getTableY(), dir);
        if (adiacente.canBeUsed())
            return true;
        return false;
    }

    protected boolean isCentered() {
//        System.out.println("x " + getMiddleX());
//        System.out.println("table x " + (getTableX()));
//        System.out.println("table " + (getTableX()) * CELL_WIDTH);
//        System.out.println("border " + X_BORDER);
//        System.out.println("relative x " + (getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH));
//        System.out.println(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2);
        if (Math.abs(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2) < MOVEMENT_ERROR_MARGIN && Math.abs(getMiddleY() - Y_BORDER - (getTableY()) * CELL_HEIGHT - CELL_HEIGHT / 2) < MOVEMENT_ERROR_MARGIN)
            return true;
//        System.out.println("Not centered");
        return false;
    }

    public Renderer<T> getRenderer() {
        return renderer;
    }


    public Direction getCurrentDir() {
        return currentDir;
    }

    public Cella getCella() {
        int x = (this.x - X_BORDER) / TABLE_WIDTH;
        int y = (this.y - Y_BORDER) / TABLE_HEIGHT;

        return PacmanGame.getInstance().getGameMap().getCella(x, y);
    }

    public boolean isAppenaMorto() {
        return appenaMorto;
    }

    public void setAppenaMorto() {
        this.appenaMorto = true;
        new java.util.Timer().schedule( //per 2 oltre a quelli in cui il gioco sta fermo non posso essere mangiato
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        appenaMorto = false;
                    }
                },
                5000
        );
    }
}
