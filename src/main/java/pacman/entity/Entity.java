package pacman.entity;

import pacman.render.Renderable;

public interface Entity extends Renderable {
    void onKeyPressed(int key);
    void onTick();
}
