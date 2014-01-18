package Build3;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanGame extends JPanel implements ActionListener {

    private final BufferedImage background, sun, cloud;
    public final Timer timer;
    private final Hero hero;

    public PanGame() throws IOException {
        addKeyListener(new Keys());
        setFocusable(true);
        setDoubleBuffered(true);
        background = ImageIO.read(new File("assets/background.png"));
        sun = ImageIO.read(new File("assets/sun.png"));
        cloud = ImageIO.read(new File("assets/cloud.png"));
        hero = new Hero();
        timer = new Timer(8, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(sun, 1060, 5, null);
        int[] nCloudX = {150, 250, 650, 1045};
        int[] nCloudY = {15, 10, 1, 60};
        for (int i = 0; i < nCloudX.length; i++) {
            g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
        }
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (hero.getAttack() == false) {
            hero.move();
            repaint();
        }
    }

    private class Keys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (hero.getAttack() == false) {
                hero.keyPressed(e);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (hero.getAttack() == false) {
                hero.keyReleased(e);
            }
        }
    }
}
