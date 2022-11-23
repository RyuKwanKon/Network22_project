package ClientThread;

import Lee.Login;
import Page.GamePagePanel.OnChat;
import Page.GamePagePanel.UserChat;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.Scanner;
import GameData.ClientUserData;

import static Frame.Frame.client;
import static Page.GamePagePanel.OnChat.input;
import static Page.GamePageView.currentChatting;
import static Page.GamePageView.timerNum;

public class TimerThread extends Thread{
    Socket socket = null;
    private ClientUserData userData;

    public TimerThread( Socket socket) {
        this.socket = socket;
        this.userData = new ClientUserData();
    }

    @Override
    public void run() {
        try {
            while(true) {
                String response = client.getInMsg().readLine();
                String[] splitMessage = response.split("/");
                if (splitMessage[0].equals("200")) {
                    if (splitMessage[1].equals("Timer")) {
                        timerNum.setText(splitMessage[2]);
                    }
                    if(splitMessage[1].equals("UserChat")){
                        System.out.println(response);
                        System.out.println(splitMessage[2]);
                        currentChatting.add(new UserChat(splitMessage[2], new Color(154, 254, 132)));
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
