package Build3;

import java.awt.BorderLayout;
import java.io.IOException;
import javax.swing.JFrame;

public class Main {

public static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;

    public static void main(String[] args) throws IOException{
       JFrame frame = new JFrame();
       frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
       frame.setLayout(new BorderLayout());
       PanGame panGame = new PanGame();
       frame.setTitle("Rome");
       frame.setResizable(false);
       frame.add(panGame);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setVisible(true);
    }
}