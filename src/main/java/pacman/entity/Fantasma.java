package pacman.entity;

import pacman.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Fantasma extends BaseCreatura<Fantasma> {
    private final Random rnDir = new Random();

    private final FantasmaImages immagini;

    public enum StatoFantasma {ATTIVO, VULNERABILE}

    private StatoFantasma stato;

    public Fantasma(Color colore) {
        super();
        this.color = colore;
        stato = StatoFantasma.ATTIVO;
        immagini = new FantasmaImages();
    }

    public Fantasma(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        stato = StatoFantasma.ATTIVO;
        immagini = new FantasmaImages();
    }

    @Override
    public void onKeyPressed(int key) {
        throw new UnsupportedOperationException("Ghost can't press a key :(");
    }

    @Override
    public void onTick() {
        if (!checkMovement()) {
            Direction newDir;
            do {
                newDir = Direction.values()[rnDir.nextInt(Direction.values().length)];
            } while (!canChangeDir(newDir));
            currentDir = newDir;
        } else if (isCentered() && rnDir.nextInt(3) == 1) {        //1 probabilità su 3 di cambiare direzione
            Direction newDir = Direction.values()[rnDir.nextInt(Direction.values().length)];
            if (canChangeDir(newDir) && !newDir.isOpposto(currentDir))
                currentDir = newDir;
        }
    }

    public Image getImage() {
        return immagini.getImage();
    }

    public class FantasmaImages {
        private final Map<Direction, Image> immagini;
        private Image vulnerable;

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

        private BufferedImage loadImage(String name) throws IOException {
            InputStream resourceBuff = ClassLoader.getSystemResourceAsStream("icons/" + name + ".png");
            return ImageIO.read(Objects.requireNonNull(resourceBuff));
        }

        public Image getImage() {
            if (isVulnerable())
                return vulnerable;
            else
                return immagini.get(currentDir);
        }
    }

    public boolean isVulnerable() {
        return stato == StatoFantasma.VULNERABILE;
    }

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

    public void muori() {
        x = X_BORDER + NUM_COLONNE / 2 * CELL_WIDTH + (CELL_WIDTH - CREATURA_WIDTH) / 2;
        y = Y_BORDER + NUM_RIGHE / 2 * CELL_HEIGHT + (CELL_HEIGHT - CREATURA_HEIGHT) / 2;
    }
}
