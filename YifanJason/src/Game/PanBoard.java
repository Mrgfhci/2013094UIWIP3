package Game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;

public class PanBoard extends JPanel implements ActionListener{

    Character luffy;
    public Image Background;
    Timer time;
    Enemies Franky;
    Enemies Franky2;
    public PanBoard() throws IOException{
        luffy = new Character();
        String sBack = "background.jpg";
        addKeyListener(new PanBoard.AL());
        setFocusable(true);
        ImageIcon background = new ImageIcon(sBack);
        Background = background.getImage();
        time = new Timer(5, this);
        time.start();
        Franky = new Enemies(500,380);
        Franky2 = new Enemies(700,380);
    }

    public void actionPerformed(ActionEvent e) {
        luffy.move();
        if(luffy.x >400)
            Franky.move(luffy.getdx());
        if(luffy.x > 500)
            Franky2.move(luffy.getdx());
        repaint();
    }

    public void paint(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        if ((luffy.getX() - 210) % 2400 == 0) {
            luffy.nX = 0;
        }
        if ((luffy.getX() - 1110) % 2400 == 0) {
            luffy.nX2 = 0;
        }
        //using the module method to repaint the background. Based on the size of the background.
        g2d.drawImage(Background, 685 - luffy.nX2, 0, null);
        if (luffy.getX() >= 210) {
            g2d.drawImage(Background, 685 - luffy.nX, 0, null);
        }
        g2d.drawImage(luffy.getImage(), luffy.movingleft, luffy.getY(), null);
        //Drawing the character and also making the character not able to move beyond the left edge of the screen.
        if(luffy.x >400)
            if(Franky.isAlive == true)
                g2d.drawImage(Franky.getImage(), Franky.getEnemyX(),Franky.getEnemyY(), null);
        if(luffy.x > 500 )
            if(Franky2.isAlive == true)
                g2d.drawImage(Franky2.getImage(), Franky2.getEnemyX(),Franky2.getEnemyY(), null);
        //Drawing the enemies.
    }

    

    private class AL extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            luffy.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            luffy.keyPressed(e);
        }
    }
}