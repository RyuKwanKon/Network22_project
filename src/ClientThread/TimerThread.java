package ClientThread;

import GameData.UserData;
import Lee.Login;
import Page.GamePagePanel.OnChat;
import Page.GamePagePanel.UserChat;

import javax.swing.*;
import java.awt.*;
import java.net.Socket;
import java.util.Scanner;
import GameData.ClientUserData;

import static Frame.Frame.client;
import static GameData.UserData.GameBidCoin;
import static Page.GamePagePanel.OnChat.input;
import static Page.GamePageView.*;

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
                    else if(splitMessage[1].equals("UserChat")){
                        System.out.println(response);
                        System.out.println(splitMessage[2]);
                        currentChatting.add(new UserChat(splitMessage[2], new Color(154, 254, 132)));
                    }
                    else if(splitMessage[1].equals("RegisterBid")){
                   //     alarm.setText("상품 " + userData.currentCard +" - 현재 금액: " + splitMessage[2] + "님 " + splitMessage[3] + "원"); // 상품, 누가 입찰했는지, 가격
                        alarm.setText("상품 " + ClientUserData.currentCard + " - 현재 금액: " + splitMessage[3] + "원"); // 상품 + 가격만, 입찰은 비밀
                    }
                    else if(splitMessage[1].equals("CurrentCard")){
                        ClientUserData.currentCard = splitMessage[2];
                    }
                    else if(splitMessage[1].equals("WinBidding")){
                        ClientUserData.coin = Integer.parseInt(splitMessage[2]);
                    }
                    else if(splitMessage[1].equals("Income")){
                        ClientUserData.coin += Integer.parseInt(splitMessage[2]);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
