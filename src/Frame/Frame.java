package Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Component.*;
import Page.GamePagePanel.GamePage;
import Page.GamePageView;
import Lee.*;

public class Frame extends JFrame {
    private String ip;
    private String id;
    private Socket socket;
    private BufferedReader inMsg;
    public static PrintWriter outMsg;
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        connectServer();
    }

    public void connectServer(){
        try{
            socket = new Socket("127.0.0.1", 1111);
            System.out.println("[Client]Server 연결 성공!!");
            inMsg = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            outMsg = new PrintWriter(socket.getOutputStream(), true);
        }catch (Exception e){

        }
    }

    public static void main(String[] args) {
        JFrame main = new Frame();
        main.setVisible(true);
    }
}
