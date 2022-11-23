package Page.GamePagePanel;
import javax.swing.*;
import java.awt.*;
import java.util.Timer;

import static GameData.UserData.remainTime;
import java.util.Timer;
import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class RemainTimer extends JLabel {
    int second;
    public static Font BidFont=new Font("Tahoma",Font.CENTER_BASELINE,28);
    public RemainTimer(int second) {
        setBounds(1160,60,100,70);
        setFont(BidFont);
        setForeground(new Color(132, 167, 254));
        setText(String.valueOf(second));
        setHorizontalAlignment(JLabel.LEFT);
    }
}




