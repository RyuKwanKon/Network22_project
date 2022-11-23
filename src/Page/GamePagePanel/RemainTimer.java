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

public class RemainTimer extends JLabel implements Runnable {
    int second;
    public static Font BidFont=new Font("Tahoma",Font.CENTER_BASELINE,28);

    public RemainTimer(int second) {
        setBounds(1160,60,100,70);
        setFont(BidFont);
        setForeground(new Color(132, 167, 254));
        setText(String.valueOf(remainTime));
        setHorizontalAlignment(JLabel.LEFT);

        this.second = second;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if(second != 1)
                    Thread.sleep(1000);    // 1초
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (second > 1) {
                second -= 1;
                setText(second + "");
            } else
                second=6;
            }
        }
    }




