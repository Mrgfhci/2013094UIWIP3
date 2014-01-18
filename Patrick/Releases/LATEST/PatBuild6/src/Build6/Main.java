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
    private static JFrame frame;
    private static Hero hero;
    private static PanCard1 panCard1;
    private static JLabel lblDeath;
    private static JPanel cards = new JPanel(new CardLayout()), panCard5;

    //Card Layout - http://www.zentut.com/java-swing/java-swing-cardlayout/
    public static void main(String[] args) throws IOException {
        frame = new JFrame();
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);
        frame.setTitle("Roman Fury");
        hero = new Hero();
        panCard1 = new PanCard1();
        panCard5 = new JPanel();
        panCard5.setLayout(new BorderLayout());
        lblDeath = new JLabel("YOU HAVE DIED.");
        lblDeath.setFont(lblDeath.getFont().deriveFont(155.0f).deriveFont(Font.BOLD));
        lblDeath.setForeground(Color.red);
        panCard5.setBackground(Color.BLACK);
        panCard5.add(lblDeath, BorderLayout.CENTER);
        cards.add(panCard1);
        cards.add(panCard5);
        frame.add(cards);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void CardChange() {
        CardLayout cs = (CardLayout) (cards.getLayout());
        if (hero.getHealth() <= 0) {
            cs.next(cards);
        }
    }
}
