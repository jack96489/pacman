package pacman.entity;
/**
 * @version 1.0
 * @author Giacomo Orsenigo
 */
import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 * Classe che gestisce gli attori {@link BaseActor}
 */
public class ActorManager {
    /**
     * Lista dei fantasmi del gioco
     * @see Fantasma
     */
    private List<Fantasma> fantasmi;

    /**
     * Pacman
     * @see Pacman
     */
    private Pacman pacman;

    /**
     * @brief Costruttore.
     * Richiama {@link #reset()}
     */
    public ActorManager() {
        reset();
    }

    /**
     * @brief rende vulnerabili tutti i fantasmi.
     * Per ogni fanstasma richiama {@link Fantasma#makeVulnerable()}
     */
    public synchronized void rendiFantasmiVulnerabili() {
        for (Fantasma f : fantasmi)
            f.makeVulnerable();
    }

    /**
     *
     */
    public synchronized void fantasmaMangiato() {
        //TODO: assegnare punti in base al numero di fantasmi mangiati (200,400,00,1600)
    }

    /**
     * @brief reset.
     * Inizializza tutti gli attributi
     */
    private void reset() {
        fantasmi = new Vector<>();
        fantasmi.add(new Fantasma(Color.CYAN));
        fantasmi.add(new Fantasma(Color.RED));
        fantasmi.add(new Fantasma(Color.orange));
        fantasmi.add(new Fantasma(Color.pink));
        pacman = new Pacman();
    }

    /**
     * @return lista {@link Fantasma} non modificabile
     */
    public List<Fantasma> getFantasmi() {
        return Collections.unmodifiableList(fantasmi);
    }

    /**
     * @param i index fantasma
     * @return fantasma nella lista {@link #fantasmi} alla posizione richiesta
     */
    public Fantasma getFantasma(int i) {
        return fantasmi.get(i);
    }

    /**
     * @return {@link #pacman}
     */
    public Pacman getPacman() {
        return pacman;
    }

    /**
     * @brief disegna gli attori.
     * Richiama il metodo {@link pacman.render.BaseRenderable#onRender(Graphics2D)} su tutti gli attori
     * @param g2d graphics
     */
    public void render(Graphics2D g2d) {
        fantasmi.forEach(f -> f.onRender(g2d));
        pacman.onRender(g2d);
    }
}
