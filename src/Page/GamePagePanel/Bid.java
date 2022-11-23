package Page.GamePagePanel;

import ClientThread.BidThread;
import Component.*;
import Page.GamePageView;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;
import static Frame.Frame.client;
import static GameData.UserData.*;

public class Bid extends JLabel {
    public Bid(){
        Color border = new Color(254, 132, 207);
        setBounds(550, 400, 220, 40);
        setForeground(border);
        setFont(FontStyle.BidFont);
        setText("입찰 +5");
        setHorizontalAlignment(JLabel.CENTER);
        setBorder(new CustomLineBorder(border, 2,
                CustomLineBorder.RIGHT | CustomLineBorder.TOP | CustomLineBorder.LEFT | CustomLineBorder.BOTTOM));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                GameBidCoin += 5;
                GamePageView.alarm.setText("상품" + Data.cardInfo[randTitle - 65] + Data.cardInfoNumber.get(randNumber) + " - 금액: " + GameBidCoin + "원");
                Thread bidThread = new BidThread(client.getSocket());
                bidThread.start();

            }
        });
    }
}
