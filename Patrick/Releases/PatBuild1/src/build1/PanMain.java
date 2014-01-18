package build1;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class PanMain extends JPanel {

    PanGame panGame = new PanGame();

    public PanMain() {
        setLayout(new BorderLayout());
        add(panGame, BorderLayout.CENTER);
    }
}
