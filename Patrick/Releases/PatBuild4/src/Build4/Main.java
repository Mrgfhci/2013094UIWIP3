package Build4;

import java.awt.CardLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Main {

public static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;
    //Card Layout - http://www.zentut.com/java-swing/java-swing-cardlayout/
    public static void main(String[] args) throws IOException{
       JFrame frame = new JFrame();
       frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
       frame.setLayout(new CardLayout());
       PanCard1 panCard1 = new PanCard1();
       frame.setTitle("Roman Fury");
       frame.setResizable(false);
       frame.add(panCard1);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
}