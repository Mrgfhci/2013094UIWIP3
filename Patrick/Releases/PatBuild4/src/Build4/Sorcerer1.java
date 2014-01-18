package Build4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Sorcerer1 {

    private static int nSor1X, nSor1Y, nAttack = 1200;
    private static boolean isAttack = false;
    public BufferedImage BImgSor1;
    private final static BufferedImage[][] arBImgSor = new BufferedImage[3][2];

    public Sorcerer1() throws IOException {
        arBImgSor[1][1] = ImageIO.read(new File("assets/sorcerer1.png"));
        arBImgSor[2][1] = ImageIO.read(new File("assets/sorcereratk1.png"));
        BImgSor1 = arBImgSor[1][1];
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
