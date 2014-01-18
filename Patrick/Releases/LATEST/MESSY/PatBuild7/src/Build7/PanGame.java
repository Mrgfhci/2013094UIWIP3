package Build7;

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

public class PanGame extends JPanel implements ActionListener {

    private int nSunX, nSunY, nChange = 0;
    private BufferedImage background;
    private final BufferedImage sun, cloud;
    public final Timer timer;
    private final Hero hero;
    private final Sorcerer1 sor1;
    private final Sorcerer2 sor2;
    private final Fireball1 fireball1;
    private final Fireball2 fireball2;
    private final Knight knight;
    private boolean isCol1 = false, isCol2 = false, isCol = false,
            isSorcerer = true, isKnight = false;
    private final Main main = new Main();

    public PanGame() throws IOException {
        addKeyListener(new Keys());
        setFocusable(true);
        setDoubleBuffered(true);
        background = ImageIO.read(new File("assets/background1.png"));
        sun = ImageIO.read(new File("assets/sun.png"));
        cloud = ImageIO.read(new File("assets/cloud.png"));
        hero = new Hero();
        sor1 = new Sorcerer1();
        sor2 = new Sorcerer2();
        fireball1 = new Fireball1();
        fireball2 = new Fireball2();
        knight = new Knight();
        timer = new Timer(9, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        g.drawImage(sun, nSunX, nSunY, null);
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        //Loads the sorcerer level
        if (isSorcerer == true) {
            nSunX = 1068;
            nSunY = 55;
            int[] nCloudX = {250, 400, 650, 1045};
            int[] nCloudY = {40, 85, 5, 165};
            for (int i = 0; i < nCloudX.length; i++) {
                g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
            }
            if (sor1.getHealth() >= 0) {
                g.drawImage(sor1.getImage(), sor1.getX(), sor1.getY(), this);
            }
            if (sor2.getHealth() >= 0) {
                g.drawImage(sor2.getImage(), sor2.getX(), sor2.getY(), this);
            }
            if (sor1.getAttack()) {
                fireball1.setVisible(true);
            }
            if (fireball1.isVisible()) {
                g.drawImage(fireball1.getImage(), fireball1.getX(), fireball1.getY(), this);
            }
            if (sor2.getAttack()) {
                fireball2.setVisible(true);
            }
            if (fireball2.isVisible()) {
                g.drawImage(fireball2.getImage(), fireball2.getX(), fireball2.getY(), this);
            }
            sor1.Sor1Health(g);
            sor2.Sor2Health(g);
            checkCollisionsSor();
        }
        //Loads the knight level
        if (isKnight == true) {
            nSunX = 600;
            nSunY = 5;
            int[] nCloudX = {450, 700, 1100, 100};
            int[] nCloudY = {40, 85, 5, 130};
            for (int i = 0; i < nCloudX.length; i++) {
                g.drawImage(cloud, nCloudX[i], nCloudY[i], null);
            }
            if (knight.getHealth() >= 0) {
                g.drawImage(knight.getImage(), knight.getX(), knight.getY(), this);
            }
            knight.Knight1Health(g);
            checkCollisionsKnight();
        }
        hero.HeroHealth(g);
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }

    //http://zetcode.com/tutorials/javagamestutorial/collision/
    public void checkCollisionsSor() {
        Rectangle RecHero = hero.getBounds(), RecFireball1 = fireball1.getBounds(),
                RecFireball2 = fireball2.getBounds(), RecSor1 = sor1.getBounds(),
                RecSor2 = sor2.getBounds();
        //checks collision of fireballs and hero
        if (fireball1.isVisible() && RecHero.intersects(RecFireball1)) {
            fireball1.setVisible(false);
            fireball1.setX(1120);
            hero.setHitRight(true);
        }
        if (fireball2.isVisible() && RecHero.intersects(RecFireball2)) {
            fireball2.setVisible(false);
            fireball2.setX(115);
            hero.setHitLeft(true);
        }
        //checks collision of the sorcerers and hero
        if (RecHero.intersects(RecSor1)) {
            if (hero.getRight() && sor1.getHealth() >= 0) {
                isCol1 = true;
                if (hero.getWeak()) {
                    sor1.setHealth(10);
                    hero.setWeak(false);
                } else if (hero.getStrong()) {
                    sor1.setHealth(30);
                    hero.setStrong(false);
                }
            } else if (!hero.getRight()) {
                isCol1 = false;
            }
        }
        if (RecHero.intersects(RecSor2)) {
            if (!hero.getRight() && sor2.getHealth() >= 0) {
                isCol2 = true;
                if (hero.getWeak()) {
                    sor2.setHealth(10);
                    hero.setWeak(false);
                } else if (hero.getStrong()) {
                    sor2.setHealth(30);
                    hero.setStrong(false);
                }
            } else if (hero.getRight()) {
                isCol2 = false;
            }
        }
        //if both fireballs collide
        if (RecFireball1.intersects(RecFireball2)) {
            fireball1.setVisible(false);
            fireball1.setX(1120);
            fireball2.setVisible(false);
            fireball2.setX(115);
        }
        if (!RecHero.intersects(RecSor1)) {
            isCol1 = false;
        }
        if (!RecHero.intersects(RecSor2)) {
            isCol2 = false;
        }
        if (sor1.getHealth() <= 0 && sor2.getHealth() <= 0 && nChange == 0) {
            isSorcerer = false;
            isKnight = true;
            try {
                background = ImageIO.read(new File("assets/background2.png"));
            } catch (IOException e) {
                System.out.println("IOException!");
            }
            nChange++;
            hero.setX(300);
            hero.setRight(true);
            isCol1 = isCol2 = false;
        }
    }

    public void checkCollisionsKnight() {
        Rectangle RecHero = hero.getBounds(), RecKnight = knight.getBounds();
        //checks collision of the knight and hero
        if (RecHero.intersects(RecKnight)) {
            if (hero.getRight() && knight.getHealth() >= 0) {
                isCol = true;
                if (hero.getWeak() && !knight.getBlock()) {
                    knight.setHealth(10);
                    hero.setWeak(false);
                } else if (hero.getStrong() && !knight.getBlock()) {
                    knight.setHealth(30);
                    hero.setStrong(false);
                }
            } else if (!hero.getRight()) {
                isCol = false;
            }
            if (knight.getAttack()) {
                knight.setAttack(false);
                hero.setHit(true);
            }
        }
        if (!RecHero.intersects(RecKnight)) {
            isCol = false;
        }
        if (knight.getHealth() <= 0) {
            main.Win();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!hero.getAction() && isCol2 == false && isCol1 == false && isCol ==  false) {
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
            if (hero.getPause()) {
                timer.stop();
            } else if (!hero.getPause()) {
                timer.start();
            }
        }
    }
}
