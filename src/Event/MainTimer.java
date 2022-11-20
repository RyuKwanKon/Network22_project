package Event;

import Component.*;

import Page.GamePagePanel.Alarm;
import Page.GamePagePanel.OnChat;
import Page.GamePagePanel.UserChat;
import Page.GamePageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.TimerTask;

import static Page.GamePagePanel.OnChat.input;
import static Page.GamePagePanel.ScrollChatting.vertical;
import static Page.GamePageView.currentChatting;

public class MainTimer implements ActionListener {
    public static int randTitle = 65;
    public static int randNumber = 1;

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            //server 요청
            //게임의 결과를 받아오기.
            //결과 받으면
            if (Alarm.startPrice == 0) { //유찰
                currentChatting.add(new UserChat(Data.cardInfo[randTitle - 65] + Data.cardInfoNumber.get(randNumber) + "가(이) 유찰되었습니다.", new Color(254, 132, 207)) );
            }
            else {
                currentChatting.add(new UserChat( Data.cardInfo[randTitle - 65] + Data.cardInfoNumber.get(randNumber) + "를 낙찰하셨습니다.", new Color(132, 167, 254)) );
                GamePageView.card[(randTitle - 65)*13 + randNumber - 1].setVisible(false);
            }

            randTitle = new Random().nextInt(4) + 65;
            Character title = (char) randTitle;
            randNumber = new Random().nextInt(13) + 1;

            BufferedImage tempImg = ImageIO.read(new File("./assets/Card/" + title + String.valueOf(randNumber) + ".png"));
            ImageIcon temp_img = new ImageIcon(tempImg);
            Image t = temp_img.getImage().getScaledInstance(141, 200, Image.SCALE_SMOOTH);
            GamePageView.CenterCard.setIcon(new ImageIcon(t));

            Alarm.startPrice = 0;
            GamePageView.alarm.setText("상품" + Data.cardInfo[randTitle - 65] + Data.cardInfoNumber.get(randNumber) + " - 금액: " + Alarm.startPrice);
            vertical.setValue(vertical.getMaximum());
        } catch (Exception error) {
            System.out.println(error);
        }
    }
}
