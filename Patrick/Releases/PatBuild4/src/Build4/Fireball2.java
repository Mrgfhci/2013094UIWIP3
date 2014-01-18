package Build4;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fireball2 {
    private static int nFireball2X, nFireball2Y;
    private final BufferedImage BImgFireball2 = ImageIO.read(new File("assets/fireball2.png"));
    private static BufferedImage BImgFireball2Final;
    private final Sorcerer2 sorcerer2;

    public Fireball2() throws IOException {
        sorcerer2 = new Sorcerer2();
        BImgFireball2Final = null;
        nFireball2X = 50;
        nFireball2Y = 505;
    }

    public int getX() {
        if (BImgFireball2Final == null){
            nFireball2X = 50;
        }
        return nFireball2X;
    }

    public int getY() {
        return nFireball2Y;
    }
    
    public BufferedImage getImage()  {
        if (sorcerer2.getAttack() == true){
            BImgFireball2Final = BImgFireball2; 
        }
        if (nFireball2X < -30 || nFireball2X > 1250) {
            BImgFireball2Final = null;
            nFireball2X = 50;
        }
        return BImgFireball2Final;
    }
    
    public void move() {
        nFireball2X += 1;
    }
}
