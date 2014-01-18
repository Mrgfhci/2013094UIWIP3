/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 *
 * @author densr1494
 */
class PanGrid extends JPanel {

    PanGrid1 panGrid = new PanGrid1();

    public PanGrid() {
        add(panGrid);
    }
}

class PanGrid1 extends JPanel {

    int nX = 10;
    int nY = 10;
    JButton[][] arsBtns = new JButton[nX][nY];

    public PanGrid1() {
        //GridBag Layout
        ActionGrid actionGrid = new ActionGrid();
        ImageIcon grass = new ImageIcon(getClass().getResource("Turf2.jpg"));
        int arsGrid[][] = new int[nX][nY];
        setLayout(new GridLayout(nX, nY));
        for (int x = 0; x < nX; x++) {
            for (int y = 0; y < nY; y++) {
                arsBtns[x][y] = new JButton(grass);
                arsBtns[x][y].setBorder(BorderFactory.createEmptyBorder());
                arsBtns[x][y].setContentAreaFilled(false);
                arsBtns[x][y].addActionListener(new ActionGrid());
                add(arsBtns[x][y]);




            }

        }

    }
}

class ActionGrid implements ActionListener {

    boolean isTree = false, isPond = false, isCave = false, isBush = false;

    public ActionGrid() {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ImageIcon tree = new ImageIcon(getClass().getResource("Turf3.jpg"));
        if (e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setIcon(tree);
        }
        System.out.println("Click");



    }
}
