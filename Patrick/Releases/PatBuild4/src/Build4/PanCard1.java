package Build4;

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

public class PanCard1 extends JPanel implements ActionListener {

    private final BufferedImage background, sun, cloud;
    public final Timer timer;
    private final Hero hero;
    private final Sorcerer1 sorcerer1;
    private final Sorcerer2 sorcerer2;
    private final Fireball1 fireball1;
    private final Fireball2 fireball2;

    public PanCard1() throws IOException {
        addKeyListener(new Keys());
        setFocusable(true);
        setDoubleBuffered(true);
        background = ImageIO.read(new File("assets/background.png"));
        sun = ImageIO.read(new File("assets/sun.png"));
        cloud = ImageIO.read(new File("assets/cloud.png"));
        hero = new Hero();
        sorcerer1 = new Sorcerer1();
        sorcerer2 = new Sorcerer2();
        fireball1 = new Fireball1();
        fireball2 = new Fireball2();
        timer = new Timer(11, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(sun, 1060, 5, null);
        int[] nCloudX = {150, 300, 650, 1045};
        int[] nCloudY = {15, 10, 1, 60};
        for (int i = 0; i < nCloudX.length; i++) {
            g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
        }
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g.drawImage(sorcerer1.getImage(), sorcerer1.getX(), sorcerer1.getY(), this);
        g.drawImage(sorcerer2.getImage(), sorcerer2.getX(), sorcerer2.getY(), this);
        g.drawImage(fireball1.getImage(), fireball1.getX(), fireball1.getY(), this);
        g.drawImage(fireball2.getImage(), fireball2.getX(), fireball2.getY(), this);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (hero.getAttack() == false) {
            hero.move();
        }
        if (fireball1.getImage() != null) {
            fireball1.move();
        }
        if (fireball2.getImage() != null) {
            fireball2.move();
        }
        repaint();
    }

    private class Keys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            hero.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            hero.keyReleased(e);
        }
    }
}