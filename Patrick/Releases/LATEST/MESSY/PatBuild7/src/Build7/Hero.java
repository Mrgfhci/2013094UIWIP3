package Build7;

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
//This class loads all of the attributes for the hero

public class Hero {

    private static int dx, nHeroX, nHeroY, nImgNum = 1, nDelay = 1, nHealth = 100, n1, n2;
    public static BufferedImage BImgHero, BImgHeroPortriat;
    private final static BufferedImage[][] arBImgHero = new BufferedImage[7][7];
    public static boolean isAction = false, isRight = true, isMoving = false,
            isBlock = false, isStrong = false, isWeak = false;
    private boolean isHitRight, isHitLeft, isHit, isPush = false, pause = false;
    private final static Timer tmrDelay = new Timer();
    private DelayTask delayTask;
    private Rectangle recHealth;
    private final Main main = new Main();
    //private final Knight knight = new Knight();

    public Hero() throws IOException {
        //First bracket is for the state of the hero and the second is for the image
        //1 - right at rest, 2 left at rest, 3 right moving, 4 left moving,
        //5 action right, 6 action left
        n1 = n2 = 1;
        arBImgHero[1][1] = ImageIO.read(new File("assets/heroright.png"));
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

    public int getX() {
        Push();
        return nHeroX;
    }

    public int getY() {
        return nHeroY;
    }

    public int getHealth() {
        return nHealth;
    }

    public boolean getAction() {
        return isAction;
    }

    public boolean getStrong() {
        return isStrong;
    }

    public boolean getWeak() {
        return isWeak;
    }

    public boolean getPause() {
        return pause;
    }

    public boolean getRight() {
        return isRight;
    }

    public boolean getBlock() {
        return isBlock;
    }

    public boolean getPush() {
        return isPush;
    }

    public BufferedImage getImage() {
        if (isMoving == true) {
            Count();
        }
        if (isAction == true) {
            n1 = 5;
        }
        main.CardChange();
        if (isRight == true) {
            BImgHero = arBImgHero[n1][n2];
        } else {
            BImgHero = arBImgHero[n1 + 1][n2];
        }
        return BImgHero;
    }

    public Rectangle getBounds() {
        if (isRight == true && isAction == false) {
            return new Rectangle(nHeroX + 28, nHeroY, BImgHero.getWidth() - 70, BImgHero.getHeight());
        } else if (isRight == false && isAction == false) {
            return new Rectangle(nHeroX + 42, nHeroY, BImgHero.getWidth() - 73, BImgHero.getHeight());
        } else if (isRight == true && isBlock == true) {
            return new Rectangle(nHeroX + 35, nHeroY, BImgHero.getWidth() - 59, BImgHero.getHeight());
        } else if (isRight == false && isBlock == true) {
            return new Rectangle(nHeroX + 22, nHeroY, BImgHero.getWidth() - 58, BImgHero.getHeight());
        } else {
            return new Rectangle(nHeroX, nHeroY, BImgHero.getWidth(), BImgHero.getHeight());
        }
    }

    public void setX(int x) {
        nHeroX = x;
    }

    public void setRight(boolean b) {
        isRight = b;
    }

    public void setHitRight(boolean Hit) {
        isHitRight = Hit;
    }

    public void setHitLeft(boolean Hit) {
        isHitLeft = Hit;
    }

    public void setHit(boolean Hit) {
        isHit = Hit;
    }

    public void setWeak(boolean quick) {
        isWeak = quick;
    }

    public void setStrong(boolean strong) {
        isStrong = strong;
    }

    public void setPush(boolean push) {
        isPush = push;
    }

    public void move() {
        nHeroX += dx;
    }

    public void HeroHealth(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        //Allows you to block attacks and if you're blocking the opposite way you'll get hurt
        if (isRight == true) {
            if (isHitRight == true) {
                if (isBlock == false) {
                    nHealth -= 10;
                }
            }
            if (isHitLeft == true) {
                nHealth -= 10;
            } else if (isHit == true) {
                if (isBlock == false) {
                    nHealth -= 20;
                }
                isPush = true;
            }
        } else if (isRight == false) {
            if (isHitRight == true) {
                nHealth -= 10;
            }
            if (isHitLeft == true && isBlock == false) {
                nHealth -= 10;
            } else if (isHit == true) {
                nHealth -= 20;
                isPush = true;
            }
        }
        isHitRight = isHitLeft = isHit = false;
        recHealth = new Rectangle(50, 15, nHealth, 20);
        g.drawImage(BImgHeroPortriat, 0, 0, null);
        g.setColor(Color.red);
        g2.fill(recHealth);
        g.setColor(Color.black);
        g2.draw(recHealth);
    }

    private void Animation() {
        n1 = 3;
        n2 = nImgNum;
    }

    //This method is part of the animation.  It's the counter that cycles
    //through the animation images.
    private void Count() {
        nDelay++;
        if (nDelay >= 21) {
            nImgNum++;
            if (nImgNum >= 7) {
                nImgNum = 1;
            }
            nDelay = 1;
        }
        Animation();
    }
    
    private void Push() {
        if (isPush == true && nHeroX > 920) {
            nHeroX -= 1;
        } else {
            isPush = false;
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
                isBlock = true;
                n2 = 1;
            } else if (key == KeyEvent.VK_X) {
                dx = 0;
                isAction = true;
                isMoving = false;
                isWeak = true;
                n2 = 2;
            } else if (key == KeyEvent.VK_Z) {
                dx = 0;
                isAction = true;
                isMoving = false;
                isStrong = true;
                n2 = 3;
            } else if (key == KeyEvent.VK_P) {
                if (pause == false) {
                    pause = true;
                } else if (pause == true) {
                    pause = false;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
            isMoving = false;
            n1 = n2 = 1;
        } else if (key == KeyEvent.VK_C) {
            isAction = false;
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 500);
        } else if (key == KeyEvent.VK_X) {
            isAction = false;
            delayTask = new DelayTask();
            tmrDelay.schedule(delayTask, 0, 450);
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
                isAction = isBlock = isStrong = isWeak = false;
                n1 = n2 = 1;
            }
        }
    }
}
