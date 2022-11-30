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
//        System.out.println(serverData.auctionRemainTime);
        try{
            // 이거 그냥 최초에 시작 하기만 하면 돌아가게 하면 될듯? 이거 유저 한명 빠지면 안돌아감
            if(ServerData.auctionRemainTime > -1){
                for (Map.Entry<String, PrintWriter> elem : userData.userConnectionList.entrySet()) {
                    elem.getValue().println("200/Timer/" + serverData.auctionRemainTime);
                    elem.getValue().flush();
                }
                serverData.auctionRemainTime--;
            }
        }catch(Exception e){}
    }

}
