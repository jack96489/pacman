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
        y = 23 * CELL_HEIGHT + Y_BORDER + (CREATURA_HEIGHT/10);     //propozione a caso per farlo spawnare giusto
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


}
