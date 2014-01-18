package Zodiac_Fortune;
import javax.swing.JFrame;

import Zodiac_Fortune.PanAstro_n_Monster;

public class Zodiac_Main extends JFrame {
    
    public Zodiac_Main() {
        add(new PanAstro_n_Monster());
        setTitle("Zodiac Fortune");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(846, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Zodiac_Main();
    }
}
