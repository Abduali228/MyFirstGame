package com.company;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        JFrame frame = new JFrame();
        PanelOfGame panel = new PanelOfGame();

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("SnakeFromTeamAbu");
        frame.setLocationRelativeTo(null);


        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
	new Main();
    }
}
