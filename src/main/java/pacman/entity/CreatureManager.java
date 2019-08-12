package pacman.entity;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class CreatureManager {
    private List<Fantasma> fantasmi;
    private Pacman pacman;

    public CreatureManager() {
        reset();
    }


    public synchronized void rendiFantasmiVulnerabili() {
        for (Fantasma f : fantasmi)
            f.makeVulnerable();
    }

    public synchronized void fantasmaMangiato() {
        //TODO: assegnare punti in base al numero di fantasmi mangiati (200,400,00,1600)
    }

    private void reset() {
        fantasmi = new Vector<>();
        fantasmi.add(new Fantasma(Color.CYAN));
        fantasmi.add(new Fantasma(Color.RED));
        fantasmi.add(new Fantasma(Color.orange));
        fantasmi.add(new Fantasma(Color.pink));
        pacman = new Pacman();
    }

    public List<Fantasma> getFantasmi() {
        return Collections.unmodifiableList(fantasmi);
    }

    public Fantasma getFantasma(int i) {
        return fantasmi.get(i);
    }

    public Pacman getPacman() {
        return pacman;
    }

    public void render(Graphics2D g2d) {
        fantasmi.forEach(f -> f.onRender(g2d));
        pacman.onRender(g2d);
    }
}
