package Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.ImageIcon;
import java.io.IOException;

public class Character {

    int x, dx, y, dy, nX, nX2, movingleft;
    Image Imgluffy;
    boolean isGrounded;
    private final Scanner gifload = new Scanner(new FileReader("gifload.txt"));
    private final String sluffyStandDown = gifload.nextLine(), sluffyStandup = gifload.nextLine(),
            sluffyStandRight = gifload.nextLine(), sluffyStandLeft = gifload.nextLine(),
            sluffyRunRight = gifload.nextLine(), sluffyRunLeft = gifload.nextLine();
    private ImageIcon standDown = new ImageIcon(sluffyStandDown), standUp = new ImageIcon(sluffyStandup),
            standRight = new ImageIcon(sluffyStandRight), standLeft = new ImageIcon(sluffyStandLeft),
           runRight = new ImageIcon(sluffyRunRight), runLeft = new ImageIcon(sluffyRunLeft);
    
    public Character() throws IOException {
        Imgluffy = standRight.getImage();
        movingleft = 250;
        nX2 = 685;
        nX = 0;
        x = 0;
        y = 400;
    }

    public void move() {
        if (dx != -1) {
            if (movingleft + dx <= 150) {
                movingleft = movingleft + dx;
            } else {
                nX = nX + dx;
                nX2 = nX2 + dx;
                x += dx;
            }
        } else {
            if (movingleft + dx > 0) {
                movingleft = movingleft + dx;
            }
        }
        y += dy;
        if (y == 400 && dy == 1) {
            dy = 0;
        }
        isGrounded = (y == 400);
    }

    public int getX() {
        return x;
    }
    public int getdx() {
        return dx;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return Imgluffy;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            Imgluffy = runLeft.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            Imgluffy = runRight.getImage();
        }
        if (key == KeyEvent.VK_SPACE) {
            if(isGrounded)
                dy = -1;
            else
                dy = 1;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            Imgluffy = standLeft.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            Imgluffy = standRight.getImage();
        }
        if (key == KeyEvent.VK_SPACE) {
            if(!isGrounded)
                dy = 1;
            else
                dy = 0;
        }
    }
}