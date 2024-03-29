
package project;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class TitleMenu extends JFrame implements ActionListener {

    JPanel panMaster;
    JButton btnStart;
    JButton btnOptions;
    
    public TitleMenu() {
        setSize(500, 500);
        setLocationRelativeTo(null);
        setTitle("Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setMinimumSize(new Dimension(500, 500));
        btnStart = new JButton("Start Game");
        btnOptions = new JButton("Options");
        btnStart.addActionListener(this);
        btnOptions.addActionListener(this);
        JPanel gamePanels = new GamePanels();
        JPanel panMenu = new JPanel();
        JPanel panOption = new PanOptions();
        panMenu.add(btnStart);
        panMenu.add(btnOptions);
        panMaster = new JPanel(new CardLayout());
        panMaster.add(panMenu,"Menu");
        panMaster.add(gamePanels,"Game");
        panMaster.add(panOption,"Options");
        add(panMaster);                   
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        CardLayout cl = (CardLayout)panMaster.getLayout();
        if(e.getSource() == btnStart){
        cl.next(panMaster);  
        }
        if(e.getSource() == btnOptions){
        cl.last(panMaster);
        }
    }

    public static void main(String args[]) {
        TitleMenu titleMenu = new TitleMenu();
    }
}
