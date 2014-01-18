package Build7;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Knight {

    private final int nKnightX, nKnightY;
    private int nHealth = 150, nAttack = 0, nBlock, n1 = 1, n2 = 1;
    private static boolean isAttack = false, bHeroAtk = false, isBlock = false;
    private BufferedImage BImgKnight;
    private final BufferedImage BImgKnightPortrait;
    private final static BufferedImage[][] arBImgKnight = new BufferedImage[3][3];
    private Rectangle recHealth;
    private final Hero hero = new Hero();
    private final static Timer tmrDelay = new Timer();
    private DelayTask delayTask;

    public Knight() throws IOException {
        arBImgKnight[1][1] = ImageIO.read(new File("assets/knightleft.png"));
        arBImgKnight[2][1] = ImageIO.read(new File("assets/knightleftattack.png"));
        arBImgKnight[2][2] = ImageIO.read(new File("assets/knightleftblock.png"));
        BImgKnight = arBImgKnight[n1][n2];
        BImgKnightPortrait = ImageIO.read(new File("assets/knightportrait.png"));
        nKnightX = 1000;
        nKnightY = 451;
    }

    public int getX() {
        return nKnightX;
    }

    public int getY() {
        return nKnightY;
    }

    public int getHealth() {
        return nHealth;
    }

    public BufferedImage getImage() {
        Action();
        BImgKnight = arBImgKnight[n1][n2];
        return BImgKnight;
    }

    public boolean getAttack() {
        return isAttack;
    }

    public boolean getBlock() {
        return isBlock;
    }

    public Rectangle getBounds() {
        if (n1 == 1 && n2 == 1) {
            return new Rectangle(nKnightX + 50, nKnightY, BImgKnight.getWidth() - 50, BImgKnight.getHeight());
        } else if (n1 == 2 && n2 == 1) {
            System.out.println(nKnightX);
            return new Rectangle(nKnightX, nKnightY, BImgKnight.getWidth(), BImgKnight.getHeight());
        } else {
            return new Rectangle(nKnightX + 58, nKnightY, BImgKnight.getWidth() - 50, BImgKnight.getHeight());
        }
    }

    public void setHealth(int health) {
        nHealth -= health;
    }
    
    public void setAttack(boolean b) {
        isAttack = b;
    }

    public void Knight1Health(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g.drawImage(BImgKnightPortrait, 0, 50, null);
        recHealth = new Rectangle(50, 65, nHealth, 20);
        g.setColor(Color.red);
        g2.fill(recHealth);
        g.setColor(Color.black);
        g2.draw(recHealth);
    }

    public void Action() {
        if (nAttack == 800) {
            n1 = 2;
            n2 = 1;
            isAttack = true;
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 2500);
            nAttack = 0;
        }
        if (hero.getWeak() || hero.getStrong() && isAttack == false) {
            bHeroAtk = true;
        }
        if (bHeroAtk == true ) {
            nBlock++;
            if (nBlock == 50) {
                isBlock = true;
                delayTask = new DelayTask();
                tmrDelay.schedule(delayTask, 0, 2000);
                n1 = n2 = 2;
                bHeroAtk = false;
                nBlock = 0;
            }
        }
        nAttack++;
    }

    class DelayTask extends TimerTask {

        public int nTimes = 0;

        @Override
        public void run() {
            nTimes++;
            if (nTimes == 2) {
                n1 = n2 = 1;
                isAttack = isBlock = false;
                hero.setPush(false);
            }
        }
    }
}
