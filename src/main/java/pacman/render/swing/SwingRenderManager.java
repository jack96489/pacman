package pacman.render.swing;

import pacman.Costanti;
import pacman.PacmanGame;
import pacman.entity.Fantasma;
import pacman.entity.Pacman;
import pacman.mappa.Cella;
import pacman.mappa.CellaRenderer;
import pacman.render.BaseRenderable;
import pacman.render.RenderManager;
import pacman.render.Renderable;
import pacman.render.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class SwingRenderManager extends JPanel implements Costanti, RenderManager, KeyListener {

    private static final PacmanGame game = PacmanGame.getInstance();
    private JFrame frame;
    private Map rendererMap;
    private static long framesRendered;

    @SuppressWarnings("unchecked")
    public SwingRenderManager() {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(this);
        rendererMap = new HashMap();
        rendererMap.put(Fantasma.class, new FantasmaRenderer(this));
        rendererMap.put(Pacman.class, new PacmanRenderer(this));
        rendererMap.put(Cella.class,new CellaRenderer(this));
        frame.addKeyListener(this);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.pack();
        setBackground(Color.BLACK);
    }

    @Override
    public void render() {
        if (!frame.isVisible())
            frame.setVisible(true);
        repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        final Graphics2D g2d = (Graphics2D) g;
        //g2d.setColor(Color.BLUE);
        //g2d.drawRect((FRAME_WIDTH - TABLE_WIDTH) / 2, (FRAME_HEIGHT - TABLE_HEIGHT) / 2, TABLE_WIDTH, TABLE_HEIGHT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;
        game.getGameMap().render(g2d);
        g2d.setColor(Color.BLUE);
        g2d.drawRect(X_BORDER, Y_BORDER, TABLE_WIDTH, TABLE_HEIGHT);
        //System.out.println("render");
        game.getFantasmi().forEach(f->f.onRender(g2d));
        game.getPacman().onRender(g2d);
        g2d.drawString( "Punti: "+Integer.toString(game.getPunti()),FRAME_WIDTH/2-100,Y_BORDER/2);
        g2d.drawString( "Vite riumanenti: "+Integer.toString(game.getVite()),FRAME_WIDTH/2+100,Y_BORDER/2);
        framesRendered++;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //System.out.println(e.getKeyCode());
        game.getPacman().onKeyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    @Override
    @SuppressWarnings("unchecked")
    public <T extends Renderable> Renderer<T> getRendererFor(Class<? extends BaseRenderable> type) {
        return (Renderer<T>) rendererMap.get(type);
    }

    @Override
    public void showDialog(String str) {
        JOptionPane.showMessageDialog(frame,str);
    }

    public long getFramesRendered() {
        return framesRendered;
    }
}
