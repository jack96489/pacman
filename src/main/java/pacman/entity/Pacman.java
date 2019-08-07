package pacman.entity;

import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Pacman extends BaseCreatura<Pacman> {


    private Queue<Direction> nuoveDirezioni;


    public Pacman() {
        super();
        color = Color.yellow;
        nuoveDirezioni = new LinkedBlockingQueue<>(MAX_STORED_MOVES);
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                changeDirection(Direction.UP);
                break;
            case KeyEvent.VK_DOWN:
                changeDirection(Direction.DOWN);
                break;
            case KeyEvent.VK_RIGHT:
                changeDirection(Direction.RIGHT);
                break;
            case KeyEvent.VK_LEFT:
                changeDirection(Direction.LEFT);
                break;
        }
    }

    private void changeDirection(Direction dir) {
        Direction lastDir = nuoveDirezioni.isEmpty() ? currentDir : nuoveDirezioni.peek();
        if (!dir.equals(lastDir) /*&& !dir.equals(lastDir.getOpposto())*/)
            nuoveDirezioni.offer(dir);
    }

    @Override
    public void onTick() {
        checkMovement();
        checkForMele();
        checkForNewDir();
    }

    private void checkMovement() {
        int _x = x, _y = y;
        switch (currentDir) {
            case UP:
                _y -= CELL_HEIGHT / 2 + MOVEMENT_ERROR_MARGIN/2;
                break;
            case DOWN:
                _y += CELL_HEIGHT / 2 +MOVEMENT_ERROR_MARGIN/2;
                break;
            case RIGHT:
                _x += CELL_WIDTH / 2+ MOVEMENT_ERROR_MARGIN/2;
                break;
            case LEFT:
                _x -= CELL_WIDTH / 2 + MOVEMENT_ERROR_MARGIN/2;
                break;
        }
        System.out.println("X: " + x + "-" + _x);
        System.out.println("Y: " + y + "-" + _y);
        Cella nuova = game.getGameMap().getCella((_x + width / 2 - X_BORDER) / CELL_WIDTH, (_y + height / 2 - Y_BORDER) / CELL_HEIGHT);

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
        }
    }

    private void checkForNewDir() {
        if (!nuoveDirezioni.isEmpty())
            if (canChangeDir(nuoveDirezioni.peek()) && isCentered())
                currentDir = nuoveDirezioni.poll();
            else {
                final List<Direction> newDirs = new ArrayList<>(nuoveDirezioni);
                System.out.println(newDirs.size());
                for (int i = 1; i < nuoveDirezioni.size(); i++)
                    if (canChangeDir(newDirs.get(i)) && isCentered()) {
                        currentDir = newDirs.get(i);
                        nuoveDirezioni.clear();
                        break;
                    }
            }
    }

    private void checkForMele() {
        Cella cellaAttuale = game.getGameMap().getCella(getTableX(), getTableY());
        if (cellaAttuale.getTipoCella() == Cella.TipoCella.MELA) {
            PacmanGame.getInstance().melaMangiata();
            cellaAttuale.melaMangiata();
        }
        if (cellaAttuale.getTipoCella() == Cella.TipoCella.MELONE) {
            PacmanGame.getInstance().melonaMangiato();
            cellaAttuale.melaMangiata();
        }
    }

    private boolean canChangeDir(Direction dir) {
        Cella adiacente = game.getGameMap().getCellaAdiacente(getTableX(), getTableY(), dir);
        if (adiacente.canBeUsed())
            return true;
        return false;
    }

    private boolean isCentered() {
//        System.out.println("x " + getMiddleX());
//        System.out.println("table x " + (getTableX()));
//        System.out.println("table " + (getTableX()) * CELL_WIDTH);
//        System.out.println("border " + X_BORDER);
//        System.out.println("relative x " + (getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH));
//        System.out.println(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2);
        if (Math.abs(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2) < MOVEMENT_ERROR_MARGIN && Math.abs(getMiddleY() - Y_BORDER - (getTableY()) * CELL_HEIGHT - CELL_HEIGHT / 2) < MOVEMENT_ERROR_MARGIN)
            return true;
        System.out.println("Not centered");
        return false;
    }


}
