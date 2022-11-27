package ClientThread;

import GameData.UserData;
import Lee.Login;
import Page.GamePagePanel.OnChat;
import Page.GamePagePanel.UserChat;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.Socket;
import java.util.Scanner;
import GameData.ClientUserData;
import Page.GamePageView;

import static Component.Data.cardInfo;
import static Frame.Frame.client;
import static GameData.UserData.currentDack;
import static GameData.UserData.remainCard;
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
                System.out.println(response);
                String[] splitMessage = response.split("/");
                if (splitMessage[0].equals("200")) {
                    if (splitMessage[1].equals("Timer")) {
                        timerNum.setText(splitMessage[2]);
                    }
                    //200/UserChat/Chat/
                    //200/UserChat/Server/
                    else if(splitMessage[1].equals("UserChat")){
                        if(splitMessage[2].equals("Server"))  currentChatting.add(new UserChat(splitMessage[3], new Color(154, 254, 132)));
                        else currentChatting.add(new UserChat(splitMessage[3], new Color(132, 167, 254)));
                    }
                    //알람
                    //200/RegisterBid/userName/price
                    else if(splitMessage[1].equals("RegisterBid")){
                   //     alarm.setText("상품 " + userData.currentCard +" - 현재 금액: " + splitMessage[2] + "님 " + splitMessage[3] + "원"); // 상품, 누가 입찰했는지, 가격
                        userData.userBid = Integer.parseInt(splitMessage[3]);
                        System.out.println(userData.userBid);
                        alarm.setText("상품 " + userData.currentCard + " - 현재 금액: " + userData.userBid + "원"); // 상품 + 가격만, 입찰은 비밀
                    }
                    //새로운 카드
                    //200/currentCard/카드종류
                    else if(splitMessage[1].equals("CurrentCard")){
                        int title = (int)splitMessage[2].charAt(0);
                        String number = splitMessage[2].substring(1);
                        String current = cardInfo[title- 65] + number;
                        System.out.println(current);
                        userData.currentCard = current;
                        BufferedImage tempImg = ImageIO.read(new File("./assets/Card/" + splitMessage[2] + ".png"));
                        ImageIcon temp_img = new ImageIcon(tempImg);
                        Image t = temp_img.getImage().getScaledInstance(141, 200, Image.SCALE_SMOOTH);
                        CenterCard.setIcon(new ImageIcon(t));
                        alarm.setText("상품 " + current + " - 현재 금액: 0원"); // 상품 + 가격만, 입찰은 비밀
                    }
                    //낙찰 받은 유저 200/EndRound/WinBidding/userName/currentCoin/카드
                    //유찰 받은 유저 또는 경매에 응시 안했을 경우 200/EndRound/NoBidding/currentCoin/카드
                    else if(splitMessage[1].equals("EndRound")){
                        int title = (int)splitMessage[5].charAt(0);
                        int number = Integer.parseInt(splitMessage[5].substring(1));
                        String current = cardInfo[title- 65] + String.valueOf(number);
                        remainCard[(title - 65)*13 + (number - 1)] = 1;
                        GamePageView.card[(title - 65)*13 + (number - 1)].setVisible(false);
                        userData.coin = Integer.parseInt(splitMessage[4]);
                        coin.setText(String.valueOf(userData.coin));
                        userData.userBid = 0;
                        if(splitMessage[2].equals("WinBidding")){
                            System.out.println("hi");
                            currentDack[(title - 65)*13 + (number - 1)] = 1;
                            GamePageView.cardDack[(title - 65)*13 + (number - 1)].setVisible(true);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
