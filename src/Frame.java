import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Component.*;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;

public class Frame extends JFrame {
    Frame() {
        setTitle("auction poker");
        setSize(1280, 720);
        Color background = new Color(249, 249, 249);

        Container contentPane = getContentPane();
        contentPane.setBackground(background);
        contentPane.setLayout(null);

        contentPane.add(new GamePageView());
    }
}
