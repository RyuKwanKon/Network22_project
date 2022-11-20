package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Component.*;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;
import Lee.*;

public class Frame extends JFrame {
    public static JPanel gamePage = new GamePageView();
    public static JPanel LoginPage = new Login();
    public Frame() {
        setTitle("auction poker");
        setSize(1280, 720);
        Color background = new Color(249, 249, 249);

        Container contentPane = getContentPane();
        contentPane.setBackground(background);
        contentPane.setLayout(null);

        contentPane.add(gamePage);
        gamePage.setVisible(false);
        contentPane.add(LoginPage);

    }
}
