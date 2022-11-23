package SW;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ServerTime implements Runnable{
    private Socket socket = null;
    private UserData userData;
    private ServerData serverData;
    public ServerTime(){
        this.userData = new UserData();
        this.serverData = new ServerData();
    }
    public synchronized void run(){
        try{
            if(userData.count == 4) {
                for (Map.Entry<String, PrintWriter> elem : userData.userConnectionList.entrySet()) {
                    //시간 요청.
                    elem.getValue().println("200/Timer/" + serverData.AuctionRemainTime);
                    elem.getValue().flush();
                }
                serverData.AuctionRemainTime--;
            }
        }catch(Exception e){}
    }
}
