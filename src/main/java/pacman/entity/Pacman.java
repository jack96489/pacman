package pacman.entity;
/**
 * @version 1.0
 * @author Giacomo Orsenigo
 */
import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Classe che rappresenta il pacman
 * Estende {@link BaseActor}
 */
public class Pacman extends BaseActor<Pacman> {

    /**
     * Coda di nuove direzioni
     */
    private Queue<Direction> nuoveDirezioni;

    /**
     * @brief Costruttore.
     * Inizializza gli attributi richiamando anche {@link super#BaseActor()}
     */
    public Pacman() {
        super();
        y = 23 * CELL_HEIGHT + Y_BORDER + (CREATURA_HEIGHT/10);     //propozione a caso per farlo spawnare giusto
        color = Color.yellow;
        nuoveDirezioni = new LinkedBlockingQueue<>(MAX_STORED_MOVES);
    }

    /**
     * @brief tasto premuto.
     * Richiama il metodo {@link #changeDirection(Direction)} passandogli la direzione corrispondente
     * @param keyCode codice nuova direzione
     */
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

    /**
     * @brief cambia direzione.
     * Aggiunge la direzione passata alla code {@link #nuoveDirezioni} se non è uguale a quella precedente
     * @param dir direzione da aggiungere
     */
    private void changeDirection(Direction dir) {
        Direction lastDir = nuoveDirezioni.isEmpty() ? currentDir : nuoveDirezioni.peek();
        if (!dir.equals(lastDir) /*&& !dir.equals(lastDir.getOpposto())*/)
            nuoveDirezioni.offer(dir);
    }

    /**
     * @brief Tick.
     * Richiama i metodi {@link super#move()}, {@link #checkForMele()} e {@link #checkForNewDir()}
     */
    @Override
    public void onTick() {
        move();
        checkForMele();
        checkForNewDir();
    }

    /**
     * @brief controlla le nuove direzioni.
     * Controlla se è possibile cambiare direzione con una di quelle disponibili nella coda {@link #nuoveDirezioni}.
     * Nel caso non sia l'ultima svuota la coda.
     * @see #canChangeDir(Direction)
     */
    private void checkForNewDir() {
        if (!nuoveDirezioni.isEmpty())
            if (canChangeDir(nuoveDirezioni.peek()) && isCentered())
                currentDir = nuoveDirezioni.poll();
            else {
                final List<Direction> newDirs = new ArrayList<>(nuoveDirezioni);
//                System.out.println(newDirs.size());
                for (int i = 1; i < nuoveDirezioni.size(); i++)
                    if (canChangeDir(newDirs.get(i)) && isCentered()) {
                        currentDir = newDirs.get(i);
                        nuoveDirezioni.clear();
                        break;
                    }
            }
    }

    /**
     * @brief controlla le mele.
     * Controlla se nella cella attuale è presente una mela e in caso positivo la mangia e incrementa il punteggio
     * @see Cella#melaMangiata()
     * @see PacmanGame#melaMangiata()
     */
    private void checkForMele() {
        Cella cellaAttuale = getCellaAttuale();
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
