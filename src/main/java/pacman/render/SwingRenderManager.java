package pacman.render;

import pacman.Costanti;
import pacman.DatiCondivisi;
import pacman.entity.Fantasma;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SwingRenderManager extends JPanel implements Costanti, RenderManager, KeyListener {

    private DatiCondivisi dati;
    private JFrame frame;

    public SwingRenderManager() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setContentPane(this);
    }

    @Override
    public void render() {
        if(!frame.isVisible())
            frame.setVisible(true);
        repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.drawRect((FRAME_WIDTH-TABLE_WIDTH)/2,(FRAME_HEIGHT-TABLE_HEIGHT)/2,TABLE_WIDTH,TABLE_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        dati.getFantasmi().forEach(Fantasma::onRender);
        dati.getPacman().onRender();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        dati.getPacman().onKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
