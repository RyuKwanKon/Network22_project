package SW;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameManage extends Thread{
    private UserData userData;
    private ServerData serverData;
    public GameManage(){
        this.userData = new UserData();
        this.serverData = new ServerData();
    }
    @Override
    synchronized public void run(){
        while (true){
            try {
                wait(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            System.out.println(ServerData.isStart);
            if(UserData.count == 4 && ServerData.isStart){
                serverData.isStart = false;
                serverData.startGame = true;
                ServerData.auctionRemainTime = 5;
                System.out.println(ServerData.auctionRemainTime);
                GameThread gameThread = new GameThread();
                gameThread.start();
            }
        }
    }
}
