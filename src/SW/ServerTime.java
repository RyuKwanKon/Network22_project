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
            // 이거 그냥 최초에 시작 하기만 하면 돌아가게 하면 될듯? 이거 유저 한명 빠지면 안돌아감
            if(serverData.auctionRemainTime > -1){
//                if(userData.userConnectionList.size() == 4) {
                    for (Map.Entry<String, PrintWriter> elem : userData.userConnectionList.entrySet()) {
                        //시간 요청.
                        elem.getValue().println("200/Timer/" + serverData.auctionRemainTime);
                        elem.getValue().flush();
                    }
                    serverData.auctionRemainTime--;
//                }
            }
        }catch(Exception e){}
    }

}
