package Build7;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fireball2 {
    
    private int nFireball2X;
    private final int nFireball2Y;
    private final BufferedImage BImgFireball2 = ImageIO.read(new File("assets/fireball2.png"));
    private boolean visible = false;

    public Fireball2() throws IOException {
        nFireball2X = 115;
        nFireball2Y = 565;
    }

    public int getX() {
        return nFireball2X;
    }

    public int getY() {
        return nFireball2Y;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(nFireball2X, nFireball2Y, BImgFireball2.getWidth(), BImgFireball2.getHeight());
    }
    
    public BufferedImage getImage()  {
        if (nFireball2X < -30 || nFireball2X > 1250) {
            visible = false;
            nFireball2X = 125;
        }
        return BImgFireball2;
    }
    
    public void setVisible(boolean vis) {
        visible = vis;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public void setX(int x){
        nFireball2X = x;
    }
        
    public void move() {
        nFireball2X += 1;
    }
}
