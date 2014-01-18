package build1;

import java.awt.Graphics;
import java.awt.Graphics2D;
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

    public BufferedImage background;
    public BufferedImage sun;
    public BufferedImage cloud;
    private Timer timer;
    private Hero hero;
    public int nV = 5;

    public PanGame() {
        addKeyListener(new Keys());
        setFocusable(true);
        setDoubleBuffered(true);
        try {
            background = ImageIO.read(new File("assets/background.png"));
            sun = ImageIO.read(new File("assets/sun.png"));
            cloud = ImageIO.read(new File("assets/cloud.png"));
        } catch (IOException e) {
        }
        hero = new Hero();
        timer = new Timer(nV, this);
        timer.start();
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(background, 0, 0, null);
        g.drawImage(sun, 1070, 5, null);
        int[] nCloudX = {200, 600, 1050};
        int[] nCloudY = {100, 50, 73};
        for (int i=0;i<3;i++){
            g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
        }
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void actionPerformed(ActionEvent e) {
        hero.move();
        repaint();
    }

    private class Keys extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            hero.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            hero.keyPressed(e);
        }
    }
}
