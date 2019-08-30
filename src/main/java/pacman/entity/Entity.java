package pacman.entity;

/**
 * @version 1.0
 * @author Giacomo Orsenigo
 */

import pacman.render.Renderable;

/**
 * L'interfaccia rappresenta una qualsiasi entità del gioco
 * Estende {@link Renderable}
 */
public interface Entity extends Renderable {
    /**
     * @brief Metodo richiamato alla pressione di un tasto
     * @param key tasto premuto
     */
    void onKeyPressed(int key);

    /**
     * @brief Aggiorna l'entità
     */
    void onTick();
}
