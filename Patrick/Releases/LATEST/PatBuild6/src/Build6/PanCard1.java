package Build6;

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
import java.awt.Rectangle;

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
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {

        super.paint(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(sun, 1060, 5, null);
        int[] nCloudX = {250, 400, 650, 1045};
        int[] nCloudY = {15, 10, 1, 60};
        for (int i = 0; i < nCloudX.length; i++) {
            g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
        }
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        if (sorcerer1.getHealth() >= 0) {
            g.drawImage(sorcerer1.getImage(), sorcerer1.getX(), sorcerer1.getY(), this);
        }
        if (sorcerer2.getHealth() >= 0) {
            g.drawImage(sorcerer2.getImage(), sorcerer2.getX(), sorcerer2.getY(), this);
        }
        if (sorcerer1.getAttack()) {
            fireball1.setVisible(true);
        }
        if (fireball1.isVisible()) {
            g.drawImage(fireball1.getImage(), fireball1.getX(), fireball1.getY(), this);
        }
        if (sorcerer2.getAttack()) {
            fireball2.setVisible(true);
        }
        if (fireball2.isVisible()) {
            g.drawImage(fireball2.getImage(), fireball2.getX(), fireball2.getY(), this);
        }
        hero.HeroHealth(g);
        sorcerer1.Sor1Health(g);
        sorcerer2.Sor2Health(g);
        checkCollisions();
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    //http://zetcode.com/tutorials/javagamestutorial/collision/
    public void checkCollisions() {
        Rectangle RecHero = hero.getBounds(), RecFireball1 = fireball1.getBounds(),
                RecFireball2 = fireball2.getBounds(), RecSor1 = sorcerer1.getBounds(),
                RecSor2 = sorcerer2.getBounds();
        if (fireball1.isVisible()) {
            if (RecHero.intersects(RecFireball1)) {
                fireball1.setVisible(false);
                fireball1.setX(1110);
                hero.setHitRight(true);
                System.out.println("hhi");
            }
        }
        if (fireball2.isVisible()) {
            if (RecHero.intersects(RecFireball2)) {
                fireball2.setVisible(false);
                fireball2.setX(160);
                hero.setHitLeft(true);
            }
        }
        if (RecHero.intersects(RecSor1)) {
            if (hero.getQuick()) {
                sorcerer1.setHealth(10);
                 hero.setQuick(false);
            } else if (hero.getStrong()) {
                sorcerer1.setHealth(30);
                hero.setStrong(false);
            }
        }
        if (RecHero.intersects(RecSor2)) {
            if (hero.getQuick()) {
                sorcerer2.setHealth(10);
                hero.setQuick(false);
            } else if (hero.getStrong()) {
                sorcerer2.setHealth(30);
                hero.setStrong(false);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (hero.getAttack() == false) {
            hero.move();
        }
        if (fireball1.isVisible()) {
            fireball1.move();
        }
        if (fireball2.isVisible()) {
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
            if (hero.getPause() == true) {
                timer.stop();
            } else if (hero.getPause() == false) {
                timer.start();
            }
        }
    }
}
