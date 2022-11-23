package Lee;

import ClientThread.ChatThread;
import ClientThread.ClientConnect;
import ClientThread.TimerThread;
import Component.*;
import GameData.ClientUserData;
import Page.GamePagePanel.ConnectUser;

import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Frame.Frame.*;
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
                    //대기화면
                    String response = client.getInMsg().readLine();
                    String[] splitMessage = response.split("/");
                    System.out.println(response);
                    if(splitMessage[0].equals("200")){
                        if(splitMessage[1].equals("gameStart"))
                            LoginPage.setVisible(false);
                            gamePage.setVisible(true);
                            gamePage.requestFocus();
                            for(int i = 0; i <= 3; i++){
                                connectUser.add(new ConnectUser("User: " + splitMessage[4 + i]));
                            }
                            userData.userName = splitMessage[2];
                            coin.setText(splitMessage[3]);
                            Thread timer = new TimerThread(client.getSocket());
                            timer.start();
                    }
                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
