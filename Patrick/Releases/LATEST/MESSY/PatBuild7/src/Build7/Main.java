package Build7;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import java.awt.FontFormatException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.File;

public class Main {

    public static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;
    private static final String sDeath = "Death", sGame = "Game",
            sWin = "Win", sKnight = "Knight";
    private static JFrame frame;
    private static Hero hero;
    private static JLabel lblDeath, lblWin;
    private final static JPanel cards = new JPanel(new CardLayout());
    private static JPanel panGame, panDeath, panWin, panKnight;
    private static CardLayout cs;

    //Card Layout - http://www.zentut.com/java-swing/java-swing-cardlayout/
    public static void main(String[] args) throws IOException, FontFormatException {
        frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Roman Fury");
        hero = new Hero();
        panGame = new PanGame();
        panDeath = new JPanel();
        panWin = new JPanel();
        panDeath.setLayout(new BorderLayout());
        panWin.setLayout(new BorderLayout());
        Font font = Font.createFont(Font.TRUETYPE_FONT, new File("CAESAR.ttf"));
        lblDeath = new JLabel("YOU HAVE DIED.");
        lblDeath.setFont(font.deriveFont(Font.BOLD, 154.0f));
        lblDeath.setForeground(Color.red);
        panDeath.add(lblDeath, BorderLayout.CENTER);
        panDeath.setBackground(Color.BLACK);
        lblWin = new JLabel("YOU HAVE DEFEATED THE HOSTIS, GLORY FOR ROME!");
        lblWin.setFont(font.deriveFont(Font.BOLD, 46.0f));
        lblWin.setForeground(Color.yellow);
        panWin.add(lblWin, BorderLayout.CENTER);
        panWin.setBackground(Color.red);
        cards.add(sGame, panGame);
        cards.add(sDeath, panDeath);
        cards.add(sWin, panWin);
        frame.add(cards);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        cs = (CardLayout) (cards.getLayout());
    }

    public void CardChange() {
        if (hero.getHealth() <= 0) {
            cs.show(cards, sDeath);
        }
    }

    public void Win(){
        cs.show(cards, sWin);
    }
}
