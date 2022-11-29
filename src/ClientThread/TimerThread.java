package ClientThread;

import Page.GamePagePanel.ConnectUser;
import Page.GamePagePanel.UserChat;

import java.awt.*;

import GameData.ClientUserData;
import Page.GamePageView;

import static Frame.MainFrame.*;
import static Component.Data.cardInfo;
import static GameData.ClientUserData.changeCard;
import static GameData.ClientUserData.currentCard;
import static GameData.UserData.*;
import static Page.GamePagePanel.ScrollChatting.vertical;
import static Page.GamePageView.*;

public class TimerThread extends Thread{
    private boolean finish = false;
    ClientConnect connect = null;
    private ClientUserData userData;
    public TimerThread(ClientConnect connect) {
        this.connect = connect;
        this.userData = new ClientUserData();
    }
    @Override
    public void run() {
        try {
            while(true) {
                if(finish){
                    System.out.println("end");
                    connect.endSocket();
                    return;
                }
                String response = client.getInMsg().readLine();
                System.out.println(response);
                String[] splitMessage = response.split("/");
                if (splitMessage[0].equals("200")) {
                    if (splitMessage[1].equals("Timer")) {
                        timer(splitMessage[2]);
                    }
                    if(splitMessage[1].equals("gameStart")) {
                        LoadingPage.setVisible(false);
                        gamePage.setVisible(true);
                        gamePage.requestFocus();
                        for (int i = 0; i <= 3; i++) {
                            userData.userList.add(splitMessage[5 + i]);
                            connectUser.add(new ConnectUser("User: " + splitMessage[5 + i]));
                        }
                        userData.userName = splitMessage[3];
                        userData.coin = 100;
                        coin.setText(splitMessage[4]);
                    }
                    //200/UserChat/Chat/
                    //200/UserChat/Server/
                    else if(splitMessage[1].equals("UserChat")){
                        userChat(splitMessage[2], splitMessage[3]);
                    }
                    //알람
                    //200/RegisterBid/userName/price
                    else if(splitMessage[1].equals("RegisterBid")){
                        registerBid(splitMessage[3]);
                    }
                    //새로운 카드
                    //200/currentCard/카드종류
                    else if(splitMessage[1].equals("CurrentCard")){
                        currentCard(splitMessage[2]);
                    }
                    //낙찰 받은 유저 200/EndRound/WinBidding/userName/currentCoin/카드
                    //유찰 받은 유저 또는 경매에 응시 안했을 경우 200/EndRound/NoBidding/currentCoin/카드
                    //실패 했을 경우 200/EndRound/NoneBidding/userName/currentCoin/카드
                    else if(splitMessage[1].equals("EndRound")){
                        endRound(splitMessage[2], splitMessage[4], splitMessage[5]);
                    }
                }else if(splitMessage[0].equals("300")){
                    if(splitMessage[1].equals("Disconnect")){
                        disconnect(splitMessage[2]);
                    }
                }
            }
        } catch (Exception e) {}
    }
    synchronized public void timer(String currentTimer){
        if(currentTimer.equals("0")){
            button.setEnabled(false);
            button.setText("Wait");
        }else if(!currentCard.equals("wait")){
            button.setEnabled(true);
            button.setText("입찰 +5");
        }
        timerNum.setText(currentTimer);
    }
    synchronized public void userChat(String typeMessage, String messageContent){
        if(typeMessage.equals("Server")){
            if(messageContent.equals("Finish")){
                connect.getOutMsg().println("Finish/" + userData.userName );
                alarm.setText("--------- End Game ---------");
                finish = true;
                return;
            }
            currentChatting.add(new UserChat(messageContent, new Color(154, 254, 132)));
        }
        else currentChatting.add(new UserChat(messageContent, new Color(132, 167, 254)));
        vertical.setValue(vertical.getMaximum());
    }
    synchronized public void registerBid(String currentBidCost){
        userData.userBid = Integer.parseInt(currentBidCost);
        alarm.setText("상품 " + currentCard + " - 현재 금액: " + userData.userBid + "원"); // 상품 + 가격만, 입찰은 비밀
    }
    synchronized public void currentCard(String typeCard){
        int title = (int)typeCard.charAt(0);
        String number = typeCard.substring(1);
        String current = cardInfo[title- 65] + number;
        currentCard = current;
        changeCard(typeCard);
        alarm.setText("상품 " + current + " - 현재 금액: 0원"); // 상품 + 가격만, 입찰은 비밀
    }
    synchronized public void endRound(String typeMessage, String currentCoin, String card){
        if(card == null) return;
        userData.coin = Integer.parseInt(currentCoin);
        coin.setText(String.valueOf(userData.coin));
        userData.userBid = 0;
        int title = (int)card.charAt(0);
        int number = Integer.parseInt(card.substring(1));
        if(typeMessage.equals("NoneBidding")) return;
        else if(typeMessage.equals("WinBidding")){
            currentDack[(title - 65)*13 + (number - 1)] = 1;
            GamePageView.cardDack[(title - 65)*13 + (number - 1)].setVisible(true);
        }
        //String current = cardInfo[title- 65] + String.valueOf(number);
        remainCard[(title - 65)*13 + (number - 1)] = 1;
        GamePageView.card[(title - 65)*13 + (number - 1)].setVisible(false);
    }
    synchronized public void disconnect(String name){
        int getIdx = userData.userList.indexOf(name);
        userData.userList.remove(getIdx);
        connectUser.removeAll();
        for (String n : userData.userList) {
            connectUser.add(new ConnectUser("User: " + n));
        }
        connectUser.revalidate();
        connectUser.repaint(20, 10, 200, 200);
    }
}
