/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author RILEY
 */
class PanOptions extends JPanel implements ActionListener {

    JButton btnSmall, btnLarge, btnReturn;
    JLabel lblSize, lblOptions;

    public PanOptions() {       
        lblOptions = new JLabel("Options");
        lblSize = new JLabel("");
        btnSmall = new JButton("Small");
        btnLarge = new JButton("Large");
        //btnReturn = new JButton("Back");
        //add(btnReturn);
        add(lblOptions);
        add(btnSmall);
        add(btnLarge);
        add(lblSize);
        btnSmall.addActionListener(this);
        btnLarge.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PanGrid1 panGrid = new PanGrid1();
        TitleMenu title = new TitleMenu();
        if (e.getSource() == btnSmall) {
            panGrid.nX = 10;
            panGrid.nY = 10;
            lblSize.setText("Small");
        }
        if (e.getSource() == btnLarge) {
            panGrid.nX = 25;
            panGrid.nY = 25;
            lblSize.setText("Large");
        }
        if (e.getSource() == btnReturn) {
        }

    }
}
