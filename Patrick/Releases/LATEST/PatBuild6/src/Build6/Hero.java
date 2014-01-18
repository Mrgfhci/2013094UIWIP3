package Build6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Graphics2D;
//This class loads all of the attributes 

public class Hero {

    private static int dx, nHeroX, nHeroY, nImgNum = 1, nDelay = 1, nHealth = 100, nSelect = 0;
    public static BufferedImage BImgHero, BImgHeroPortriat;
    private final static BufferedImage[][] arBImgHero = new BufferedImage[7][7];
    public static boolean isAction = false, isRight = true, isMoving = false,
            isBlocking = false, isStrong = false, isQuick = false;
    private boolean isHitRight, isHitLeft, pause = false;
    private static Timer tmrDelay = new Timer();
    private DelayTask delayTask;
    private Rectangle recHealth;
    private Main main = new Main();

    public Hero() throws IOException {
        //First bracket is for the state of the hero and the second is for the image
        //1 - right at rest, 2 left at rest, 3 right moving, 4 left moving,
        //5 action right, 6 action left
        arBImgHero[1][1] = ImageIO.read(new File("assets/heroright.png"));
        BImgHero = arBImgHero[1][1];
        arBImgHero[2][1] = ImageIO.read(new File("assets/heroleft.png"));
        for (int i = 1; i < arBImgHero.length; i++) {
            arBImgHero[3][i] = ImageIO.read(new File("assets/herorightwalk" + i + ".png"));
            arBImgHero[4][i] = ImageIO.read(new File("assets/heroleftwalk" + i + ".png"));
        }
        for (int i = 1; i < 4; i++) {
            arBImgHero[5][i] = ImageIO.read(new File("assets/herorightaction" + i + ".png"));
            arBImgHero[6][i] = ImageIO.read(new File("assets/heroleftaction" + i + ".png"));
        }
        BImgHeroPortriat = ImageIO.read(new File("assets/heroportrait.png"));
        isHitRight = isHitLeft = false;
        nHeroX = 580;
        nHeroY = 505;
    }

    public void move() {
        nHeroX += dx;
    }

    public int getX() {
        return nHeroX;
    }

    public int getY() {
        return nHeroY;
    }

    public BufferedImage getImage() {
        if (isMoving == true) {
            Count();
        }
        main.CardChange();
        return BImgHero;
    }

    public boolean getAttack() {
        return isAction;
    }

    public boolean getStrong() {
        return isStrong;
    }

    public boolean getQuick() {
        return isQuick;
    }

    public Rectangle getBounds() {
        return new Rectangle(nHeroX, nHeroY, BImgHero.getWidth(), BImgHero.getHeight());
    }

    public void HeroHealth(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Allows you to block attacks and if you're blocking the opposite way you'll get hurt
        if (isRight == true) {
            if (isHitRight == true) {
                if (isBlocking == false) {
                    nHealth -= 10;
                }
            } else if (isHitLeft == true) {
                nHealth -= 10;
            }
        } else if (isRight == false) {
            if (isHitRight == true) {
                nHealth -= 10;
            }
            if (isHitLeft == true) {
                if (isBlocking == false) {
                    nHealth -= 10;
                }
            }
        }
        isHitRight = isHitLeft = false;
        recHealth = new Rectangle(50, 15, nHealth, 20);
        g.drawImage(BImgHeroPortriat, 0, 0, null);
        g.setColor(Color.red);
        g2.fill(recHealth);
        g.setColor(Color.black);
        g2.draw(recHealth);
    }

    public void setHitRight(boolean Hit) {
        isHitRight = Hit;
    }

    public void setHitLeft(boolean Hit) {
        isHitLeft = Hit;
    }

    public int getHealth() {
        return nHealth;
    }

    public boolean getPause() {
        return pause;
    }
    
    public void setQuick(boolean quick) {
       isQuick = quick;
    }
    
    public void setStrong(boolean strong) {
        isStrong = strong;
    }

    private void Animation() {
        if (isRight == true) {
            BImgHero = arBImgHero[3][nImgNum];
        } else if (isRight == false) {
            BImgHero = arBImgHero[4][nImgNum];
        }
    }

    //This method is part of the animation.  It's the counter that cycles
    //through the animation images.
    private void Count() {
        nDelay++;
        if (nDelay >= 17) {
            nImgNum++;
            if (nImgNum >= 7) {
                nImgNum = 1;
            }
            nDelay = 1;
        }
        Animation();
    }

    private void Block() {
        if (isRight == true) {
            BImgHero = arBImgHero[5][1];
        } else {
            BImgHero = arBImgHero[6][1];
        }
    }

    private void Quick() {
        if (isRight == true) {
            BImgHero = arBImgHero[5][2];
        } else {
            BImgHero = arBImgHero[6][2];
        }
    }

    private void Strong() {
        if (isRight == true) {
            BImgHero = arBImgHero[5][3];
        } else {
            BImgHero = arBImgHero[6][3];
        }
    }

    private void Rest() {
        if (isRight == true) {
            BImgHero = arBImgHero[1][1];
        } else {
            BImgHero = arBImgHero[2][1];
        }
    }

    //http://zetcode.com/tutorials/javagamestutorial/movingsprites/
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (isAction == false) {
            if (key == KeyEvent.VK_LEFT) {
                dx = -1;
                if (nHeroX < -10) {
                    dx = 0;
                }
                isRight = false;
                isMoving = true;
            } else if (key == KeyEvent.VK_RIGHT) {
                dx = 1;
                if (nHeroX > 1200) {
                    dx = 0;
                }
                isRight = true;
                isMoving = true;
            } else if (key == KeyEvent.VK_C) {
                dx = 0;
                isAction = true;
                isMoving = false;
                isBlocking = true;
                Block();
            } else if (key == KeyEvent.VK_X) {
                dx = 0;
                isAction = true;
                isMoving = false;
                isQuick = true;
                Quick();
            } else if (key == KeyEvent.VK_Z) {
                dx = 0;
                isAction = true;
                isMoving = false;
                isStrong = true;
                Strong();
            }
            if (key == KeyEvent.VK_P) {
                pause = true;
                nSelect++;
                if (nSelect == 2) {
                    pause = false;
                    nSelect = 0;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            isMoving = false;
            Rest();
        } else if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            isMoving = false;
            Rest();
        } else if (key == KeyEvent.VK_C) {
            isAction = false;
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 500);
        } else if (key == KeyEvent.VK_X) {
            isAction = false;
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 300);
        } else if (key == KeyEvent.VK_Z) {
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 1200);
        }
        nImgNum = (int) (Math.random() * 6 + 1);
        nDelay = 0;


    }

    //http://www.javaprogrammingforums.com/java-se-api-tutorials/883-how-use-tmrDelay-java.html
    class DelayTask extends TimerTask {

        public int nTimes = 0;

        @Override
        public void run() {
            nTimes++;
            if (nTimes == 2) {
                isAction = false;
                isBlocking = false;
                //isStrong = false;
                //isQuick = false;
                Rest();
            }
        }
    }
}
