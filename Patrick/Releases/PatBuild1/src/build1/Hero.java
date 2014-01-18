package build1;

import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Hero {

    private static int dx, x, y;
    private final String sHero = "assets/hero.png";
    public static Image HeroIMG;
    public Hero(){
        ImageIcon HeroIcon = new ImageIcon(this.getClass().getResource(sHero));
        HeroIMG = HeroIcon.getImage();
        x=50;
        y=455;
    }

    public void move() {
        x += dx;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public Image getImage() {
        return HeroIMG;
    }
//http://zetcode.com/tutorials/javagamestutorial/movingsprites/
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
