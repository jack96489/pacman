package pacman.entity;

import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Pacman extends BaseCreatura<Pacman> {


    private Queue<Direction> nuoveDirezioni;


    public Pacman() {
        super();
        color = Color.yellow;
        nuoveDirezioni = new LinkedBlockingDeque<>(MAX_STORED_MOVES);
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
        //System.out.println((x-X_BORDER)/CELL_WIDTH+"-"+(y-Y_BORDER)/CELL_HEIGHT);
        Cella cellaAttuale = game.getGameMap().getCella(getTableX(), getTableY());
        System.out.println("X: "+getTableX()+" Y: "+ getTableY());
        int _x = x, _y = y;
        switch (currentDir) {
            case UP:
                _y -= MOVEMENT_SPEED;
                break;
            case DOWN:
                _y += MOVEMENT_SPEED;
                break;
            case RIGHT:
                _x += MOVEMENT_SPEED;
                break;
            case LEFT:
                _x -= MOVEMENT_SPEED;
                break;
        }
        Cella nuova = game.getGameMap().getCella((_x - X_BORDER) / CELL_WIDTH, (_y - Y_BORDER) / CELL_HEIGHT);

        if (nuova.canBeUsed()) {
            x = _x;
            y = _y;
        }

        if (cellaAttuale.getTipoCella() == Cella.TipoCella.MELA) {
            PacmanGame.getInstance().melaMangiata();
            cellaAttuale.melaMangiata();
        }


        for (Direction dir : nuoveDirezioni) {
            if (canChangeDir(dir) && isCentered()) {
                currentDir = dir;
                nuoveDirezioni.clear();
            }
        }

    }

    private boolean canChangeDir(Direction dir) {
        Cella adiacente = game.getGameMap().getCellaAdiacente(getTableX(), getTableY(), dir);
        if (adiacente.canBeUsed())
            return true;
        return false;
    }

    private boolean isCentered() {
        System.out.println("x " + getMiddleX());
        System.out.println("table x " + (getTableX()));
        System.out.println("table " + (getTableX()) * CELL_WIDTH);
        System.out.println("border " + X_BORDER);
        System.out.println("relative x " + (getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH));
        System.out.println(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2);
        if (Math.abs(getMiddleX() - X_BORDER - (getTableX()) * CELL_WIDTH - CELL_WIDTH / 2) < MOVEMENT_ERROR_MARGIN && Math.abs(getMiddleY() - Y_BORDER - (getTableY()) * CELL_HEIGHT - CELL_HEIGHT / 2) < MOVEMENT_ERROR_MARGIN)
            return true;
        return false;
    }


}
