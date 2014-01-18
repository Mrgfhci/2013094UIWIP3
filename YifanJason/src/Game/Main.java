package Game;

import javax.swing.*;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) throws IOException{
        JFrame frame = new JFrame();
        frame.add(new PanBoard());
        frame.setTitle("2-D Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,636);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}