package pacman.entity;
/**
 * @version 1.0
 * @author Giacomo Orsenigo
 */
import pacman.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Classe che rappresenta un fantasma del gioco
 * Estende {@link BaseActor}
 */
public class Fantasma extends BaseActor<Fantasma> {
    /**
     * Oggetto Random per estrarre nuova direzioni casualmente
     */
    private final Random rnDir = new Random();

    /**
     * Oggetto che rappresenta le immagini con cui deve disegnarsi il fantasma
     * @see FantasmaImages
     */
    private final FantasmaImages immagini;

    /**
     * Enumerazione che rappresena i possibili stati che può assumere il fantasma
     */
    public enum StatoFantasma {ATTIVO, VULNERABILE}

    /**
     * stato del fantasma
     * @see StatoFantasma
     */
    private StatoFantasma stato;

    /**
     * @brief Costruttore.
     * Inizializza gli attributi richiamando anche {@link super#BaseActor()}
     * @param colore colore del fantasmna
     */
    public Fantasma(Color colore) {
        super();
        this.color = colore;
        stato = StatoFantasma.ATTIVO;
        immagini = new FantasmaImages();
    }

    /**
     * @brief Costruttore.
     * Inizializza gli attributi richiamando anche {@link super#BaseActor(int, int, int, int, Color)}
     */
    public Fantasma(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        stato = StatoFantasma.ATTIVO;
        immagini = new FantasmaImages();
    }

    /**
     * @brief tasto premuto.
     * @param key tasto premuto
     * @throws UnsupportedOperationException
     */
    @Override
    public void onKeyPressed(int key) {
        throw new UnsupportedOperationException("Ghost can't press a key :(");
    }

    /**
     * @brief Tick.
     * Muove il fantasma. In caso di movimento possibile c'è 1 probabilità su 3 di cambiare direzione (se la nuova direzione è utilizzabile).
     * In caso di movimento non possibile estrae una nuova direzione possibile
     */
    @Override
    public void onTick() {
        if (!move()) {
            List<Direction> newDirs = possibleNewDirections();
            if (newDirs.isEmpty())
                throw new UnsupportedOperationException("Fantasma bloccato");       //unreachble
            currentDir = newDirs.get(rnDir.nextInt(newDirs.size()));
        } else if (isCentered() && rnDir.nextInt(3) == 1) {        //1 probabilità su 3 di cambiare direzione
            Direction newDir = Direction.values()[rnDir.nextInt(Direction.values().length)];
            if (canChangeDir(newDir) && !newDir.isOpposto(currentDir))
                currentDir = newDir;
        }
    }

    /**
     * @brief get immagine attuale
     * @return immagine da disegnare
     */
    public Image getImage() {
        return immagini.getImage();
    }

    /**
     * Classe che rappresenta le possibili immagini che può assumere il fantasma
     */
    public class FantasmaImages {

        /**
         * Mappa che contiene le immagini abbinate alla direzione
         */

        private final Map<Direction, Image> immagini;
        /**
         * Immagine del fantasma quando vulnerabile
         */
        private Image vulnerable;

        /**
         * @brief Costruttore.
         * Inizializza gli attributi
         * @see #loadImage(String)
         */
        public FantasmaImages() {
            immagini = new HashMap<>();
            String imagePrefix;
            if (color == Color.cyan)
                imagePrefix = "inky";
            else if (color == Color.red)
                imagePrefix = "blinky";
            else if (color == Color.orange)
                imagePrefix = "clyde";
            else if (color == Color.pink)
                imagePrefix = "pinky";
            else
                throw new IllegalStateException("Unexpected value: " + color);

            try {
                immagini.put(Direction.LEFT, loadImage(imagePrefix + "3left"));
                immagini.put(Direction.RIGHT, loadImage(imagePrefix + "3right"));
                immagini.put(Direction.UP, loadImage(imagePrefix + "3up"));
                immagini.put(Direction.DOWN, loadImage(imagePrefix + "3down"));
                vulnerable = loadImage("ghost3");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * @brief carica un'immagine.
         * Ritorna un oggetto {@link Image} contenente l'immagine caricata
         * @param name nome dell'immagine
         * @return immagine
         * @throws IOException In caso di errori
         */
        private BufferedImage loadImage(String name) throws IOException {
            InputStream resourceBuff = ClassLoader.getSystemResourceAsStream("icons/" + name + ".png");
            return ImageIO.read(Objects.requireNonNull(resourceBuff));
        }

        /**
         * @brief get immagine attuale.
         * @return immagine attuale
         */
        public Image getImage() {
            if (isVulnerable())
                return vulnerable;
            else
                return immagini.get(currentDir);
        }
    }

    /**
     * @brief vulnerabile.
     * @return true se {@link #stato} è {@link StatoFantasma#VULNERABILE}, false in caso contrario
     */
    public boolean isVulnerable() {
        return stato == StatoFantasma.VULNERABILE;
    }

    /**
     * @brief rende vulnerabile.
     * Rende il fantasma vulnerabile per 10 secondi e cambia la direzione con quella opposta
     */
    public void makeVulnerable() {
        stato = StatoFantasma.VULNERABILE;
        Direction newDir = currentDir.getOpposto();
        if (canChangeDir(newDir))
            currentDir = newDir;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        stato = StatoFantasma.ATTIVO;
                    }
                },
                10000
        );
    }

    /**
     * @brief morte.
     * Riporta il fantasma al centro, lo riattiva e richiama {@link #setAppenaMorto()}
     */
    public void muori() {
        x = X_BORDER + NUM_COLONNE / 2 * CELL_WIDTH + (CELL_WIDTH - CREATURA_WIDTH) / 2;
        y = Y_BORDER + NUM_RIGHE / 2 * CELL_HEIGHT + (CELL_HEIGHT - CREATURA_HEIGHT) / 2;
        stato = StatoFantasma.ATTIVO;       //una volta mangiato torna attivo
        setAppenaMorto();
    }
}
