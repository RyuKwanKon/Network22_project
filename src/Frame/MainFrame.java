package Frame;

import javax.swing.*;
import java.awt.*;

import Page.GamePageView;
import Lee.*;
import ClientThread.ClientConnect;

public class MainFrame extends JFrame {
    public static ClientConnect client;
    public static JPanel gamePage = new GamePageView();
    public static JPanel LoginPage = new Login();
    public static JPanel LoadingPage = new Loding();
    public MainFrame() {
        setTitle("Auction poker");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Color background = new Color(249, 249, 249);

        Container contentPane = getContentPane();
        contentPane.setBackground(background);
        contentPane.setLayout(null);

        contentPane.add(LoginPage);
        LoginPage.setVisible(true);
        contentPane.add(LoadingPage);
        LoadingPage.setVisible(false);
        contentPane.add(gamePage);
        gamePage.setVisible(false);

        client = new ClientConnect();
        client.connectServer();
    }

}
