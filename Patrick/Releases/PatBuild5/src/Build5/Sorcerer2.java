package Build5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sorcerer2 {

    private static int nSor2X, nSor2Y, nAttack = 0, nHealth = 30;
    private static boolean isAttack = false;
    public BufferedImage BImgSor2, BImgSor2Portrait;
    private final static BufferedImage[][] arBImgSor = new BufferedImage[3][2];

    public Sorcerer2() throws IOException {
        arBImgSor[1][1] = ImageIO.read(new File("assets/sorcerer2.png"));
        arBImgSor[2][1] = ImageIO.read(new File("assets/sorcereratk2.png"));
        BImgSor2 = arBImgSor[1][1];
        BImgSor2Portrait = ImageIO.read(new File("assets/sorcerer2portrait.png"));
        nSor2X = 30;
        nSor2Y = 505;
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
        g.drawImage(BImgSor2Portrait, 0, 100, null);
        g.setColor(Color.red);
        g.fillRect(50, 115, nHealth, 20);
    }

    public void Attack() {
        nAttack++;
        if (nAttack > 1400) {
            BImgSor2 = arBImgSor[2][1];
            nSor2X -= 16;
            isAttack = true;
            nAttack = 0;
        } else if (nAttack == 40) {
            if (isAttack == true) {
                BImgSor2 = arBImgSor[1][1];
                nSor2X += 16;
                isAttack = false;
            }
        }
    }
}