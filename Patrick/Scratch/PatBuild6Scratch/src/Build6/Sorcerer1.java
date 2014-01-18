package Build6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sorcerer1 {

    private final int nSor1X, nSor1Y;
    private int nAttack = 1100, nHealth = 30, nChange = 0;
    private static boolean isAttack = false;
    private BufferedImage BImgSor1;
    private final BufferedImage BImgSor1Portrait;
    private final static BufferedImage[][] arBImgSor = new BufferedImage[3][2];
    private Rectangle recHealth;

    public Sorcerer1() throws IOException {
        arBImgSor[1][1] = ImageIO.read(new File("assets/sorcerer1.png"));
        arBImgSor[2][1] = ImageIO.read(new File("assets/sorcereratk1.png"));
        BImgSor1 = arBImgSor[1][1];
        BImgSor1Portrait = ImageIO.read(new File("assets/sorcerer1portrait.png"));
        nSor1X = 1140;
        nSor1Y = 508;
    }

    public int getX() {
        return nSor1X;
    }

    public int getY() {
        return nSor1Y;
    }

    public BufferedImage getImage() {
        Attack();
        return BImgSor1;
    }

    public boolean getAttack() {
        return isAttack;
    }

    public int getHealth() {
        return nHealth;
    }

    public Rectangle getBounds() {
        return new Rectangle(nSor1X, nSor1Y, BImgSor1.getWidth(), BImgSor1.getHeight());
    }

    public void Sor1Health(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(BImgSor1Portrait, 0, 50, null);
        recHealth = new Rectangle(50, 65, nHealth, 20);
        g.setColor(Color.red);
        g2.fill(recHealth);
        g.setColor(Color.black);
        g2.draw(recHealth);
    }

    public void setHealth(int health) {
        nHealth -= health;
    }

    public void Attack() {
        nAttack++;
        isAttack = false;
        if (nAttack > (int) (Math.random() * 2500 + 1300)) {
            BImgSor1 = arBImgSor[2][1];
            isAttack = true;
            nAttack = 0;

        }
        if (nChange == 40) {
            BImgSor1 = arBImgSor[1][1];
            nChange = 0;
        }
        nChange++;
    }

}
