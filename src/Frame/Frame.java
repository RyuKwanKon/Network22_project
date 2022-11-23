package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Component.*;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;
import Lee.*;
import ClientThread.ClientConnect;

public class Frame extends JFrame {
    public static ClientConnect client;
    private String ip;
    private String id;
    public static JPanel gamePage = new GamePageView();
    public static JPanel LoginPage = new Login();
    public static JPanel LodingPage = new Loding();

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
        contentPane.add(LodingPage);


    /*public static void main(String[] args) {
        JFrame main = new Frame();
        main.setVisible(true);
    }*/
    }
}
