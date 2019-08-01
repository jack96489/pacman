package pacman.entity;

import pacman.Direction;

import java.awt.event.KeyEvent;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class Pacman extends BaseCreatura<Pacman>{

    private Queue<Direction> nuoveDirezioni;


    public Pacman() {
        nuoveDirezioni=new LinkedBlockingDeque<>(MAX_STORED_MOVES);
    }

    @Override
    public void onKeyPressed(int keyCode) {
        switch(keyCode) {
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
        if(!dir.equals(lastDir) && !dir.equals(lastDir.getOpposto()))
            nuoveDirezioni.offer(dir);
    }
    @Override
    public void onTick() {

    }
}
