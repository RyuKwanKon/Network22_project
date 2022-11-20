package Page.GamePagePanel;

import Component.*;
import Page.*;
import javax.swing.*;
import java.awt.*;

import static Event.MainTimer.randNumber;
import static Event.MainTimer.randTitle;

public class Alarm extends JLabel {
    public static int startPrice = 0;
    Font font = new Font("Tahoma", Font.PLAIN, 35);
    public Alarm(){
        System.out.println(startPrice);
        Color background = new Color(28, 35, 42);
        Color fontColor = new Color(115, 255, 138);
        setLayout(null);
        setBounds(530, 50, 700, 50);
        setBackground(background);
        setText("상품" + Data.cardInfo[randTitle - 65] + Data.cardInfoNumber.get(randNumber) + " - 금액: " + startPrice);
        setFont(font);
        setForeground(fontColor);
    }
}
