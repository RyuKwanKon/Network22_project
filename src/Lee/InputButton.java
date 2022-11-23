package Lee;

import ClientThread.ChatThread;
import ClientThread.ClientConnect;
import Component.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import Frame.Frame.*;
import SW.*;

import static Frame.Frame.*;
import static Page.GamePagePanel.OnChat.input;
import static Page.GamePageView.currentChatting;
import SW.*;

public class InputButton extends JButton{
    public InputButton(){
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
                    //SW.ServetThread.beforeGS(count);
                    String userName = Login.loginInput.getText();
                    client.getOutMsg().println("userConnection/" + userName);
                    LodingPage.setVisible(true);
                    LoginPage.setVisible(false);
                    String response = client.getInMsg().readLine();
                    String[] splitMessage = response.split("/");
                    System.out.println(response);
                    if(splitMessage[0].equals("200")){
                        if(splitMessage[1].equals("gameStart")){
                            LodingPage.setVisible(false);
                            gamePage.setVisible(true);
                            gamePage.requestFocus();
                        }
                    }
                } catch (Exception ex) {
//                    throw new RuntimeException(ex);
                }
            }
        });
    }
}