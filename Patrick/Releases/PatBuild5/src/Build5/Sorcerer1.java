package Build5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sorcerer1 {

    private static int nSor1X, nSor1Y, nAttack = 1200, nHealth = 30;
    private static boolean isAttack = false;
    private BufferedImage BImgSor1, BImgSor1Portrait;
    private final static BufferedImage[][] arBImgSor = new BufferedImage[3][2];

    public Sorcerer1() throws IOException {
        arBImgSor[1][1] = ImageIO.read(new File("assets/sorcerer1.png"));
        arBImgSor[2][1] = ImageIO.read(new File("assets/sorcereratk1.png"));
        BImgSor1 = arBImgSor[1][1];
        BImgSor1Portrait = ImageIO.read(new File("assets/sorcerer1portrait.png"));
        nSor1X = 1180;
        nSor1Y = 505;
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
    
    public void Sor1Health(Graphics g){
        g.drawImage(BImgSor1Portrait, 0, 50, null);
        g.setColor(Color.red);
        g.fillRect(50, 65, nHealth, 20);
    }

    public void Attack() {
        nAttack++;
        if (nAttack > 1400) {
            BImgSor1 = arBImgSor[2][1];
            nSor1X -= 20;
            isAttack = true;
            nAttack = 0;
        } else if (nAttack == 40) {
            if (isAttack == true) {
                BImgSor1 = arBImgSor[1][1];
                nSor1X += 20;
                isAttack = false;
            }
        }
    }
}
