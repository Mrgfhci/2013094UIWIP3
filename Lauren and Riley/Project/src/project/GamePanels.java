package project;

import java.awt.BorderLayout;
import javax.swing.JPanel;
//import javax.swing.JPanel;

public class GamePanels extends JPanel {

    public GamePanels() {
        setLayout(new BorderLayout());       
        add(new PanStatus(), BorderLayout.NORTH);
        add(new PanInventory(), BorderLayout.SOUTH);
        add(new PanGrid(), BorderLayout.CENTER);
        setVisible(true);
       


    }
}
