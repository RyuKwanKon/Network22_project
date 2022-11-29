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
                    String response = client.getInMsg().readLine();
                    String[] splitMessage = response.split("/");
                    System.out.println(response);
                    if(splitMessage[0].equals("200")){
                        if(splitMessage[1].equals("gameStart")) {
                            MainFrame.LoadingPage.setVisible(false);
                            MainFrame.gamePage.setVisible(true);
                            MainFrame.gamePage.requestFocus();
                            for (int i = 0; i <= 3; i++) {
                                connectUser.add(new ConnectUser("User: " + splitMessage[5 + i]));
                            }
                            userData.userName = splitMessage[3];
                            coin.setText(splitMessage[4]);
                            Thread timer = new TimerThread(client.getSocket());
                            timer.start();
                        }
                    }
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
