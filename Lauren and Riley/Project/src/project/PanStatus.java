/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author densr1494
 */
class PanStatus extends JPanel {
    ImageIcon tree = new ImageIcon(getClass().getResource("tree.jpg"));
    int nCount = 0;
    JLabel lblCells = new JLabel("0");
    JLabel lblTime = new JLabel("0:00");
    JLabel lblIcon  = new JLabel(tree);
    JLabel lblCount = new JLabel();

    public PanStatus() {
        
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 4;
        c.ipady = 10;
        c.ipadx = 10;
        add(lblCells, c);
        add(lblTime, c);
        add(lblIcon, c);
        add(lblCount, c);
        Timer timer = new Timer(1000, null);
        timer.addActionListener(new ActionTimer());
        timer.start();
    }

    class ActionTimer implements ActionListener {

        int nCount1 = 0;
        int nCount2 = 0;
        int nCount3 = 0;

        public ActionTimer() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String sCount1, sCount2, sCount3;
            nCount1 += 1;
            if (nCount1 == 10) {
                nCount1 = 0;
                nCount2 += 1;
            } else if (nCount2 == 10) {
                nCount2 = 0;
                nCount3 += 1;
            }
            sCount1 = Integer.toString(nCount1);
            sCount2 = Integer.toString(nCount2);
            sCount3 = Integer.toString(nCount3);
            lblTime.setText(sCount3 + ":" + sCount2 + "" + sCount1);
            //System.out.println(nCount1);
            revalidate();

        }
    }
}
