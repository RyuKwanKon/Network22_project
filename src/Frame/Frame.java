package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import ClientThread.ClientConnect;
import Component.*;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;
import Lee.*;

public class Frame extends JFrame {
    public static ClientConnect client;
    private String ip;
    private String id;
    public static JPanel gamePage = new GamePageView();
    public static JPanel LoginPage = new Login();
    public Frame() {
        setTitle("auction poker");
        setSize(1280, 720);
        Color background = new Color(249, 249, 249);

        Container contentPane = getContentPane();
        contentPane.setBackground(background);
        contentPane.setLayout(null);
        contentPane.add(LoginPage);
        contentPane.add(gamePage);
        gamePage.setVisible(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client = new ClientConnect();
        client.connectServer();
    }

    public static void main(String[] args) {
        JFrame main = new Frame();
        main.setVisible(true);
    }
}
