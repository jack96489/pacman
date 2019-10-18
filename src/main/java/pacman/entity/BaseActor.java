package pacman.entity;
/**
 * @version 1.0
 * @author Giacomo Orsenigo
 */

import pacman.Costanti;
import pacman.Direction;
import pacman.PacmanGame;
import pacman.mappa.Cella;
import pacman.render.BaseRenderable;
import pacman.render.Renderer;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe rappresenta una qualsiasi personaggio del gioco (pacmna e fantasmi)
 * Estende {@link BaseRenderable} e implementa {@link Entity} e {@link Costanti}
 *
 * @param <T>
 */
public abstract class BaseActor<T extends BaseActor> extends BaseRenderable<T> implements Entity, Costanti {
    protected final int startX, startY;
    /**
     * puntarore all'istanza di gioco
     */
    protected static final PacmanGame game = PacmanGame.getInstance();

    /**
     * Direzione attuale
     *
     * @see Direction
     */
    protected Direction currentDir;

    /**
     * Indica se l'attore è morto negli ultimi {@link Costanti#BLINK_TIME} secondi
     * Necessario per farlo lampeggiare e non farlo rimangiare subito dopo
     */
    private boolean appenaMorto = false;

    /**
     * @brief Costruttore vuoto.
     * Inizializza gli attributi con valori di default, utilizzande {@link Costanti}.
     * Richiama {@link #BaseActor(int, int, int, int, Color)}
     */
    public BaseActor(int x, int y) {
        this(x, y, CREATURA_WIDTH, CREATURA_HEIGHT, Color.BLACK);
    }

    /**
     * @param x      coordinata x
     * @param y      coordinata y
     * @param width  larghezza
     * @param height altezza
     * @param color  colore
     * @brief Costruttore.
     * Inizializza gli attributi con i valori passati.
     */
    public BaseActor(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        currentDir = Direction.RIGHT;
        startX = x;
        startY = y;
    }

    /**
     * @return lista di direzioni
     * @brief Ritorna le possibili direzione in cui può andare.
     * Partendo dalla cella attuale controlla tutte quelle intorno e crea una lista con le possibili direzioni.
     */
    public List<Direction> possibleNewDirections() {
        List<Direction> possibleDirs = new ArrayList<>();
        final int x = getTableX(), y = getTableY();

        for (Direction d : Direction.values())
            if (game.getGameMap().getCellaAdiacente(x, y, d).canBeUsed())
                possibleDirs.add(d);

//        if (PacmanGame.getInstance().getGameMap().getCella(x + 1, y).canBeUsed())
//            possibleDirs.add(Direction.RIGHT);
//        if (PacmanGame.getInstance().getGameMap().getCella(x - 1, y).canBeUsed())
//            possibleDirs.add(Direction.LEFT);
//        if (PacmanGame.getInstance().getGameMap().getCella(x, y + 1).canBeUsed())
//            possibleDirs.add(Direction.DOWN);
//        if (PacmanGame.getInstance().getGameMap().getCella(x, y - 1).canBeUsed())
//            possibleDirs.add(Direction.UP);

        possibleDirs.remove(currentDir);
        return possibleDirs;
    }

    /**
     * @return true se è stato possibile il movimento, false in caso contrario
     * @brief muove l'attore dopo gli opportuni controlli.
     * Utilizzando il metodo {@link Cella#canBeUsed()}, controlla che la casella in cui si deve spostare (in base alla direzione {@link #currentDir}) sia utilizzabile.
     * In caso di uscita dalla mappa lo teletrasporta dall'alltra parte.
     */
    protected boolean move() {
//        System.out.println(TABLE_WIDTH + X_BORDER - (x + width));
        if (x - X_BORDER < MOVEMENT_SPEED + MOVEMENT_ERROR_MARGIN && currentDir == Direction.LEFT) {    //teletrasporto da sinistra
            x += TABLE_WIDTH - width;
            return true;
        } else if (TABLE_WIDTH + X_BORDER - (x + width) < MOVEMENT_SPEED + MOVEMENT_ERROR_MARGIN && currentDir == Direction.RIGHT) {    //teletrasporto da destra
            x -= TABLE_WIDTH - width;
            return true;
        } else {    //movimento normale
            int _x = x, _y = y;
//            if(this instanceof Pacman)
//                System.out.println(_x+" - "+(_x + width / 2 - X_BORDER) / CELL_WIDTH);
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
//            if (this instanceof Pacman) System.out.println(tableX);
            final Cella nuova = game.getGameMap().getCella(tableX, tableY);

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

    /**
     * @param dir nuova direzione
     * @return true se è possibile cambiare direzione, false in caso contrario
     * @brief controlla se è possibile cambiare la direzione attuale in quella data.
     * @see pacman.mappa.Mappa#getCellaAdiacente(int, int, Direction)
     * @see #getTableX()
     * @see #getTableY()
     */
    protected boolean canChangeDir(Direction dir) {
        Cella adiacente = game.getGameMap().getCellaAdiacente(getTableX(), getTableY(), dir);
        if (adiacente.canBeUsed())
            return true;
        return false;
    }

    /**
     * @return true se è centrato, false in case contrario
     * @brief controlla se è posizionato al centro della cella.
     * @see #getTableX()
     * @see #getTableY()
     * @see #getMiddleX()
     * @see #getMiddleY()
     */
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

    public Direction getCurrentDir() {
        return currentDir;
    }

    /**
     * @return cella attuale
     * @brief ritorna la cella attuale.
     * @see #getTableY()
     * @see #getTableY()
     */
    public Cella getCellaAttuale() {
        return PacmanGame.getInstance().getGameMap().getCella(getTableX(), getTableY());
    }

    /**
     * @return {@link #appenaMorto}
     */
    public boolean isAppenaMorto() {
        return appenaMorto;
    }

    /**
     * @brief imposta l'attore come appena morto.
     * imposta {@link #appenaMorto} a true e avvia un timer che lo riporta a false dopo {@link Costanti#BLINK_TIME} secondi
     */
    private void setAppenaMorto() {
        this.appenaMorto = true;
        new java.util.Timer().schedule( //per 2 secondi oltre a quelli in cui il gioco sta fermo non posso essere mangiato
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        appenaMorto = false;
                    }
                },
                BLINK_TIME
        );
    }


    /**
     * @brief morte.
     * Riporta l'attore al centro, lo riattiva e richiama {@link #setAppenaMorto()}
     */
    public void muori() {
        setAppenaMorto();
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        reset();
                    }
                },
                PAUSE_TIME
        );
    }

    private void reset(){
        x = startX;
        y = startY;
        currentDir = Direction.RIGHT;
    }
}
