package Build6;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fireball1 {

    private int nFireball1X, nFireball1Y;
    private final BufferedImage BImgFireball1 = ImageIO.read(new File("assets/fireball1.png"));
    private boolean visible = false;

    public Fireball1() throws IOException {
        nFireball1X = 1130;
        nFireball1Y = 565;
    }

    public int getX() {
        return nFireball1X;
    }

    public int getY() {
        return nFireball1Y;
    }

    public Rectangle getBounds() {
        return new Rectangle(nFireball1X, nFireball1Y, BImgFireball1.getWidth(), BImgFireball1.getHeight());
    }

    public BufferedImage getImage() {
        if (nFireball1X < -30 || nFireball1X > 1250) {
            visible = false;
            nFireball1X = 1110;
        }
        return BImgFireball1;
    }
    
    public void setVisible(boolean vis) {
        visible = vis;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public void setX(int x){
        nFireball1X = x;
    }
    
    public void move() {
        nFireball1X -= 1;
    }
}
