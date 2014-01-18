package Build5;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fireball1 {

    private static int nFireball1X, nFireball1Y;
    private final BufferedImage BImgFireball1 = ImageIO.read(new File("assets/fireball1.png"));
    private static BufferedImage BImgFireball1Final;
    private final Sorcerer1 sorcerer1;

    public Fireball1() throws IOException {
        sorcerer1 = new Sorcerer1();
        BImgFireball1Final = null;
        nFireball1X = 1110;
        nFireball1Y = 505;
    }

    public int getX() {
        return nFireball1X;
    }

    public int getY() {
        return nFireball1Y;
    }
    
    public BufferedImage getImage()  {
        if (sorcerer1.getAttack() == true){
            BImgFireball1Final = BImgFireball1; 
        }
        if (nFireball1X < -30 || nFireball1X > 1250) {
            BImgFireball1Final = null;
            nFireball1X = 1110;
        }
        return BImgFireball1Final;
    }
    
    public void move() {
        nFireball1X -= 1;
    }
}