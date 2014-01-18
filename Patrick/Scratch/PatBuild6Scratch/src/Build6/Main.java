package Build6;

import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Main {

    public static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720, nSelect = 0;
    private static final String sDeath = "Death", sSorcerer = "Sorcerer",
            sWin = "Win";
    private static JFrame frame;
    private static Hero hero;
    private static Sorcerer1 sor1;
    private static Sorcerer2 sor2;
    private static JLabel lblDeath;
    private final static JPanel cards = new JPanel(new CardLayout());
    private static JPanel panSorcerer, panDeath, panWin;
    private static CardLayout cs;

    //Card Layout - http://www.zentut.com/java-swing/java-swing-cardlayout/
    public static void main(String[] args) throws IOException {
        frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Roman Fury");
        hero = new Hero();
        sor1 = new Sorcerer1();
        sor2 = new Sorcerer2();
        panSorcerer = new PanSorcerer();
        panDeath = new JPanel();
        panWin = new JPanel();
        panDeath.setLayout(new BorderLayout());
        lblDeath = new JLabel("YOU HAVE DIED.");
        lblDeath.setFont(lblDeath.getFont().deriveFont(155.0f).deriveFont(Font.BOLD));
        lblDeath.setForeground(Color.red);
        panDeath.setBackground(Color.BLACK);
        panDeath.add(lblDeath, BorderLayout.CENTER);
        cards.add(sSorcerer, panSorcerer);
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
        if (sor1.getHealth() == 0 && sor2.getHealth() == 0) {
            cs.show(cards, sWin);
        }
    }
}
