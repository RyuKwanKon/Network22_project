package SW;

import java.awt.*;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class AuctionTimer extends Thread{

    private int remainTime = 5;
    public AuctionTimer(){

    }

    public void setRemainTime(){
        this.remainTime = 5;
    }

    public int getRemainTime(){
        while(true){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            if(remainTime > -1){
                remainTime--;
                return remainTime;
            }
            else break;
        }
        return 0;
    }
}

/*
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
*/
