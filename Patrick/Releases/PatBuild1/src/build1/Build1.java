package build1;

import javax.swing.JFrame;

public class Build1 {

public static int FRAME_WIDTH = 1280, FRAME_HEIGHT = 720;

    public static void main(String[] args) {
       JFrame frame = new JFrame();
       frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
       PanMain panMain = new PanMain();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setLocationRelativeTo(null);
       frame.setTitle("Rome");
       frame.setResizable(false);
       frame.add(panMain);
       frame.setVisible(true);
    }
}