package Build6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sorcerer2 {

    private int nSor2X, nSor2Y, nAttack = 0, nHealth = 30;
    private static boolean isAttack = false;
    private BufferedImage BImgSor2;
    private final BufferedImage BImgSor2Portrait;
    private final static BufferedImage[][] arBImgSor = new BufferedImage[3][2];
    private Rectangle recHealth;

    public Sorcerer2() throws IOException {
        arBImgSor[1][1] = ImageIO.read(new File("assets/sorcerer2.png"));
        arBImgSor[2][1] = ImageIO.read(new File("assets/sorcereratk2.png"));
        BImgSor2 = arBImgSor[1][1];
        BImgSor2Portrait = ImageIO.read(new File("assets/sorcerer2portrait.png"));
        nSor2X = 40;
        nSor2Y = 508;
    }

    public int getX() {
        return nSor2X;
    }

    public int getY() {
        return nSor2Y;
    }

    public BufferedImage getImage() {
        Attack();
        return BImgSor2;
    }

    public boolean getAttack() {
        return isAttack;
    }
    
    public void Sor2Health(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(BImgSor2Portrait, 0, 100, null);
        recHealth = new Rectangle(50, 115, nHealth, 20);
        g.setColor(Color.red);
        g2.fill(recHealth);
        g.setColor(Color.black);
        g2.draw(recHealth);
    }
    
    public int getHealth() {
        return nHealth;
    }
    
    public void setHealth(int health) {
        nHealth -= health;
    }

    public void Attack() {
        nAttack++;
        if (nAttack > 1200) {
            BImgSor2 = arBImgSor[2][1];
            isAttack = true;
            nAttack = 0;
        } else if (nAttack == 40) {
            if (isAttack == true) {
                BImgSor2 = arBImgSor[1][1];
                isAttack = false;
            }
        }
    }
    
    public Rectangle getBounds() {
        return new Rectangle(nSor2X, nSor2Y, BImgSor2.getWidth(), BImgSor2.getHeight());
    }
}