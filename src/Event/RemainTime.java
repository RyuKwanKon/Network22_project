package Event;
import Page.GamePagePanel.RemainTimer;

import javax.swing.*;
import java.util.Calendar;

import static GameData.UserData.remainTime;


import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class RemainTime extends JLabel{
    RemainTimer timer = new RemainTimer(5);
    public static Font BidFont=new Font("Tahoma",Font.CENTER_BASELINE,28);
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            while(true){
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }

            if (remainTime > -1) {
                remainTime--;
                setText(String.valueOf(remainTime));

            }
            else{
                break;
            }
            }
        }
    };
}
