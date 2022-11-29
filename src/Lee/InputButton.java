package Lee;

import ClientThread.TimerThread;
import Component.*;
import GameData.ClientUserData;
import Page.GamePagePanel.ConnectUser;
import Frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Frame.MainFrame.*;
import static Page.GamePageView.*;

public class InputButton extends JButton{
    private ClientUserData userData;
    public InputButton(){
        userData = new ClientUserData();
        Color border = new Color(254, 132, 207);
        setBounds(540, 500, 200, 40);
        setForeground(border);
        setFont(FontStyle.BidFont);
        setText("Start");
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new CustomLineBorder(border, 2,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String userName = Login.loginInput.getText();
                    client.getOutMsg().println("userConnection/" + userName);
                    change();
                    Thread timer = new TimerThread(client);
                    timer.start();
//                    String response = client.getInMsg().readLine();
//                    String[] splitMessage = response.split("/");
//                    System.out.println(response);
//                    if(splitMessage[0].equals("200")){
//
//                    }
                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
                }
            }
        });
    }
    public void change(){
        LoginPage.setVisible(false);
        MainFrame.LoadingPage.setVisible(true);
//        MainFrame.LoadingPage = new Loding();
    }
}
