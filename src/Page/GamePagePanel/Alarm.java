package Page.GamePagePanel;

import Component.*;
import Page.*;
import javax.swing.*;
import java.awt.*;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;
import static GameData.UserData.*;

public class Alarm extends JLabel {
    Font font = new Font("Tahoma", Font.PLAIN, 35);
    public Alarm(){
        System.out.println(currentBidCoin);
        Color background = new Color(28, 35, 42);
        Color fontColor = new Color(115, 255, 138);
        setLayout(null);
        setBounds(480, 50, 800, 50);
        setBackground(background);
        setText("---- waiting game ----"); // + " - 금액: " + String.format("%01d", startPrice) + "원");
        setFont(font);
        setForeground(fontColor);
    }
}
