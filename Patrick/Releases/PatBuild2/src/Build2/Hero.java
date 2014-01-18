package build2;

import java.awt.event.KeyEvent;
import java.awt.Image;
import javax.swing.ImageIcon;
import java.io.IOException;
import java.io.FileReader;
import java.util.Scanner;

public class Hero {

    public static int dx, x, y, nCounter1 = 0, nCounter2 = 4;
    private final String sHeroRight = "assets/heroright.png", sHeroLeft = "assets/heroleft.png";
    public static Image HeroImg;
    private final ImageIcon HeroRightIcon = new ImageIcon(this.getClass().getResource(sHeroRight)),
            HeroLeftIcon = new ImageIcon(this.getClass().getResource(sHeroLeft));
    private String[] sHeroRights = new String[6], sHeroLefts = new String[6];
    private static Image[] RightHeroes = new Image[6], LeftHeroes = new Image[6];
    private static ImageIcon[] RightHeroIcons = new ImageIcon[6], LeftHeroIcons = new ImageIcon[6];

    public Hero() throws IOException {

        HeroImg = HeroRightIcon.getImage();
        Scanner HeroRightLoad = new Scanner(new FileReader("HeroRightLoad.txt")),
                HeroLeftLoad = new Scanner(new FileReader("HeroLeftLoad.txt"));
        for (int i = 0; i < RightHeroes.length; i++) {
            sHeroRights[i] = HeroRightLoad.nextLine();
            sHeroLefts[i] = HeroLeftLoad.nextLine();
            RightHeroIcons[i] = new ImageIcon(this.getClass().getResource(sHeroRights[i]));
            LeftHeroIcons[i] = new ImageIcon(this.getClass().getResource(sHeroLefts[i]));
            RightHeroes[i] = RightHeroIcons[i].getImage();
            LeftHeroes[i] = LeftHeroIcons[i].getImage();
        }
        x = 500;
        y = 505;
    }

    public int getCounter() {
        nCounter1++;
        if (nCounter1 == 6) {
            nCounter1 = 0;
        }
        return nCounter1;
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
        return HeroImg;
    }

    public Image LeftImage() {
        HeroImg = HeroLeftIcon.getImage();
        return HeroImg;
    }

    public Image RightImage() {
        HeroImg = HeroRightIcon.getImage();
        return HeroImg;
    }

    public void rightAnimation() {
        HeroImg = RightHeroes[nCounter1];
    }

    public void leftAnimation() {
        HeroImg = LeftHeroes[nCounter1];
    }

    public void Count() {
        nCounter2++;
        if (nCounter2 >= 5) {
            nCounter1++;
            if (nCounter1 == 6) {
                nCounter1 = 0;
            }
            nCounter2 = 0;
        }
    }
    //http://zetcode.com/tutorials/javagamestutorial/movingsprites/
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode(); 
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = -1;
            Count();
            leftAnimation();

        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 1;
            Count();
            rightAnimation();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
            dx = 0;
            nCounter1 = 0;
            LeftImage();
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
            dx = 0;
            nCounter1 = 0;
            RightImage();
        }
    }
}